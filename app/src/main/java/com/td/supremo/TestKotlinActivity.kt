package com.td.supremo

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * Description : 测试kotlin功能页面
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
 */
class TestKotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val bundle = this.intent.extras
        bundle?.get("key").toString()
        bundle?.get("name").toString()
    }

    override fun onStart() {
        super.onStart()
        start()
    }

    private fun start() {
    }

    fun openActivty() {
        val intent = Intent(this, StartActivity::class.java)
        val bundle = Bundle()
        bundle.putString("name", "kotlin")
        bundle.putInt("sex", 1)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
