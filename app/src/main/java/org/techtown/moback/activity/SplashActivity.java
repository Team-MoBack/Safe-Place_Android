package org.techtown.moback.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.techtown.moback.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //로그인 액티비티
        Button signIn = findViewById(R.id.signin_btn_splash);
        signIn.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
        });

        //회원가입 액티비티
        Button signUp = findViewById(R.id.signup_btn_splash);
        signUp.setOnClickListener(view -> {

            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        });

        //비밀번호 찾기
        TextView forget = findViewById(R.id.forgot_btn_splash);
        forget.setOnClickListener(view -> {
            //TODO : 비밀번호 찾기
        });
    }
}