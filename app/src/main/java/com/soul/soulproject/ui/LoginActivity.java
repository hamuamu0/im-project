package com.soul.soulproject.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.soul.soulproject.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 类或接口的描述信息
 *
 * @Author:qubin
 * @Theme:
 * @Data:2020-10-12
 * @Describe:
 */
public class LoginActivity extends AppCompatActivity {

    EditText edtPhone;
    EditText edtCode;
    Button btnLogin;
    Button btnSendCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

    }

    private void initView() {
        edtPhone = findViewById(R.id.edt_phone);
        edtCode = findViewById(R.id.edt_code);
        btnLogin = findViewById(R.id.btn_login);
        btnSendCode = findViewById(R.id.btn_send_code);

    }

    private void onClick(View view){
        switch (view.getId()){
            case R.id.btn_login:

                break;

            case R.id.btn_send_code:

                break;

            default:
                break;
        }
    }
}
