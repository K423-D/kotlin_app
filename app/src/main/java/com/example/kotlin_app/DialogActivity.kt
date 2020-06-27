package com.example.kotlin_app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_dialog.*

class DialogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)

        dialog_btn.setOnClickListener {
            Log.d("Dialog Activity", "点击了 dialog btn")
            finish()
        }
    }
}