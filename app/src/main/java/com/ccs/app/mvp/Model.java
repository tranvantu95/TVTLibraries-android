package com.ccs.app.mvp;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

public class Model<P extends Model.Callback> extends ViewModel {

    protected P presenter;

    protected void setPresenter(@NonNull P p) {
        presenter = p;
    }

    public interface Callback {
        @NonNull
        LifecycleOwner getLifecycleOwner();
    }
}
