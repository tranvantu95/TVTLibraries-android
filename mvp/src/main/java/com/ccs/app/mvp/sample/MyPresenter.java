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

    public interface Callback extends ListPresenter.Callback<String> {
    }
}
