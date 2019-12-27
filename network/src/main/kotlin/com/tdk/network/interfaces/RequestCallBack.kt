package com.tdk.network.interfaces

import java.io.Serializable

/**
 * Description : 网络回调类
 * Created by Wang Yue on 2019-10-12.
 * Phone ：18610413765
 * Email：wangyue@91sph.com
 */
interface RequestCallBack<T> : Serializable {

    fun onSuccess(data: T?)

    fun cancel()

    fun onFail(errorCode:Int, errorMessage: String)
}