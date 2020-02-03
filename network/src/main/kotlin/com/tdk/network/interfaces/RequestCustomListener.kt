package com.tdk.network.interfaces

/**
 * Description :
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
 */
interface RequestCustomListener {

    fun custom(code: Int?, requestBody : String?) : Boolean
}