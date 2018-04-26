package com.ccs.app.mvp.list;

import com.ccs.app.mvp.ViewCallback;

public interface ListViewCallback extends ViewCallback {
    void onItemClick(int position);
}
