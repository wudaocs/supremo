package com.td.exts

import android.view.View

/**
 * Description : 
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
 */
const val ClickDelayTime: Long = 300

fun <T : View> T?.onClick(block: () -> Unit) {
    this?.setOnClickListener {
        isEnabled = false
        block()
        postDelayed({ isEnabled = true }, ClickDelayTime)
    }
}

