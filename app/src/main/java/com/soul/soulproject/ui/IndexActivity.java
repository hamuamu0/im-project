package com.soul.soulproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.soul.framework.entity.Contants;

import com.soul.framework.utils.SpUtils;
import com.soul.soulproject.MainActivity;
import com.soul.soulproject.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 类或接口的描述信息
 *
 * @Author:qubin
 * @Theme:
 * @Data:2020-10-12
 * @Describe:
 */
public class IndexActivity extends AppCompatActivity {

    private final int SKIP_CLASS = 1000;



    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            switch (message.what){
                case SKIP_CLASS :
                    startMain();
                    break;
            }
            return false;
        }
    });




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        handler.sendEmptyMessageDelayed(SKIP_CLASS,2000);
    }

    private void startMain() {
        boolean isFirstStart = SpUtils.getInstance(this).getBoolean(Contants.SP_IS_FIRST_APP, true);
        Intent intent = new Intent();


        if (isFirstStart){
            //判断是否是第一次启动
            intent.setClass(this,GuideActivity.class);
            SpUtils.getInstance(this).putBoolean(Contants.SP_IS_FIRST_APP,false);
        }else {
            //如果不是第一次登陆则判断是否是登陆过
            boolean isToken = SpUtils.getInstance(this).getBoolean(Contants.SP_TOKEN, true);
            if (isToken){
                intent.setClass(this, MainActivity.class);
            }else {
                intent.setClass(this,LoginActivity.class);
            }
        }
        startActivity(intent);

    }


}
