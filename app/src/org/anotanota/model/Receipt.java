package org.anotanota.model;

public class Receipt {
  private int mId;
  private String mPath;
  private String mContent;

  private Receipt() {

  }

  public static class Builder {
    private final Receipt receipt = new Receipt();

    public Builder setId(int id) {
      receipt.mId = id;
      return this;
    }

    public Builder setPath(String path) {
      receipt.mPath = path;
      return this;
    }

    public Builder setContent(String content) {
      receipt.mContent = content;
      return this;
    }

    public Receipt get() {
      return receipt;
    }
  }

  public int getId() {
    return mId;
  }

  public String getPath() {
    return mPath;
  }

  public String getContent() {
    return mContent;
  }
}
