package org.anotanota.pipeline.lexer;

import java.util.ArrayList;

public class Token {
	enum Type {
		TEXT,
		NUMBER,
		MAYBE_NUMBER,
		DEC_SEP,
		MAYBE_DIGIT,
		MAYBE_DEC_SEP,
	}

	public final int begin;
	public final int end;
	public final Type type;
	public final ArrayList<Token> children = new ArrayList<Token>();
	public Token(Type type, int begin, int end) {
		this.type = type;
		this.begin = begin;
		this.end = end;
	}
	
	public String toString() {
		return "(" + type + ") [" + begin + ", " + end + "] ";
	}

	public boolean isEmpty() {
		return begin == end;
	}
	
	public static String asString(String content, Token token) {
		return content.substring(token.begin, token.end);
	}
}
