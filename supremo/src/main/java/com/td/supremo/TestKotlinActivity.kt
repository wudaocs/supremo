package com.td.supremo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.td.supremo.TUtils.Companion.teststatic
import kotlinx.android.synthetic.main.activity_start.*

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
        setContentView(R.layout.activity_start)
        var test = findViewById<TextView>(R.id.tv_activity_start_prompt)

        val toolbar = findViewById(R.id.tv_activity_start_prompt) as TextView

        val bundle = this.intent.extras
        bundle.get("key").toString()
        bundle.get("name").toString()
    }

    override fun onStart() {
        super.onStart()
        start()
    }

    fun start() {
        tv_activity_start_prompt.text = ""
    }

    fun openActivty() {
        val intent = Intent(TestKotlinActivity@ this, StartActivity::class.java)
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
