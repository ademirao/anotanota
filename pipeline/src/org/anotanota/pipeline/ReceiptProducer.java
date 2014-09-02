package org.anotanota.pipeline;

import java.io.File;

import org.anotanota.framework.pipeline.Product;
import org.anotanota.model.Receipt;
import org.anotanota.model.ReceiptsDataAccess;

import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import dagger.Module;
import dagger.Provides;

@Module(library = true, includes = { ReceiptProducer.Inputs.class }, injects = { Receipt.class }, overrides = true)
public class ReceiptProducer {

	@Module(library = true)
	public static class Inputs {
		@Provides
		File getInput() {
			return null;
		}

		@Provides
		ReceiptsDataAccess getDa() {
			return null;
		}

		@Provides
		OCR getInLoader() {
			return null;
		}
	}

	@Provides
	@Product
	ListenableFuture<Receipt> getResult(@Product final File file, OCR ocr,
			final ReceiptsDataAccess receiptsDA) {
		System.out.println(" Going producer...");
		return Futures.transform(ocr.getUTF8Text(file), new AsyncFunction<String, Receipt>() {

			@Override
			public ListenableFuture<Receipt> apply(String ocrString)
					throws Exception {
				Receipt receipt = new Receipt.Builder().setContent(ocrString)
						.setPath(file.getAbsolutePath()).get();
				return Futures.immediateFuture(receipt);
			}
		});
	}
}
