package com.td.supremo.flutter

import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.td.base.KBaseActivity
import io.flutter.facade.Flutter

/**
 * Description :
 * Created by Wang Yue on 2019-12-27.
 * Phone ：18610413765
 */
class FunctionFlutterActivity : KBaseActivity() {

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
        val flutterView = Flutter.createView(this, lifecycle, RouterPath.main)
        flutterView.enableTransparentBackground()
        window.decorView.post{
            addContentView(flutterView, flutterLayoutParams)
        }
    }

    override fun isHiddenNavigationBar(): Boolean = true

    override fun isHiddenTitle(): Boolean = true
}