package org.anotanota.pipeline;

import java.io.File;

import com.google.common.util.concurrent.ListenableFuture;

public interface OCR {
	String getUTF8Text(File file);
}
