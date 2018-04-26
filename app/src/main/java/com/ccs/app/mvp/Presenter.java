package com.ccs.app.mvp;

import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;

public abstract class Presenter<M extends Model<?>, V extends Presenter.Callback>
        implements Model.Callback, ViewCallback {

    protected M model;

    protected V view;

    public Presenter(@NonNull V view, @NonNull M model) {
        this.view = view;
        this.model = model;
        observeModel(model);
    }


    protected abstract void observeModel(@NonNull M model);
    // {
    //      model.setPresenter(this);
    //}

    // Model Callback
    @NonNull
    @Override
    public LifecycleOwner getLifecycleOwner() {
        return view.getLifecycleOwner();
    }

    public interface Callback {
        @NonNull
        LifecycleOwner getLifecycleOwner();
    }
}
