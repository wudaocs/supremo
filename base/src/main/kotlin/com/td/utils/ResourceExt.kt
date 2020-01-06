@file:Suppress("DEPRECATION", "unused")

package com.td.utils

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

/**
 * Description :
 * Created by Wang Yue on 2020-01-06.
 * Phone ：18610413765
 */

fun Context?.getColors(resourceId: Int): Int = this?.resources?.getColor(resourceId) ?: 0

fun View?.textColor(resourceId: Int) {
    this?.run {
        when (this) {
            is TextView -> {
                setTextColor(this.context.resources.getColor(resourceId))
            }
            is Button -> {
                setTextColor(this.context.resources.getColor(resourceId))
            }
            is EditText -> {
                setTextColor(this.context.resources.getColor(resourceId))
            }
        }
    }
}

fun View?.textExt(resourceId: Int) {
    this?.textExt(this.context.resources.getString(resourceId))
}

fun View?.textExt(content: String?) {
    this?.run {
        when (this) {
            is TextView -> {
                text = content
            }
            is Button -> {
                text = content
            }
            is EditText -> {
                setText(content)
            }
        }
    }
}