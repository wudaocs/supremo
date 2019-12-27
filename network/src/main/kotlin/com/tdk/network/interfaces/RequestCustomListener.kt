package com.tdk.network.interfaces

/**
 * Description :
 * Created by Wang Yue on 2019-11-26.
 * Phone ：18610413765
 */
interface RequestCustomListener {

    fun custom(code: Int?, requestBody : String?) : Boolean
}