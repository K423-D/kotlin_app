package com.example.kotlin_app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_normal.*

class NormalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_normal)

        normal_btn.setOnClickListener {
            Log.d("Normal Activity", "点击了 normal btn")
            finish()
        }
    }
}