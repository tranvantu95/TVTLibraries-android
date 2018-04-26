package com.ccs.app.mvp.sample;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.ccs.app.mvp.R;
import com.ccs.app.mvp.list.ListAdapter;
import com.ccs.app.mvp.list.ListViewController;
import com.ccs.app.mvp.utils.ModelProviders;

public class MyViewController extends ListViewController<String, MyFragment, MyViewCallback,
        LinearLayoutManager, RecyclerView, MyAdapter>
        implements MyPresenter.Callback {

    public MyViewController(@NonNull MyFragment view) {
        super(view);
    }

    @Override
    protected Lifecycle onCreateLifecycle() {
        return new ListViewLifecycle();
    }

    @Override
    protected MyViewCallback onCreatePresenter() {
        return new MyPresenter(this, ModelProviders.of(view.getActivity()).get(MyModel.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    protected LinearLayoutManager onCreateLayoutManager() {
        return new LinearLayoutManager(view.getContext());
    }

    @Override
    protected MyAdapter onCreateAdapter() {
        return new MyAdapter(new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
                presenter.onItemClick(position);
            }
        });
    }

    // Presenter Callback
    @Override
    public void onIndexChange(@Nullable Integer index) {
        Toast.makeText(view.getContext(), "" + index, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemChange(@Nullable String s) {
        Toast.makeText(view.getContext(), "" + s, Toast.LENGTH_LONG).show();
    }

}
