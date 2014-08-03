package org.anotanota.model;

public class ReceiptItem {
  private int mId;
  private String mName;
  private float mQuantity;
  private float mPrice;
  private int mReceiptId;

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
      item.setName(name);
      return this;
    }

    public Builder setQuantity(float quantity) {
      item.setQuantity(quantity);
      return this;
    }

    public Builder setPrice(float price) {
      item.setPrice(price);
      return this;
    }

    public Builder setReceiptId(int receiptId) {
      item.setReceiptId(receiptId);
      return this;
    }
  }

  public int getId() {
    return mId;
  }

  public String getName() {
    return mName;
  }

  private void setName(String mName) {
    this.mName = mName;
  }

  public float getQuantity() {
    return mQuantity;
  }

  private void setQuantity(float mQuantity) {
    this.mQuantity = mQuantity;
  }

  public float getPrice() {
    return mPrice;
  }

  private void setPrice(float mPrice) {
    this.mPrice = mPrice;
  }

  public int getReceiptId() {
    return mReceiptId;
  }

  private void setReceiptId(int mReceiptId) {
    this.mReceiptId = mReceiptId;
  }
}
