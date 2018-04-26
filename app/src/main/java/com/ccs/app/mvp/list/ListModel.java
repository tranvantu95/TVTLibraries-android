package com.ccs.app.mvp.list;

import android.arch.core.util.Function;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ccs.app.mvp.Model;
import com.ccs.app.mvp.utils.ListUtils;

import java.util.List;

public class ListModel<Item, P extends ListModel.Callback<Item>> extends Model<P> {

    private MutableLiveData<List<Item>> items = new MutableLiveData<>();

    private MutableLiveData<Integer> index = new MutableLiveData<>();

    private LiveData<Item> item = Transformations.switchMap(index, new Function<Integer, LiveData<Item>>() {
        @Override
        public LiveData<Item> apply(Integer input) {
            return getItem(input);
        }
    });

    private Item oldItem;

    @Nullable
    public LiveData<Item> getItem(@Nullable final Integer index) {
        return Transformations.map(items, new Function<List<Item>, Item>() {
            @Override
            public Item apply(List<Item> input) {
                if(input != null && index != null && ListUtils.isValidateIndex(input, index))
                    return input.get(index);
                return null;
            }
        });
    }

    @Nullable
    public List<Item> getItems() {
        return items.getValue();
    }

    public void setItems(@Nullable List<Item> items) {
        this.items.setValue(items);
    }

    @Nullable
    public Item getItem() {
        return item.getValue();
    }

    @Nullable
    public Integer getIndex() {
        return index.getValue();
    }

    public void setIndex(int index) {
        if(getIndex() != null && getIndex() == index) return;
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

        index.observe(owner, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                presenter.onIndexChange(integer);
            }
        });

        item.observe(owner, new Observer<Item>() {
            @Override
            public void onChanged(@Nullable Item item) {
                if(oldItem == null && item == null || oldItem != null && oldItem.equals(item)) return;
                oldItem = item;
                presenter.onItemChange(item);
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
        void onIndexChange(@Nullable Integer index);
        void onItemChange(@Nullable Item item);
    }
}
