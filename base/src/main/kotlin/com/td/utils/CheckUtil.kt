@file:Suppress("DEPRECATION", "unused")

package com.td.utils

import android.text.TextUtils

/**
 * Description :
 * Created by Wang Yue on 2020-01-06.
 * Phone ：18610413765
 */
fun Int.zero(block: () -> Unit) {
    if (this == 0) {
        block()
    }
}

fun Int.notZero(block: () -> Unit) {
    if (this != 0) {
        block()
    }
}

fun String?.notNull(block: () -> Unit) {
    if (this != null && !TextUtils.isEmpty(this)) {
        block()
    }
}