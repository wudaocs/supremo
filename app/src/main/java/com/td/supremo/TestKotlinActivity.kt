package com.td.supremo

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * Description :
 * Created by Wang Yue on 2018/10/17.
 * Job number：135033
 * Phone ：18610413765
 * Email：wangyue@syswin.com
 * Person in charge :Wang Yue
 * Leader：Ding Lei
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
