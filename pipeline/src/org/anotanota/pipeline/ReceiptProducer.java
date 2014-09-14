package org.anotanota.pipeline;

import java.io.File;

import org.anotanota.model.Receipt;
import org.anotanota.pipeline.framework.Product;

import dagger.Module;
import dagger.Provides;

@Module(library = true, complete=false)
public class ReceiptProducer {

	@Provides
	@Product
	Receipt getResult(@Product final File file, OCR ocr) {
		System.out.println(" Going producer...");
		String ocrString = ocr.getUTF8Text(file);
		Receipt receipt = new Receipt.Builder().setContent(ocrString.toUpperCase())
				.setPath(file.getAbsolutePath()).get();
		return receipt;

	}
}
