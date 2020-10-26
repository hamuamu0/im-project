package com.soul.framework;

import android.content.Context;

import com.soul.framework.manager.BmobManager;

/**
 * 类或接口的描述信息
 *
 * @Author:qubin
 * @Theme:
 * @Data:2020-10-09
 * @Describe:
 */
public class FrameWork {

    private volatile static FrameWork mFrameWork = null;

    private FrameWork(){}

    public static FrameWork getFrameWork(){
        if (mFrameWork == null){
            synchronized (FrameWork.class){
                if (mFrameWork == null){
                    mFrameWork = new FrameWork();
                }
            }
        }
        return mFrameWork;
    }

    public void initFrameWork(Context mContext){

        BmobManager.getInstance().initBmob(mContext);
    }
}
