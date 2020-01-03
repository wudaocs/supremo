package com.td.supremo.flutter

import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.td.base.KBaseActivity
import com.td.log.L
import io.flutter.facade.Flutter
import io.flutter.view.FlutterView

/**
 * Description :
 * Created by Wang Yue on 2019-12-27.
 * Phone ：18610413765
 */
class FunctionFlutterActivity : KBaseActivity() {

    private lateinit var flutterView : FlutterView

    override fun getContentView(): View? {
        return FrameLayout(this)
    }

    override fun creating() {
        super.creating()
        val flutterLayoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        )
        flutterLayoutParams.leftMargin = 0
        flutterLayoutParams.topMargin = 0
        flutterView = Flutter.createView(this, lifecycle, RouterPath.main)
        flutterView.enableTransparentBackground()
        window.decorView.post{
            addContentView(flutterView, flutterLayoutParams)
        }
    }

    override fun isHiddenNavigationBar(): Boolean = true

    override fun isHiddenTitle(): Boolean = true

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        L.e(content = "点击返回箭头")
        return super.onKeyDown(keyCode, event)
    }

}