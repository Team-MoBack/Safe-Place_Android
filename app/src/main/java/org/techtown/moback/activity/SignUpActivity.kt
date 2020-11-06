package org.techtown.moback.activity

import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.techtown.moback.R
import org.techtown.moback.server.ServerLibrary

class SignUpActivity : AppCompatActivity() {

    private val TAG = "SignUpActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        backpress_signup.setOnClickListener({ view: View? -> finish() })
        signup_btn_signup.setOnClickListener({ view: View? ->

            //회원가입과정
            CoroutineScope(Dispatchers.Default).launch {

                var result = ServerLibrary.registerUser(id_edit_signup.text.toString(), "Test","test", pw_edit_signup.text.toString())

                async(Dispatchers.Main) {
                    if(result)
                    {
                        Toast.makeText(applicationContext, "회원가입 성공", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    else
                        Toast.makeText(applicationContext, "회원가입 실패", Toast.LENGTH_SHORT).show()
                }
            }
        })
        setTrailingMode()
    }

    //비밀번호 숨김, 해제
    private fun setTrailingMode() {
        trailing_btn_signup.setOnClickListener { view: View? ->

            //비공개 -> 공개
            if (pw_edit_signup!!.inputType == 129) {
                val position = pw_edit_signup.selectionEnd
                pw_edit_signup.inputType = 131073
                pw_edit_signup.setSelection(position)
                trailing_btn_signup.setImageDrawable(getDrawable(R.drawable.trailing_icon))
            } else {
                val position = pw_edit_signup.selectionEnd
                pw_edit_signup.inputType = 129
                pw_edit_signup.transformationMethod = PasswordTransformationMethod.getInstance()
                pw_edit_signup.setSelection(position)
                trailing_btn_signup.setImageDrawable(getDrawable(R.drawable.untrailing_icon))
            }
        }
    }
}