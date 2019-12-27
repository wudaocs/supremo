package com.tdk.network

import com.tdk.network.entity.Replying
import com.tdk.network.entity.RequestEntity
import com.tdk.network.interfaces.RequestCustomListener
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Description : 网络请求proxy
 * Created by Wang Yue on 2019-09-24.
 * Phone ：18610413765
 */
object RequestManager {

    var mRequestCustomListener : RequestCustomListener? = null

    enum class TYPE {
        // get 请求类型
        GET,
        // post 请求类型 默认form
        POST,
        POST_JSON
    }

    // 异步执行请求网络数据
    inline fun <reified T> enqueue(type: TYPE, requestEntity: RequestEntity<T>) {
        when (type) {
            TYPE.GET -> RequestServiceExec.instance.getEnqueue(requestEntity)
            TYPE.POST -> RequestServiceExec.instance.postFormEnqueue(requestEntity)
            TYPE.POST_JSON -> RequestServiceExec.instance.postJsonEnqueue(requestEntity)
        }
    }

    // 同步执行
    inline fun <reified T> execute(type: TYPE, requestEntity: RequestEntity<T>): Replying<T>? {
        return when (type) {
            TYPE.GET -> RequestServiceExec.instance.getExec(requestEntity)
            TYPE.POST -> RequestServiceExec.instance.postFormExec(requestEntity)
            TYPE.POST_JSON -> RequestServiceExec.instance.postJsonExec(requestEntity)
        }
    }

    fun <T> retrofit(url: String, cls: Class<T>): T = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(cls)

    /**
     * 设置全局网络回调 处理统一逻辑
     */
    fun setRequestCustomDeal(requestCustomListener: RequestCustomListener){
        mRequestCustomListener = requestCustomListener
    }

}