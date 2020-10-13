package com.soul.soulproject.test;

import android.os.Bundle;
import android.widget.Toast;

import com.soul.framework.view.TouchPictureView;
import com.soul.soulproject.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 类或接口的描述信息
 *
 * @Author:qubin
 * @Theme:
 * @Data:2020-10-13
 * @Describe:
 */
public class TestActivity extends AppCompatActivity {

    TouchPictureView touchPictureView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        touchPictureView = findViewById(R.id.touch_picture);
        touchPictureView.setOnViewResultLinstener(new TouchPictureView.OnViewResultLinstener() {
            @Override
            public void onResult() {
                Toast.makeText(TestActivity.this, "成功！", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
