package com.ccs.app.mvp.utils;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.lifecycle.ViewModelStore;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class ModelProviders extends ViewModelProviders {

    private static ViewModelStore appViewModelStore;

    @NonNull
    @MainThread
    public static ViewModelProvider ofApp(@Nullable Application application) {
        return ofApp(application, null);
    }

    @NonNull
    @MainThread
    public static ViewModelProvider ofApp(@Nullable Application application, @Nullable ViewModelProvider.Factory factory) {
        if (application == null) {
            throw new IllegalStateException("Your activity/fragment is not yet attached to "
                    + "Application. You can't request ViewModel before onCreate call.");
        }
        if(appViewModelStore == null) {
            appViewModelStore = new ViewModelStore();
        }
        if (factory == null) {
            factory = ViewModelProvider.AndroidViewModelFactory.getInstance(application);
        }
        return new ViewModelProvider(appViewModelStore, factory);
    }

    @NonNull
    @MainThread
    public static ViewModelProvider ofApp() {
        return ofApp(new ViewModelProvider.NewInstanceFactory());
    }

    @NonNull
    @MainThread
    public static ViewModelProvider ofApp(@NonNull ViewModelProvider.Factory factory) {
        if(appViewModelStore == null) {
            appViewModelStore = new ViewModelStore();
        }
        return new ViewModelProvider(appViewModelStore, factory);
    }

}
