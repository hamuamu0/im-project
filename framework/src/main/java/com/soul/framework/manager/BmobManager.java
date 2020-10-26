package com.soul.framework.manager;

import android.content.Context;

import cn.bmob.v3.Bmob;

/**
 * 类或接口的描述信息
 *
 * @Author:qubin
 * @Theme:
 * @Data:2020-10-26
 * @Describe: bmob管理类
 */
public class BmobManager {

    private static volatile BmobManager mInstance = null;

    private final String BMOB_SDK_ID = "ed266b9dad49d5bf34f74966260b7672";

    private BmobManager(){}

    public static BmobManager getInstance(){
        if (mInstance == null){
            synchronized (BmobManager.class){
                if (mInstance == null){
                    mInstance = new BmobManager();
                }
            }
        }
        return mInstance;
    }

    public void initBmob(Context context){
        Bmob.initialize(context,BMOB_SDK_ID);
    }
}
