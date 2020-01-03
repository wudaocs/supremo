package com.td.utils

import android.content.Context

/**
 * Description :
 * Created by Wang Yue on 2020-01-02.
 * Phone ：18610413765
 */
object ScreenUtil {

    var width : Int = 0
    var height : Int = 0

    fun init(context: Context){
        context.resources.displayMetrics.run {
            width = widthPixels
            height = heightPixels
        }
    }
}