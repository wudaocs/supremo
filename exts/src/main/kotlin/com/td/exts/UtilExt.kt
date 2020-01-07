@file:Suppress("unused")

package com.td.exts

import android.app.Activity
import android.widget.Toast

/**
 * Description :
 * Created by Wang Yue on 2020-01-07.
 * Phone ：18610413765
 */
private var toast: Toast? = null

fun Activity.toast(message: CharSequence) {
    toast?.cancel()
    toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
            .apply { show() }
}