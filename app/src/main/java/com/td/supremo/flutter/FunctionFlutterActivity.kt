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
    }

    override fun onResume() {
        super.onResume()
        val flutterLayoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        )
        flutterLayoutParams.leftMargin = 0
        flutterLayoutParams.topMargin = 0
        val flutterView = Flutter.createView(this, lifecycle, RouterPath.main)
        flutterView.enableTransparentBackground()
        addContentView(flutterView, flutterLayoutParams)
    }

    override fun isHiddenNavigationBar(): Boolean = false

    override fun isHiddenTitle(): Boolean = true
}