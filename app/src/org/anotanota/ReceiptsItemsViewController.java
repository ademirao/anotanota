package org.anotanota;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Provider;

import org.anotanota.framework.Activity;
import org.anotanota.framework.UIViewController;
import org.anotanota.model.ReceiptItem;
import org.anotanota.model.ReceiptItemsDataAccess;
import org.anotanota.views.ArrayAdapterHelper;

import android.support.v7.app.ActionBar;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.common.base.Function;
import com.google.common.util.concurrent.ListenableFuture;

public class ReceiptsItemsViewController implements UIViewController {
  private final Activity mActivity;
  private final ArrayAdapterHelper mListViewBuilder;
  private final Provider<ListView> mListViewProvider;
  private final ReceiptItemsDataAccess mDataAccess;
  private final Executor mExecutor;
  private final LayoutInflater mLayoutInflater;

  @Inject
  public ReceiptsItemsViewController(ActionBar actionBar,
    Activity activity,
    LayoutInflater layoutInfrater,
    Provider<ListView> listViewProvider,
    ArrayAdapterHelper adapterBuilder,
    ReceiptItemsDataAccess dataAccess,
    Executor executor) {
    mActivity = activity;
    mListViewBuilder = adapterBuilder;
    mDataAccess = dataAccess;
    mLayoutInflater = layoutInfrater;
    mExecutor = executor;
    mListViewProvider = listViewProvider;
  }

  @Override
  public View loadView() {
    final ListenableFuture<List<ReceiptItem>> items = mDataAccess.listItems();
    final ListView listView = mListViewProvider.get();
    final ArrayAdapterHelper.Builder<ReceiptItem, LinearLayout> builder = mListViewBuilder
        .<ReceiptItem, LinearLayout> newBuilder()
        .setItemLayout(R.layout.receipt_item)
        .setLoadItemFunction(
            new Function<Pair<LinearLayout, ReceiptItem>, Void>() {
              @Override
              public Void apply(Pair<LinearLayout, ReceiptItem> pair) {
                loadReceiptItemView(pair.second, pair.first);
                return null;
              }
            });
    items.addListener(new Runnable() {
      List<ReceiptItem> itemsList = FuturesUtil.get(items);

      @Override
      public void run() {
        listView.setAdapter(builder.setObjects(itemsList).get());
      }
    }, mExecutor);
    return listView;
  }

  private final void loadReceiptItemView(ReceiptItem item, LinearLayout view) {
    ((EditText) view.findViewById(R.id.name)).setText(item.getName());
    ((EditText) view.findViewById(R.id.quantity)).setText(Float.toString(item
        .getQuantity()));
    ((EditText) view.findViewById(R.id.price)).setText(Float.toString(item
        .getPrice()));
  }
}
