package com.ccs.app.mvp.list;

import android.arch.lifecycle.LifecycleOwner;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ccs.app.mvp.R;
import com.ccs.app.mvp.ViewController;

import java.util.List;

public abstract class ListViewController<Item,
        V extends LifecycleOwner,
        P extends ListViewCallback,
        LM extends RecyclerView.LayoutManager,
        LV extends RecyclerView,
        LA extends ListAdapter<Item, ?>>
        extends ViewController<V, P>
        implements ListPresenter.Callback<Item> {

    protected LM layoutManager;
    protected LV listView;
    protected LA adapter;

    public ListViewController(@NonNull V view) {
        super(view);
    }

    public class ListViewLifecycle extends ViewLifecycle {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            layoutManager = onCreateLayoutManager();
            adapter = onCreateAdapter();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return super.onCreateView(inflater, container, savedInstanceState);
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            listView = view.findViewById(R.id.list_view);

            updateListView();
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();

            listView.setLayoutManager(null);
            listView.setAdapter(null);
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
        }
    }

    protected abstract LM onCreateLayoutManager();

    protected abstract LA onCreateAdapter();

    protected void updateAdapter(@NonNull List<Item> items) {
        adapter.setItems(items);
        adapter.notifyDataSetChanged();
    }

    protected void updateListView() {
        listView.setLayoutManager(layoutManager);
        listView.setAdapter(adapter);
    }

    // ListPresenter Callback
    @Override
    public void onItemsChange(@Nullable List<Item> items) {
        if(items != null) updateAdapter(items);
    }

}
