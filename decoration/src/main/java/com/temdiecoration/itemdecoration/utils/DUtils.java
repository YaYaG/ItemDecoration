package com.temdiecoration.itemdecoration.utils;

import android.content.Context;

/**
 * 姓名: 王进亚
 * 时间： 2018/9/22
 * 描述：
 */

public class DUtils {

    public static int dp2px(Context context,int dp){
        return (int) (context.getResources().getDisplayMetrics().density * dp + 0.5f);
    }

}
