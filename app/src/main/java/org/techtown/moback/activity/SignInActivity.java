package org.techtown.moback.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import org.techtown.moback.R;

public class SignInActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageButton backPress_btn;
    private Button signIn_btn;
    private ImageButton traililng_btn;

    private EditText id_edit;
    private EditText pw_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        id_edit = findViewById(R.id.id_edit_signin);
        pw_edit = findViewById(R.id.pw_edit_signin);

        backPress_btn = findViewById(R.id.backpress_signin);
        backPress_btn.setOnClickListener(view -> {
            finish();
        });

        signIn_btn = findViewById(R.id.signin_btn_signin);
        signIn_btn.setOnClickListener(view -> {

            //TODO : 로그인 과정
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        traililng_btn = findViewById(R.id.trailling_btn_signin);


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