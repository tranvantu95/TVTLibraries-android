package com.ccs.app.mvp.sample;

import android.support.annotation.NonNull;

import com.ccs.app.mvp.Presenter;
import com.ccs.app.mvp.list.ListPresenter;

public class MyPresenter extends ListPresenter<String, MyModel, MyPresenter.Callback>
        implements MyModel.Callback, ViewCallback {

    public MyPresenter(@NonNull Callback view, @NonNull MyModel model) {
        super(view, model);
    }

    @Override
    protected void observeModel(@NonNull MyModel model) {
        model.setPresenter(this);
    }

    // Model Callback
    @Override
    public void onTextChange(CharSequence text) {
        view.onTextChange(text);
    }

    // View Callback
    @Override
    public void onTextInputChange(CharSequence text) {
        model.setText(text);
    }

    public interface Callback extends ListPresenter.Callback<String> {
        void onTextChange(CharSequence text);
    }
}
