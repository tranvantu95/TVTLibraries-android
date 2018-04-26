package com.ccs.app.mvp.list;

import android.support.annotation.CallSuper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class ListAdapter<Item,
        VH extends ListAdapter.ViewHolder<Item, ?>>
        extends RecyclerView.Adapter<VH> {

    private List<Item> items = new ArrayList<>();

    private OnItemClickListener onItemClickListener;

    public ListAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getItemLayoutId(), parent, false);
        return getViewHolder(view);
    }

    protected abstract int getItemLayoutId();

    protected abstract VH getViewHolder(View view);

    @Override
    public void onBindViewHolder(VH holder, int position) {
        Item item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder<Item,
            LA extends ListAdapter<Item, ?>>
            extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected LA adepter;

        protected Item item;

        public ViewHolder(LA adepter, View itemView) {
            super(itemView);
            this.adepter = adepter;
            itemView.setOnClickListener(this);
        }

        @CallSuper
        public void setItem(Item item) {
            this.item = item;
        }

        @Override
        public void onClick(View view) {
            adepter.getOnItemClickListener().onItemClick(this, view, getAdapterPosition());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position);
    }
}
