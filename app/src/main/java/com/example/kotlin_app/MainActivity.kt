package com.example.kotlin_app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //  启动时调用
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 加载布局文件
        setContentView(R.layout.activity_main)
        // 直接用id作为变量，添加点击事件监听器。
        main_btn.setOnClickListener {
            // 设置Toast并使用
            //  Toast.makeText(this, "点击了 按钮", Toast.LENGTH_SHORT).show()

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
    }

    // 创建 menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //  加载 menu 资源文件
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //  通过 item.itemId 监听被点击的 item 并做出提示
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
}