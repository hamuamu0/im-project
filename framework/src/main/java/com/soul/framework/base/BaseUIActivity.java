package com.soul.framework.base;

import android.os.Bundle;

import com.soul.framework.utils.SystemUI;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 类或接口的描述信息
 *
 * @Author:qubin
 * @Theme:
 * @Data:2020-10-09
 * @Describe: 沉浸式状态栏UI
 */
public class BaseUIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemUI.fixSystemUI(this);
    }
}
