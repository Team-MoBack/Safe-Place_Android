package org.techtown.moback.activity

import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.techtown.moback.MyApplication
import org.techtown.moback.R
import org.techtown.moback.server.ServerLibrary

class SignInActivity : AppCompatActivity() {

    private val TAG = "SignInActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        backpress_signin.setOnClickListener({ view: View? -> finish() })

        var application = application as MyApplication

        signin_btn_signin.setOnClickListener( { view: View? ->

            //TODO : 로그인 과정
            CoroutineScope(Dispatchers.Main).launch {

                async (Dispatchers.Default) {
                    return@async ServerLibrary.login(id_edit_signin.text.toString(), pw_edit_signin.text.toString())
                }.await()?.let {
                    application.token = it

                    Log.d(TAG, "token : $it")

                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }?:let {
                    Toast.makeText(applicationContext, "로그인 실패", Toast.LENGTH_SHORT).show()
                }
            }

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