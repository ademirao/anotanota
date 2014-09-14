package org.anotanota.pipeline;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.anotanota.model.Receipt;
import org.anotanota.model.ReceiptItem;
import org.anotanota.pipeline.lexer.Lexer;
import org.anotanota.pipeline.lexer.Token;
import org.xml.sax.ext.LexicalHandler;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.UnmodifiableListIterator;
import com.sun.org.apache.bcel.internal.generic.IMUL;

import dagger.Module;
import dagger.Provides;

@Module(library = true, injects = ReceiptItemsProducer.Result.class, complete = false)
public class ReceiptItemsProducer {


	public static class Result {
		@Inject
		public List<ReceiptItem> result;
	}

	@Provides
	public List<ReceiptItem> produce(Receipt receipt) { 
		String content = receipt.getContent();
		System.out.println(content);
		Lexer lexer = new Lexer(new StringReader(content));
		try {
		Token t = null;
		do {
			t = lexer.yylex();
			if (t.isEmpty()) {
				break;
			}
			System.out.println(t + "<<" + Token.asString(content, t) + ">>");
		} while (t!= null);
		
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
		List<ReceiptItem> items = new ArrayList<ReceiptItem>();
		/*for (String line : lines) {
			ReceiptItem item = new ReceiptItem.Builder().setContent(line)
					.setReceiptId(receipt.getId()).get();
			items.add(item);
		}*/
		return items;
	}
}
