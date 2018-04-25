package com.ccs.app.mvp.sample;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ccs.app.mvp.Model;
import com.ccs.app.mvp.list.ListModel;

public class MyModel extends ListModel<String, MyModel.Callback> {

    private MutableLiveData<CharSequence> text = new MutableLiveData<>();

    public CharSequence getText() {
        return text.getValue();
    }

    public void setText(CharSequence text) {
        this.text.setValue(text);
    }

    @Override
    public void setPresenter(@NonNull MyModel.Callback myPresenter) {
        super.setPresenter(myPresenter);

        text.observe(presenter.getLifecycleOwner(), new Observer<CharSequence>() {
            @Override
            public void onChanged(@Nullable CharSequence charSequence) {
                presenter.onTextChange(charSequence);
            }
        });
    }

    public interface Callback extends ListModel.Callback<String> {
        void onTextChange(CharSequence text);
    }
}
