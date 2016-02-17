package com.zhouchangping.library4zcp.Utils;

import android.content.Context;
import android.content.res.Resources;


public class CommonUtils {

    public static int dipToPx(Context context, float dip) {
        if (context == null) {
            return (int) dip;
        }
        Resources res = context.getResources();
        if (res != null) {
            final float scale = res.getDisplayMetrics().density;
            int px = (int) (dip * scale + 0.5f);
            return px;
        }
        return (int) dip;
    }

    public static float px2dp(Context context, int px) {
        if (context == null) {
            return px;
        }
        Resources res = context.getResources();
        if (res == null) {
            return px;
        }
        final float scale = res.getDisplayMetrics().density;
        float dp = (px - 0.5f) / scale;
        return dp;
    }


}
