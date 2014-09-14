package org.anotanota.pipeline.lexer;

import static org.anotanota.pipeline.lexer.Token.Type.NUMBER;
import static org.anotanota.pipeline.lexer.Token.Type.TEXT;

%%
%class Lexer
%public
%unicode

%{ 
  private int matchBegin = 0;
  private int matchEnd = 0;

  private void append() {
    matchEnd += yylength();
  }
  private Token emitToken(Token.Type type) {
    int begin = matchBegin;
    int end = matchEnd;
    matchBegin = matchEnd;
    
    return new Token(type, begin, end);
  }
%}

%type Token

%char

%state BEGIN_NUMBER
%state M_BEGIN_NUMBER
%state DEC_SEP
%state DEC_SEP_WITH_PREFIX
%state M_DEC_SEP
%state M_DEC_SEP_WITH_PREFIX
%state M_END_NUMBER
%state END_NUMBER
%state TEXT

SureDigit = [0-9]
MaybeDigit = [G!glLiIoO]
SureDecimalSeparator = [,.]
MaybeDecimalSeparator = [;g()\[\]]
Dollar = [$]
EndLine = \r|\n|\r\n
Separator = [ \t\f!@#$ˆ&*-_+={}|\\<>/?] | {EndLine}

%%

<YYINITIAL> {  
  {SureDigit} {
    yybegin(BEGIN_NUMBER);
    append();
  }

  {SureDecimalSeparator} {
    yybegin(DEC_SEP);
    append();
  }

  {MaybeDecimalSeparator} {
    yybegin(M_DEC_SEP);
    append();
  }

  {MaybeDigit} {
    yybegin(M_BEGIN_NUMBER);
    append();
  }

  [^] {
    yybegin(TEXT);
    append();
  }
}

<M_BEGIN_NUMBER> {
}

<M_DEC_SEP> {
}

<M_DEC_SEP_WITH_PREFIX> {
}

<M_END_NUMBER> {
}

<DEC_SEP_WITH_PREFIX> {  
  {SureDigit} {
    yybegin(END_NUMBER);
    append();
  }
  {SureDecimalSeparator} {
     Token t = emitToken(NUMBER);
     yybegin(DEC_SEP);
     append();
     return t;
  }
  [^] {
    yybegin(TEXT);
    append();
  }
}

<DEC_SEP> {  
  {SureDigit} {
    yybegin(END_NUMBER);
    append();
  }
  {SureDecimalSeparator} {
     Token t = emitToken(Token.Type.TEXT);
     yybegin(DEC_SEP);
     append();
     return t;
  }
  [^] {
     Token t = emitToken(Token.Type.DEC_SEP);
     yybegin(TEXT);
     append();
     return t;
  }
}

<BEGIN_NUMBER> {  
  {SureDigit} {
    append();
  }
  {SureDecimalSeparator} {
    yybegin(DEC_SEP_WITH_PREFIX);
    append();
  }
  [^] {
     Token t = emitToken(NUMBER);
     yybegin(TEXT);
     append();
     return t;
  }
}

<END_NUMBER> {  
  {SureDigit} {
    append();
  }
  {SureDecimalSeparator} {
     Token t = emitToken(NUMBER);
     yybegin(DEC_SEP);
     append();
     return t;
  }
  [^] {
     Token t = emitToken(NUMBER);
     yybegin(TEXT);
     append();
     return t;
  }
}

<TEXT> {  
  {SureDigit} {
     Token t = emitToken(Token.Type.TEXT);
     yybegin(BEGIN_NUMBER);
     append();
     return t;
  }
  {SureDecimalSeparator} {
     Token t = emitToken(Token.Type.TEXT);
     yybegin(DEC_SEP);
     append();
     return t;
  }
  [^] { append(); }
  <<EOF>> { return emitToken(Token.Type.TEXT); }
} 

