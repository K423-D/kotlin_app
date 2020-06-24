package com.example.kotlin_app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_first.*

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        //  获取上个 intent 传递过来的数据
        val msg = intent.getStringExtra("msg")

        Toast.makeText(this, "传过来的 msg：\r\n$msg", Toast.LENGTH_SHORT).show()

        first_btn1.setOnClickListener {
            //  Log.d("FirstActivity", "传过来的 msg：$msg")

            //  返回数据给上一个 activity
            setResult(
                Activity.RESULT_OK, Intent().putExtra(
                    "msg_return", "hello main activity"
                )
            )
            finish()
        }
    }

    override fun onBackPressed() {
        //  不需要调用 super.onBackPressed()
        setResult(
            Activity.RESULT_OK, Intent().putExtra(
                "msg_return",
                "hello main activity(from back pressed)"
            )
        )
        finish()
    }
}