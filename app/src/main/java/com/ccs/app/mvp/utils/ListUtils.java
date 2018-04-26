package com.ccs.app.mvp.utils;

import android.support.annotation.NonNull;

import java.util.List;

public class ListUtils {

    public static boolean isValidateIndex(@NonNull List list, int index) {
        return index >= 0 && index < list.size();
    }
}
