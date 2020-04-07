package com.mc.pluginfun.byInterfaces

import android.app.Activity
import android.os.Bundle
import android.view.MotionEvent

/**
 * 插件与宿主关联接口
 */
public interface IPluginRelatedListener {

    fun attach(activity : Activity)

    /**
     * 生命周期
     */
    fun onPCreate(saveInstanceState: Bundle?)
    fun onPStart()
    fun onPResume()
    fun onPPause()
    fun onPStop()
    fun onPDestroy()
    fun onPSaveInstanceState(outState: Bundle?)
    fun onPTouchEvent(event: MotionEvent?): Boolean
    fun onPBackPressed()

}