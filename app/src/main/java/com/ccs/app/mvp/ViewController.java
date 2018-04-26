package com.ccs.app.mvp;

import android.arch.lifecycle.LifecycleOwner;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class ViewController <
        V extends LifecycleOwner,
        P extends ViewCallback>
        implements Presenter.Callback {

    protected V view;

    protected P presenter;

    private Lifecycle lifecycle;

    public Lifecycle getLifecycle() {
        return lifecycle;
    }

    public ViewController(@NonNull V view) {
        this.view = view;
        lifecycle = onCreateLifecycle();
    }

    protected Lifecycle onCreateLifecycle() {
        return new ViewLifecycle();
    }

    public interface Lifecycle {
        void onCreate(@Nullable Bundle savedInstanceState);
        View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);
        void onViewCreated(View view, @Nullable Bundle savedInstanceState);
        void onActivityCreated(@Nullable Bundle savedInstanceState);
        void onDestroyView();
        void onDestroy();
    }

    public class ViewLifecycle implements Lifecycle {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            presenter = onCreatePresenter();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(getLayoutId(), container, false);
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        }

        @Override
        public void onDestroyView() {

        }

        @Override
        public void onDestroy() {

        }
    }

    protected abstract P onCreatePresenter();

    protected abstract int getLayoutId();

    // Presenter Callback
    @NonNull
    @Override
    public LifecycleOwner getLifecycleOwner() {
        return view;
    }

}
