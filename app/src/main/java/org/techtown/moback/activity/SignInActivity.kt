package org.techtown.moback.activity

import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.techtown.moback.R

class SignInActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        backpress_signin.setOnClickListener({ view: View? -> finish() })

        signin_btn_signin.setOnClickListener( { view: View? ->

            //TODO : 로그인 과정
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        })

        setTrailingMode()
    }

    //비밀번호 숨김, 해제
    private fun setTrailingMode() {
        trailing_btn_signin.setOnClickListener { view: View? ->

            //비공개 -> 공개
            if (pw_edit_signin.inputType == 129) {
                val position = pw_edit_signin.selectionEnd
                pw_edit_signin.inputType = 131073
                pw_edit_signin.setSelection(position)
                trailing_btn_signin.setImageDrawable(getDrawable(R.drawable.trailing_icon))
            }
            else
            {
                val position = pw_edit_signin.selectionEnd
                pw_edit_signin.inputType = 129
                pw_edit_signin.transformationMethod = PasswordTransformationMethod.getInstance()
                pw_edit_signin.setSelection(position)
                trailing_btn_signin.setImageDrawable(getDrawable(R.drawable.untrailing_icon))
            }
        }
    }
}