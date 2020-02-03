package com.td.utils

import android.content.Context

/**
 * Description : 
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
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