package org.anotanota;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Provider;

import org.anotanota.framework.Navigation;
import org.anotanota.framework.UIViewController;
import org.anotanota.model.Receipt;
import org.anotanota.model.ReceiptItem;
import org.anotanota.model.ReceiptItemsDataAccess;
import org.anotanota.model.ReceiptsDataAccess;
import org.anotanota.views.ArrayAdapterHelper;

import android.graphics.Color;
import android.util.Pair;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout.LayoutParams;
import android.widget.TextView;

import com.google.common.base.Function;
import com.google.common.util.concurrent.ListenableFuture;

public class ReceiptsViewController implements UIViewController {
  private final ReceiptsDataAccess mDataAccess;
  private final ReceiptItemsDataAccess mItemsDataAccess;
  private final Provider<ListView> mListViewProvider;
  private final Provider<TextView> mTextViewProvider;
  private final ArrayAdapterHelper mListViewBuilder;
  private final Executor mExecutor;
  private final Navigation mNavigation;

  @Inject
  public ReceiptsViewController(Provider<ListView> listViewProvider,
    Provider<TextView> textViewProvider,
    ArrayAdapterHelper arrayAdapterBuilder,
    ReceiptsDataAccess receiptsDataAccess,
    ReceiptItemsDataAccess receiptItemsDataAccess,
    Navigation navigation,
    Executor executor) {
    mDataAccess = receiptsDataAccess;
    mListViewProvider = listViewProvider;
    mListViewBuilder = arrayAdapterBuilder;
    mExecutor = executor;
    mNavigation = navigation;
    mTextViewProvider = textViewProvider;
    mItemsDataAccess = receiptItemsDataAccess;
  }

  @Override
  public View loadView() {
    final ListenableFuture<List<Receipt>> items = mDataAccess.listReceipts();
    final ListView listView = mListViewProvider.get();
    final ArrayAdapterHelper.Builder<Receipt, LinearLayout> builder = mListViewBuilder
        .<Receipt, LinearLayout> newBuilder().setItemLayout(R.layout.receipts)
        .setLoadItemFunction(new Function<Pair<LinearLayout, Receipt>, Void>() {
          @Override
          public Void apply(Pair<LinearLayout, Receipt> pair) {
            loadReceiptView(pair.second, pair.first);
            return null;
          }
        });
    items.addListener(new Runnable() {

      @Override
      public void run() {
        List<Receipt> itemsList = FuturesUtil.get(items);
        listView.setAdapter(builder.setObjects(itemsList).get());
      }
    }, mExecutor);
    return listView;
  }

  private static List<ReceiptItem> findItems(String content) {
    String[] lines = content.split("\n");
    for (String line : lines) {
      System.out.println("LINE: " + line);
    }
    return Arrays.asList();
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
            TextView textView = mTextViewProvider.get();
            textView.setBackgroundColor(Color.WHITE);
            textView.setLayoutParams(new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            textView.setText(receipt.getContent());
            List<ReceiptItem> items = findItems(receipt.getContent());
            for (ReceiptItem item : items) {
              mItemsDataAccess.createItem(item);
            }
            return textView;
          }
        });
      }
    });
  }
}
