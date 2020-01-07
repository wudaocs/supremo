package com.td.exts

import android.view.View

/**
 * Description :
 * Created by Wang Yue on 2020-01-06.
 * Phone ：18610413765
 */
const val ClickDelayTime: Long = 300

fun <T : View> T?.onClick(block: () -> Unit) {
    this?.setOnClickListener {
        isEnabled = false
        block()
        postDelayed({ isEnabled = true }, ClickDelayTime)
    }
}

