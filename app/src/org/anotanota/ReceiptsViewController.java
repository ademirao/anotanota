package org.anotanota;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import org.anotanota.framework.Navigation;
import org.anotanota.framework.UIViewController;
import org.anotanota.model.Receipt;
import org.anotanota.model.ReceiptItemsDataAccess;
import org.anotanota.model.ReceiptsDataAccess;
import org.anotanota.views.ArrayAdapterHelper;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.common.base.Function;
import com.google.common.util.concurrent.ListenableFuture;

public class ReceiptsViewController implements UIViewController {
  private final ReceiptsDataAccess mDataAccess;
  private final ReceiptItemsDataAccess mItemsDataAccess;
  private final ArrayAdapterHelper mListViewBuilder;
  private final Executor mExecutor;
  private final Navigation mNavigation;
  private final LayoutInflater mLayoutInflater;

  @Inject
  public ReceiptsViewController(LayoutInflater layoutInflater,
    ArrayAdapterHelper arrayAdapterBuilder,
    ReceiptsDataAccess receiptsDataAccess,
    ReceiptItemsDataAccess receiptItemsDataAccess,
    Navigation navigation,
    Executor executor) {
    mDataAccess = receiptsDataAccess;
    mListViewBuilder = arrayAdapterBuilder;
    mExecutor = executor;
    mNavigation = navigation;
    mItemsDataAccess = receiptItemsDataAccess;
    mLayoutInflater = layoutInflater;
  }

  @Override
  public View loadView() {
    final SwipeRefreshLayout receiptsList = (SwipeRefreshLayout) mLayoutInflater
        .inflate(R.layout.swipe_to_refresh_list, null);

    final ListView listView = (ListView) receiptsList.findViewById(R.id.list);
    final ArrayAdapterHelper.Builder<Receipt, LinearLayout> builder = mListViewBuilder
        .<Receipt, LinearLayout> newBuilder().setItemLayout(R.layout.receipt)
        .setLoadItemFunction(new Function<Pair<LinearLayout, Receipt>, Void>() {
          @Override
          public Void apply(Pair<LinearLayout, Receipt> pair) {
            loadReceiptView(pair.second, pair.first);
            return null;
          }
        });

    final Function<Void, Void> loadData = new Function<Void, Void>() {
      @Override
      public Void apply(Void arg0) {
        final ListenableFuture<List<Receipt>> items = mDataAccess
            .listReceipts();
        items.addListener(new Runnable() {
          @Override
          public void run() {
            List<Receipt> itemsList = FuturesUtil.get(items);
            listView.setAdapter(builder.setObjects(itemsList).get());
            receiptsList.setRefreshing(false);
          }
        }, mExecutor);
        return null;
      }
    };

    receiptsList.setOnRefreshListener(new OnRefreshListener() {
      @Override
      public void onRefresh() {
        loadData.apply(null);
      }
    });
    receiptsList.setRefreshing(true);
    loadData.apply(null);
    return receiptsList;
  }

  private void loadReceiptView(final Receipt receipt, LinearLayout view) {
    TextView textView = ((TextView) view.findViewById(R.id.path));
    textView.setText(receipt.getPath());
    textView.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        mNavigation.navigateTo(new UIViewController() {
          @Override
          public View loadView() {
            View view = mLayoutInflater.inflate(R.layout.view_receipt, null);
            EditText textView = (EditText) view
                .findViewById(R.id.receipt_content);
            textView.setText(receipt.getContent());
            return view;
          }
        });
      }
    });
  }
}
