@file:Suppress("unused")

package com.td.exts

import android.app.Activity
import android.widget.Toast

/**
 * Description :
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
 */
private var toast: Toast? = null

fun Activity.toast(message: CharSequence) {
    toast?.cancel()
    toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
            .apply { show() }
}
