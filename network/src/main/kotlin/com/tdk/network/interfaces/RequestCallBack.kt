package com.tdk.network.interfaces

import java.io.Serializable

/**
 * Description :  网络回调类
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
 */
interface RequestCallBack<T> : Serializable {

    fun onSuccess(data: T?)

    fun cancel()

    fun onFail(errorCode:Int, errorMessage: String)
}