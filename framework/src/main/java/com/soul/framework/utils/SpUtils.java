package com.soul.framework.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;


/**
 * FileName: SpUtils
 * Founder: LiuGuiLin
 * Profile:
 */
public class SpUtils {

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private final String SP_NAME = "SP_NAME";

    /**
     * key - values 存储方式
     * 它的存储路径：data/data/packageName/shared_prefs/sp_name.xml
     * <p>
     * File存储：/sdcard/ 读写方式不一样
     * 数据库：LitePal
     * get/post:数据的读写
     */

    private volatile static SpUtils mInstance = null;

    private SpUtils(Context context) {

        /**
         * MODE_PRIVATE：只限于本应用读写
         * MODE_WORLD_READABLE:支持其他应用读，但是不能写
         * MODE_WORLD_WRITEABLE:其他应用可以写
         */
        sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();

    }

    public static SpUtils getInstance(Context context) {
        if (mInstance == null) {
            synchronized (SpUtils.class) {
                if (mInstance == null) {
                    mInstance = new SpUtils(context);
                }
            }
        }
        return mInstance;
    }

    public void initSp(Context mContext) {


    }

    public void putInt(String key, int values) {
        editor.putInt(key, values);
        editor.commit();
    }

    public int getInt(String key, int defValues) {
        return sp.getInt(key, defValues);
    }

    public void putString(String key, String values) {
        editor.putString(key, values);
        editor.commit();
    }

    public String getString(String key, String defValues) {
        return sp.getString(key, defValues);
    }

    public void putBoolean(String key, boolean values) {
        editor.putBoolean(key, values);
        editor.commit();
    }

    public boolean getBoolean(String key, boolean defValues) {
        return sp.getBoolean(key, defValues);
    }

    public void deleteKey(String key) {
        editor.remove(key);
        editor.commit();
    }

}
