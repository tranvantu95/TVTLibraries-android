package com.ccs.app.mvp.sample;

import android.support.annotation.NonNull;

import com.ccs.app.mvp.list.ListModel;

public class MyModel extends ListModel<String, MyModel.Callback> {

    @Override
    public void setPresenter(@NonNull MyModel.Callback myPresenter) {
        super.setPresenter(myPresenter);
    }

    public interface Callback extends ListModel.Callback<String> {}
}
