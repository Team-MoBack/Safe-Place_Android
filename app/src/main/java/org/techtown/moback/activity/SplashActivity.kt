package org.techtown.moback.activity

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_splash.*
import org.techtown.moback.R

class SplashActivity : AppCompatActivity() {

    private val TAG = "SplashActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

//권한 체크
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            //Toast.makeText(this, "권한 필요",Toast.LENGTH_SHORT).show();
            //거부 한적 있는 경우
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.ACCESS_FINE_LOCATION) && ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.ACCESS_COARSE_LOCATION)) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("권한 요청").setMessage("위치 권한을 사용하지 않으면 앱을 이용할 수 없습니다.")
                builder.setPositiveButton("확인") { dialogInterface: DialogInterface?, i: Int ->
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                            0)
                }.setNegativeButton("종료") { dialogInterface: DialogInterface?, i: Int -> finish() }.create().show()
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                        0)
            }
        }

        //로그인 액티비티
        signin_btn_splash.setOnClickListener { view: View? ->
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        //회원가입 액티비티
        signup_btn_splash.setOnClickListener { view: View? ->
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        //비밀번호 찾기
        forgot_btn_splash.setOnClickListener { view: View? -> }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            0 -> if (grantResults.size > 0) {
                //권한허용
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) Toast.makeText(this, "위치 권한이 허용되었습니다.", Toast.LENGTH_SHORT).show() else {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("권한 거절").setMessage("위치 권한을 사용하지 않으면 앱을 이용할 수 없습니다.")
                    builder.setPositiveButton("종료") { dialogInterface: DialogInterface?, i: Int -> finish() }.create().show()
                }
            }
        }
    }
}