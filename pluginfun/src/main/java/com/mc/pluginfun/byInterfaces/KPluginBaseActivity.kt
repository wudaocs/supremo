package com.mc.pluginfun.byInterfaces

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import com.td.base.KBaseActivity
import com.td.exts.whatCanDo

/**
 * 插件页面基类
 */
abstract class KPluginBaseActivity : KBaseActivity(), IPluginRelatedListener {

    private var mActivity: Activity? = null

    override fun attach(activity: Activity) {
        mActivity = activity
    }

    // 加载页面显示

    override fun setContentView(layoutResID: Int) {
        mActivity.whatCanDo(
                { super.setContentView(layoutResID) },
                { (it as Activity).setContentView(layoutResID) }
        )
    }

    override fun setContentView(view: View?) {
        mActivity.whatCanDo(
                { super.setContentView(view) },
                { (it as Activity).setContentView(view) }
        )
    }

    override fun startActivity(intent: Intent?) {
        val i = Intent()
        intent?.run {
            i.putExtras(this)
        }
        mActivity?.startActivity(intent)
    }


    // 捆绑生命周期

    override fun onPCreate(saveInstanceState: Bundle?) {
        onCreate(saveInstanceState)
    }

    override fun onPStart() {
        onStart()
    }

    override fun onPResume() {
        onResume()
    }

    override fun onPPause() {
        onPause()
    }

    override fun onPStop() {
        onStop()
    }

    override fun onPDestroy() {
        onDestroy()
    }

    override fun onPSaveInstanceState(outState: Bundle?) {
        outState?.run {
            onSaveInstanceState(this)
        }
    }

    override fun onPTouchEvent(event: MotionEvent?): Boolean {
        return onTouchEvent(event)
    }

    override fun onPBackPressed() {
        onBackPressed()
    }


}