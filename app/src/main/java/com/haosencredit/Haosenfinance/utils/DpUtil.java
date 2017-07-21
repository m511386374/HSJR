package com.haosencredit.Haosenfinance.utils;
import android.content.res.Resources;


/**
 * Created by Nick.Ming on 2017/6/28.
 */

public class DpUtil {

    public static int dip2px(int dip, Resources a) {
        float density = a.getDisplayMetrics().density;
        return (int) (dip * density + 0.5);
    }
}
