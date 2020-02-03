@file:Suppress("DEPRECATION", "unused")

package com.td.exts

import android.text.TextUtils

/**
 * Description : 
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
 */
fun Int?.zero(block: () -> Unit) {
    if (this != null && this == 0) {
        block()
    }
}

fun Int?.notZero(block: () -> Unit) {
    if (this != null && this != 0) {
        block()
    }
}

fun String?.notNull(block: () -> Unit) {
    if (this != null && !TextUtils.isEmpty(this)) {
        block()
    }
}

fun <T : Any> T?.notNull(block: () -> Unit){
    if (this != null) {
        block()
    }
}