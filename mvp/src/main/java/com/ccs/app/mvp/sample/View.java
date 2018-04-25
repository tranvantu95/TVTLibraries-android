package com.ccs.app.mvp.sample;

import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.ccs.app.mvp.list.ListFragment;
import com.ccs.app.mvp.list.ListViewCallback;

public class View extends ListFragment<String> implements MyPresenter.Callback {

    private EditText editText;
    private TextView textView;

    @Override
    protected ListViewCallback onCreatePresenter() {
        return new MyPresenter(this, new MyModel());
    }

    @NonNull
    @Override
    public LifecycleOwner getLifecycleOwner() {
        return null;
    }

    @Override
    public void onTextChange(CharSequence text) {
        textView.setText(text);
    }

}
