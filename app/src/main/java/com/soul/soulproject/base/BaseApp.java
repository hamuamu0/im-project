package com.soul.soulproject.base;

import android.app.Application;

import com.soul.framework.FrameWork;

/**
 * 类或接口的描述信息
 *
 * @Author:qubin
 * @Theme:
 * @Data:2020-10-26
 * @Describe:
 */
public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FrameWork.getFrameWork().initFrameWork(this);
    }
}
