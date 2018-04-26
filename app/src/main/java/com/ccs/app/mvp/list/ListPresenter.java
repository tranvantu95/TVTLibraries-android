package com.ccs.app.mvp.list;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ccs.app.mvp.Presenter;

import java.util.List;

public abstract class ListPresenter<Item,
        M extends ListModel<Item, ?>,
        V extends ListPresenter.Callback<Item>>
        extends Presenter<M, V>
        implements ListModel.Callback<Item>, ListViewCallback {

    public ListPresenter(@NonNull V view, @NonNull M model) {
        super(view, model);
    }

    // ListModel Callback
    @Override
    public void onItemsChange(@Nullable List<Item> items) {
        view.onItemsChange(items);
    }

    @Override
    public void onIndexChange(@Nullable Integer index) {
        view.onIndexChange(index);
    }

    @Override
    public void onItemChange(@Nullable Item item) {
        view.onItemChange(item);
    }

    // ListView Callback
    @Override
    public void onItemClick(int position) {
        model.setIndex(position);
    }

    public interface Callback<Item> extends Presenter.Callback {
        void onItemsChange(@Nullable List<Item> items);
        void onIndexChange(@Nullable Integer index);
        void onItemChange(@Nullable Item item);
    }
}
