package com.ccs.app.mvp.sample;

import android.support.annotation.NonNull;

import com.ccs.app.mvp.list.ListPresenter;

public class MyPresenter extends ListPresenter<String, MyModel, MyPresenter.Callback>
        implements MyModel.Callback, MyViewCallback {

    public MyPresenter(@NonNull Callback view, @NonNull MyModel model) {
        super(view, model);
    }

    @Override
    protected void observeModel(@NonNull MyModel model) {
        model.setPresenter(this);
    }

    @Override
    public void onItemClick(int position) {
        super.onItemClick(position);

        view.showToast(model.getItems().get(position));
    }

    public interface Callback extends ListPresenter.Callback<String> {
        void showToast(String text);
    }
}
