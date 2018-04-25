package com.ccs.app.mvp.list;

import android.arch.lifecycle.LifecycleOwner;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.List;

public abstract class ListFragment<Item> extends Fragment implements ListPresenter.Callback<Item> {

    private ListModel<Item, ?> model;
    protected ListViewCallback presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = onCreatePresenter();
    }

    protected abstract ListViewCallback onCreatePresenter();

    @NonNull
    @Override
    public LifecycleOwner getLifecycleOwner() {
        return this;
    }

    @Override
    public void onItemsChange(@Nullable List<Item> items) {

    }

    @Override
    public void onItemChange(@Nullable Item item) {

    }

    @Override
    public void onIndexChange(@Nullable Integer index) {

    }
}
