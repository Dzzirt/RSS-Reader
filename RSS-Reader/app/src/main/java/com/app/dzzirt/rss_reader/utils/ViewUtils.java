package com.app.dzzirt.rss_reader.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by Dzzirt on 08.01.2017.
 */

public class ViewUtils {
    public static int getSpanCount(Context context, int viewWidthDimension) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        float dimension = context.getResources().getDimension(viewWidthDimension);
        return (int) (dpWidth / dimension);
    }
}
