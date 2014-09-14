package org.anotanota.model;

public class ReceiptItem {
  private int mId;
  private String mName;
  private float mQuantity;
  private float mPrice;
  private int mReceiptId;
  private String mContent;

  private ReceiptItem() {
  }

  public static final class Builder {
    ReceiptItem item = new ReceiptItem();

    public ReceiptItem get() {
      try {
        return item;
      } finally {
        item = null;
      }
    }

    public Builder setId(int id) {
      item.mId = id;
      return this;
    }

    public Builder setName(String name) {
      item.mName = name;
      return this;
    }

    public Builder setQuantity(float quantity) {
      item.mQuantity = quantity;
      return this;
    }

    public Builder setPrice(float price) {
      item.mPrice = price;
      return this;
    }

    public Builder setReceiptId(int receiptId) {
      item.mReceiptId = receiptId;
      return this;
    }

    public Builder setContent(String content) {
      item.mContent = content;
      return this;
    }
  }

  public int getId() {
    return mId;
  }

  public String getName() {
    return mName;
  }

  public float getQuantity() {
    return mQuantity;
  }

  public float getPrice() {
    return mPrice;
  }

  public int getReceiptId() {
    return mReceiptId;
  }

  public String getContent() {
    return mContent;
  }
  
  public String toString() {
	  return "name: '" + mName + "' content: '" + mContent + "'\n";
  }
}
