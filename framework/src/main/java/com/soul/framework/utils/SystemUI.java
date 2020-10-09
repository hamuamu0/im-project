package com.soul.framework.utils;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;

/**
 * 类或接口的描述信息
 *
 * @Author:qubin
 * @Theme:
 * @Data:2020-10-09
 * @Describe:
 */
public class SystemUI {

    /**
     * 沉浸栏UI类
     * @param activity
     */
    public static void fixSystemUI(Activity activity) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
