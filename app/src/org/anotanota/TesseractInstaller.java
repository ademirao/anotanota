package org.anotanota;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.inject.Inject;
import javax.inject.Singleton;

import android.content.res.AssetManager;

import com.google.common.base.Joiner;

@Singleton
public class TesseractInstaller {

  private final AssetManager mAssetManager;
  private final byte[] copyBuffer = new byte[1 << 20]; // 1MB
  private final String mTesseractPath;
  private final String mTesseractAssetsPath;

  @Inject
  public TesseractInstaller(AssetManager assetManager,
    @Anotanota.TesseractInstallPath String tesseractPath,
    @Anotanota.TesseractAssetsPath String tesseractAssetsPath) {
    mAssetManager = assetManager;
    mTesseractPath = tesseractPath;
    mTesseractAssetsPath = tesseractAssetsPath;
  }

  private void recursiveCopyDirFiles(final String srcDir, final String outDir) {
    File file = new File(Joiner.on(File.separator).join(outDir, "checkpoint"));
    if (file.exists()) {
      System.out.println("Tesseract already installed.");
      return;
    }
    try {
      recursiveCopyDirFiles(srcDir, mAssetManager.list(srcDir), outDir);
      FileOutputStream fos = new FileOutputStream(file);
      fos.write("checkpoint".getBytes());
      fos.flush();
      fos.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void recursiveCopyDirFiles(final String srcDir,
                                     final String[] contentFiles,
                                     final String outDir) {
    if (contentFiles.length == 0) {
      return;
    }
    File dir = new File(outDir);
    if (!dir.exists()) {
      dir.mkdirs();
    }
    try {
      for (String path : contentFiles) {
        final String fullOutChildPath = Joiner.on(File.separator).join(outDir,
            path);
        final String fullSrcChildPath = Joiner.on(File.separator).join(srcDir,
            path);
        String[] childContentFiles = mAssetManager.list(fullSrcChildPath);
        if (childContentFiles.length > 0) {
          recursiveCopyDirFiles(fullSrcChildPath, childContentFiles,
              fullOutChildPath);
          continue;
        }
        InputStream is = null;
        OutputStream os = null;
        try {
          System.out.println("Copying file: " + fullSrcChildPath + " to "
              + fullOutChildPath);
          is = mAssetManager.open(fullSrcChildPath);
          os = new FileOutputStream(fullOutChildPath);
          int read = 0;
          while ((read = is.read(copyBuffer)) != -1) {
            os.write(copyBuffer, 0, read);
          }
          is.close();
          is = null;
          os.flush();
          os.close();
          os = null;
        } catch (IOException ioe) {
          throw new RuntimeException(ioe);
        } finally {
          if (is != null)
            is.close();
          if (os != null)
            os.close();
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public synchronized void install() {
    recursiveCopyDirFiles(mTesseractAssetsPath, mTesseractPath);
  }
}
