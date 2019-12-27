package com.td.supremo.flutter

import android.view.View
import com.td.base.KBaseActivity
import io.flutter.facade.Flutter

/**
 * Description :
 * Created by Wang Yue on 2019-12-27.
 * Phone ：18610413765
 */
class FunctionFlutterActivity : KBaseActivity() {

    override fun getContentView(): View? {
        return Flutter.createView(this, lifecycle, RouterPath.main)
    }
}