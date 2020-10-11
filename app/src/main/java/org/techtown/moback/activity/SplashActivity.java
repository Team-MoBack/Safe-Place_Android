package org.techtown.moback.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.techtown.moback.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//권한 체크
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            //Toast.makeText(this, "권한 필요",Toast.LENGTH_SHORT).show();

            //거부 한적 있는 경우
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) && ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("권한 요청").setMessage("위치 권한을 사용하지 않으면 앱을 이용할 수 없습니다.");
                builder.setPositiveButton("확인", (dialogInterface, i) -> {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                            0);
                }).setNegativeButton("종료", (dialogInterface, i) -> {
                    finish();
                }).create().show();

            }
            //거부 한적 없는 경우
            else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        0);
            }
        }

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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case 0 :
                if(grantResults.length > 0)
                {
                    //권한허용
                    if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                        Toast.makeText(this,"위치 권한이 허용되었습니다.",Toast.LENGTH_SHORT).show();
                    else
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("권한 거절").setMessage("위치 권한을 사용하지 않으면 앱을 이용할 수 없습니다.");
                        builder.setPositiveButton("종료", (dialogInterface, i) -> {
                            finish();
                        }).create().show();
                    }
                }
                break;
        }
    }
}