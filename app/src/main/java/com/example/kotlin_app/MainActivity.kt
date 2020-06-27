package com.example.kotlin_app

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val tag = "Main Activity"
    private var isCarrol = true

    /**
     *  Activity 第一次创建的时候调用，通常进行初始化操作
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 加载布局文件
        setContentView(R.layout.activity_main)

        Log.d(tag, "on creat")

        // 调用添加监听器的函数，传入需要设置监听器的控件
        setViewClick(this, mainBtn, main_btn1, startNormalActivity, startDialogActivity)

        // 获取保存的数据
        if (savedInstanceState != null) {
            val tempData = savedInstanceState.getString("data_key")
            Toast.makeText(this, "保存的tempData：$tempData", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     *  批量添加点击监听器
     */
    private fun Context.setViewClick(listener: View.OnClickListener, vararg views: View) {
        for (it in views) {
            it.setOnClickListener(listener)
        }
    }

    /**
     *  通过实现接口的形式注册监听器
     */
    override fun onClick(v: View?) {
        when (v?.id) {
            // 普通启动 Activity
            R.id.startNormalActivity -> {
                val intent = Intent(this, NormalActivity::class.java)
                startActivity(intent)
            }

            // 启动 Dialog Activity
            R.id.startDialogActivity -> {
                val intent = Intent(this, DialogActivity::class.java)
                startActivity(intent)
            }

            // 各种 intent 的启动方式
            R.id.mainBtn -> {
                // 设置Toast并使用
                //Toast.makeText(this, "点击了 main activity 按钮", Toast.LENGTH_SHORT).show()

                //  销毁 Activity
                //  finish()

                //  启动 intent ，前往另一个 activity

                //  显示启动(没有返回的数据)
                /*startActivity(
                    Intent(this, FirstActivity::class.java)
                        //  键值对形式传递数据给目标 activity
                        .putExtra(
                            "msg",
                            "hello first activity"
                        )
                )*/

                //  显示启动(有返回数据)
                startActivityForResult(
                    Intent(this, FirstActivity::class.java)
                        .putExtra(
                            "msg", "hello first activity"
                        ), 1
                )

                //  隐式启动
                //  一个 intent 只能指定一个 action，但是可以指定多个 category
                //  但要注意在 AndroidManifest 中配置对应的 category
                //  startActivity(Intent("com.example.kotlin_app.ACTION_START").addCategory("com.example.kotlin_app.K_CATEGORY"))

                //  使用 intent 打开百度
                //  val intent = Intent(Intent.ACTION_VIEW)
                //  注意要在 AndroidManifest 中配置对应的 data 标签
                //  intent.data = Uri.parse("https://www.baidu.com")
                //  startActivity(intent)

                //  使用 intent 打开拨号页面
                //  val intent = Intent(Intent.ACTION_DIAL)
                //  intent.data = Uri.parse("tel:10086")
                //  startActivity(intent)
            }


            //  btn1逻辑
            R.id.main_btn1 -> {
                if (isCarrol) {
                    imageView.setImageResource(R.drawable.seele)
                    isCarrol = false
                } else if (!isCarrol) {
                    imageView.setImageResource(R.drawable.carroll)
                    isCarrol = true
                }
            }
        }
    }


    /**
     *  Activity 从不可见到可见时调用
     */
    override fun onStart() {
        super.onStart()
        Log.d(tag, "on start")
    }

    /**
     *  Activity 准备好和用户交互时调用，此时 Activity 一定会处于栈顶而且处于运行状态
     */
    override fun onResume() {
        super.onResume()
        Log.d(tag, "on resume")
    }

    /**
     *  系统准备去启动或者恢复另一个 Activity 时调用，通常用于释放一些 cpu 资源，保存一些关键数据。
     *  但是执行速度一定要快，否则影响新 Activity 的使用
     */
    override fun onPause() {
        super.onPause()
        Log.d(tag, "on pause")
    }

    /**
     *  Activity 完全不可见时调用，区别如下：若启动的新 Activity 是一个对话框式的 Activity，
     *  那么 onPause（）方法会执行，而 onStop（）方法不会执行
     */
    override fun onStop() {
        super.onStop()
        Log.d(tag, "on stop")
    }

    /**
     *  Activity 在被销毁之前调用，之后 Activity 的状态变成销毁状态，完成对内存的释放操作
     */
    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "on destroy")
    }

    /**
     *  Activity 由静止状态->运行状态之前调用，即 Activity 重新启动
     */
    override fun onRestart() {
        super.onRestart()
        Log.d(tag, "on restart")
    }

    /**
     * 创建 menu
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //  加载 menu 资源文件
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    /**
     * 通过 item.itemId 监听被点击的 item 并做出相应动作
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // 类似 switch
        when (item.itemId) {
            R.id.add_item ->
                Toast.makeText(this, "点击了 Add", Toast.LENGTH_SHORT).show()
            R.id.remove_item ->
                Toast.makeText(this, "点击了 Remove", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * 使用 startActivityForResult 启动的 Activity 被销毁之后
     * 会回调上一个 Activity 的 onActivityResult
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 -> if (resultCode == Activity.RESULT_OK) {
                //  获取返回数据
                val msgReturn = data?.getStringExtra("msg_return")
                Toast.makeText(this, "返回的 msg_return：\r\n $msgReturn", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     *  Activity 进入停止状态，被回收之前保存一些希望保存的数据
     *  在 Activity 被重新创建的时候可以从新使用保存的数据
     */
    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        Log.d(tag, "save state : onCreate")
        val tempData = "刚刚输入的一些数据"
        outState.putString("data_key", tempData)
    }
}