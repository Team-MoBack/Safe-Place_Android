package org.techtown.moback.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import org.techtown.moback.R;

public class SignUpActivity extends AppCompatActivity {

    private ImageButton backPress_btn;
    private Button signUp_btn;
    private ImageButton traililng_btn;

    private EditText id_edit;
    private EditText pw_edit;
    private EditText email_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        id_edit = findViewById(R.id.id_edit_signup);
        pw_edit = findViewById(R.id.pw_edit_signup);
        email_edit = findViewById(R.id.email_edit_signup);

        backPress_btn = findViewById(R.id.backpress_signup);
        backPress_btn.setOnClickListener(view -> {
            finish();
        });

        signUp_btn = findViewById(R.id.signup_btn_signup);
        signUp_btn.setOnClickListener(view -> {

            //TODO : 회원가입과정
            finish();
        });

        traililng_btn = findViewById(R.id.trailling_btn_signup);


        setTrailingMode();
    }

    //비밀번호 숨김, 해제
    private void setTrailingMode()
    {
        traililng_btn.setOnClickListener(view -> {

            //비공개 -> 공개
            if(pw_edit.getInputType() == 129)
            {
                int position = pw_edit.getSelectionEnd();
                pw_edit.setInputType(131073);
                pw_edit.setSelection(position);
                traililng_btn.setImageDrawable(getDrawable(R.drawable.trailing_icon));
            }
            //공개 -> 비공개
            else
            {
                int position = pw_edit.getSelectionEnd();
                pw_edit.setInputType(129);
                pw_edit.setTransformationMethod(PasswordTransformationMethod.getInstance());
                pw_edit.setSelection(position);
                traililng_btn.setImageDrawable(getDrawable(R.drawable.untrailing_icon));
            }
        });
    }
}