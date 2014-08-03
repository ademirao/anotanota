package org.anotanota.views;

import java.util.List;

import javax.inject.Inject;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.google.common.base.Function;

public class ArrayAdapterHelper {
  private final LayoutInflater mLayoutInflater;
  private final Context mContext;

  @Inject
  public ArrayAdapterHelper(LayoutInflater layoutInflater, Context context) {
    mLayoutInflater = layoutInflater;
    mContext = context;
  }

  public <T, V extends View> Builder<T, V> newBuilder() {
    return new Builder<T, V>();
  }

  public final class Builder<T, V extends View> {
    private int mItemResource;
    private List<T> mObjects;
    private Function<Pair<V, T>, Void> mLoadItemFunction;

    public Builder<T, V> setItemLayout(int itemResource) {
      mItemResource = itemResource;
      return this;
    }

    public Builder<T, V> setLoadItemFunction(Function<Pair<V, T>, Void> f) {
      mLoadItemFunction = f;
      return this;
    }

    public Builder<T, V> setObjects(List<T> objects) {
      mObjects = objects;
      return this;
    }

    public ArrayAdapter<T> get() {
      return new ArrayAdapter<T>(mContext, mItemResource, mObjects) {
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
          V receiptItemView = null;
          if (convertView == null) {
            receiptItemView = createReceiptItemView(mItemResource);
          } else {
            receiptItemView = (V) convertView;
          }
          mLoadItemFunction.apply(Pair.create(receiptItemView,
              mObjects.get(position)));
          return receiptItemView;
        }
      };
    }

    private final <V> V createReceiptItemView(int resource) {
      return (V) mLayoutInflater.inflate(resource, null);
    }
  }
}
