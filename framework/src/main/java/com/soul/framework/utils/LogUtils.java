package com.soul.framework.utils;

import android.text.TextUtils;
import android.util.Log;

import com.soul.framework.BuildConfig;

/**
 * 类或接口的描述信息
 *
 * @Author:qubin
 * @Theme:
 * @Data:2020-10-09
 * @Describe: Log打印日志工具类
 */
public class LogUtils {

    private static final String TAG = "LogUtils";

    /**
     * 打印正常log
     * @param text
     */
    public static void i(String text){
        if (BuildConfig.DEBUG){
            if (!TextUtils.isEmpty(text)){
                Log.i(TAG,text);
            }
        }
    }


    /**
     * 打印错误的log
     * @param text
     */
    public static void e(String text){
        if (BuildConfig.DEBUG){
            if (!TextUtils.isEmpty(text)){
                Log.e(TAG,text);
            }
        }
    }
}
