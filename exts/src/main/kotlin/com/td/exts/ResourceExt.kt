@file:Suppress("DEPRECATION", "unused")

package com.td.exts

import android.content.Context
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

/**
 * Description :
 * Created by Wang Yue on 2020-01-06.
 * Phone ：18610413765
 */

fun Context?.getColors(resourceId: Int): Int = this?.resources?.getColor(resourceId) ?: 0

fun <T : View> T?.textColor(resourceId: Int): T? = this?.apply {
    resourceId.notZero {
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

fun <T : View> T?.textExt(resourceId: Int) {
    this?.textExt(this.context.resources.getString(resourceId))
}

fun <T : View> T?.textExt(content: String?): T? = this?.apply {
    content.notNull {
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

fun <T : View> T?.wh(width: Int = ViewGroup.LayoutParams.MATCH_PARENT, height: Int = ViewGroup.LayoutParams.MATCH_PARENT): T? =
        this?.apply {
            layoutParams = ViewGroup.LayoutParams(dp(width).toInt(), dp(height).toInt())
        }

fun <T : View> T.dp(value: Int): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value.toFloat(), resources.displayMetrics)
}

fun <T : View> T?.bgr(resourceId: Int?): T? = this?.apply {
    resourceId.notZero {
        when (this) {
            is ImageView -> setBackgroundResource(resourceId!!)
            is TextView -> setBackgroundResource(resourceId!!)
            is Button -> setBackgroundResource(resourceId!!)
        }
    }
}

fun <T : View> T?.bgc(resourceId: Int): T? = this?.apply {
    when (this) {
        is ImageView -> setBackgroundColor(resources.getColor(resourceId))
        is TextView -> setBackgroundColor(resources.getColor(resourceId))
        is Button -> setBackgroundColor(resources.getColor(resourceId))
    }
}

fun TextView?.gravityExt(gravity: Int): TextView? = this?.apply {
    this.gravity = gravity
}