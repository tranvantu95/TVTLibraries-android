package com.ccs.app.mvp.sample;

import android.view.View;
import android.widget.TextView;

import com.ccs.app.mvp.R;
import com.ccs.app.mvp.list.ListAdapter;

public class MyAdapter extends ListAdapter<String, MyAdapter.ViewHolder> {

    public MyAdapter(OnItemClickListener onItemClickListener) {
        super(onItemClickListener);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_list;
    }

    @Override
    protected ViewHolder getViewHolder(View view) {
        return new ViewHolder(this, view);
    }

    public static class ViewHolder extends ListAdapter.ViewHolder<String, MyAdapter> {

        private TextView textView;

        public ViewHolder(MyAdapter adepter, View itemView) {
            super(adepter, itemView);

            textView = itemView.findViewById(R.id.text_view);
        }

        @Override
        public void setItem(String s) {
            super.setItem(s);

            textView.setText(s);
        }
    }
}
