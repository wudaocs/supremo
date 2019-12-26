package com.tdk.network.entity

import com.tdk.network.interfaces.RequestCallBack
import java.io.Serializable
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.HashMap

/**
 * Description : 网络请求参数类
 * Created by Wang Yue on 2019-10-12.
 * Phone ：18610413765
 * Email：wangyue@91sph.com
 */
open class RequestEntity<T> : Serializable {

    private val sphAppKeySign = "123"

    var url: String? = null

    var heads: HashMap<String, String> = HashMap()

    // get| post_form 请求参数
    var params: HashMap<String, Any?> = HashMap()

    // post_json 请求参数
    var content: String? = null

    var httpCallBack: RequestCallBack<T>? = null

    var type: Type? = null

    init {
        setCommonHeaders()
    }

    fun addHeader(key: String, value: String): RequestEntity<T> {
        heads.run {
            put(key, value)
        }
        return this
    }

    fun addParams(key: String, value: Any?): RequestEntity<T> {
        params.run {
            put(key, value)
        }
        return this
    }

    fun addParamsNonNull(key: String, value: Any?): RequestEntity<T> {
        value?.run {
            params.run {
                put(key, value)
            }
        }
        return this
    }

    fun addGetSignParams(time: Long = System.currentTimeMillis()): HashMap<String, Any?> =
            params.apply {
                params.apply {
                    if (this.containsKey("timestamp")){
                        remove("timestamp")
                        remove("sign")
                    }
                    params["timestamp"] = (time).toString()
                    val sign = StringBuilder()
                    if (params.isNotEmpty()) {
                        val mapKeys: MutableList<String> = ArrayList(params.keys)
                        mapKeys.sort()
                        for (key in mapKeys) {
                            if (params[key] != null) {
                                sign.append(params[key])
                            }
                        }
                    }
                    sign.append(sphAppKeySign)
                    put("sign", com.td.utils.secret.EncryptUtil.EncoderByMd5(sign.toString()))
                }
            }

    fun addPostFormSignContent(time: Long = System.currentTimeMillis()) {
        params.apply {
            if (this.containsKey("timestamp")){
                remove("timestamp")
                remove("sign")
            }
            params["timestamp"] = (time).toString()
            val sign = StringBuilder()
            if (params.isNotEmpty()) {
                val mapKeys: MutableList<String> = ArrayList(params.keys)
                mapKeys.sort()
                for (key in mapKeys) {
                    if (params[key] != null) {
                        sign.append(params[key])
                    }
                }
            }
            sign.append(sphAppKeySign)
            put("sign", com.td.utils.secret.EncryptUtil.EncoderByMd5(sign.toString()).toLowerCase())

        }
    }

    private fun setCommonHeaders() {
    }


}