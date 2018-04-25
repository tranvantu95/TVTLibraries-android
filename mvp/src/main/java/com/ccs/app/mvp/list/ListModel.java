package com.ccs.app.mvp.list;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ccs.app.mvp.Model;

import java.util.List;

public class ListModel<Item, P extends ListModel.Callback<Item>> extends Model<P> {

    private MutableLiveData<List<Item>> items = new MutableLiveData<>();

    private MutableLiveData<Item> item = new MutableLiveData<>();

    private MutableLiveData<Integer> index = new MutableLiveData<>();

    @Nullable
    public List<Item> getItems() {
        return items.getValue();
    }

    public void setItems(@Nullable List<Item> items) {
        this.items.setValue(items);
    }

    @Nullable
    public Item getItem(int index) {
        if(getItems() != null) return getItems().get(index);
        return null;
    }

    @Nullable
    public Item getItem() {
        return item.getValue();
    }

    public void setItem(@Nullable Item item) {
        this.item.setValue(item);
    }

    @Nullable
    public Integer getIndex() {
        return index.getValue();
    }

    public void setIndex(@Nullable Integer index) {
        this.index.setValue(index);
    }

    @Override
    protected void setPresenter(@NonNull P p) {
        super.setPresenter(p);

        LifecycleOwner owner = presenter.getLifecycleOwner();

        items.observe(owner, new Observer<List<Item>>() {
            @Override
            public void onChanged(@Nullable List<Item> items) {
                presenter.onItemsChange(items);
            }
        });

        item.observe(owner, new Observer<Item>() {
            @Override
            public void onChanged(@Nullable Item item) {
                presenter.onItemChange(item);
            }
        });

        index.observe(owner, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                presenter.onIndexChange(integer);
            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        LifecycleOwner owner = presenter.getLifecycleOwner();

        items.removeObservers(owner);
        item.removeObservers(owner);
        index.removeObservers(owner);
    }

    public interface Callback<Item> extends Model.Callback {
        void onItemsChange(@Nullable List<Item> items);
        void onItemChange(@Nullable Item item);
        void onIndexChange(@Nullable Integer index);
    }
}
