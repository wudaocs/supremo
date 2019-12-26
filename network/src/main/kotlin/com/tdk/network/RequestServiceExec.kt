package com.tdk.network

import android.text.TextUtils
import androidx.annotation.NonNull
import com.td.network.BuildConfig
import com.td.utils.gson.JsonUtil
import com.tdk.network.entity.Replying
import com.tdk.network.entity.RequestEntity
import com.tdk.network.interfaces.RequestCallBack
import okhttp3.*
import okhttp3.Headers.Companion.toHeaders
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.security.SecureRandom
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager


/**
 * Description : 包含同步、异步网络请求类
 * Created by YW on 2019-12-26.
 * Email：1809267944@qq.com
 */
class RequestServiceExec {

    // 设置超时时间
    private val connectTimeout: Long = 15
    // 设置读取超时时间
    private val readTimeout: Long = 20
    // 设置写入超时时间
    private val writeTimeout: Long = 20

    companion object {
        // 网络请求异常错误码
        const val errorCode = -1
        val json = "application/json; charset=utf-8".toMediaTypeOrNull()
        private var mOkHttpClient: OkHttpClient? = null
        val instance: RequestServiceExec by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            RequestServiceExec()
        }
    }

    /**
     * 获取client
     */
    fun getHttpClient(): OkHttpClient? {
        if (mOkHttpClient == null) {
            val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
            // 忽略https证书
            val trustAllManager = TrustAllManager()
            mOkHttpClient =
                    okHttpClientBuilder.sslSocketFactory(
                            createTrustAllSSLFactory(trustAllManager)!!, trustAllManager
                    )
                            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                            .readTimeout(readTimeout, TimeUnit.SECONDS)
                            .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                            .addInterceptor(HttpLoggingInterceptor().apply {
                                level = when (BuildConfig.DEBUG) {
                                    true -> HttpLoggingInterceptor.Level.BODY
                                    false -> HttpLoggingInterceptor.Level.BASIC
                                }
                            })
                            .retryOnConnectionFailure(true)
                            .build()
        }
        return mOkHttpClient
    }

    private fun createTrustAllSSLFactory(trustAllManager: TrustAllManager): SSLSocketFactory? {
        var ssfFactory: SSLSocketFactory? = null
        try {
            ssfFactory = SSLContext.getInstance("SSL")?.let {
                it.init(null, arrayOf<TrustManager>(trustAllManager), SecureRandom())
                it.socketFactory
            }
        } catch (ignored: Exception) {
            ignored.printStackTrace()
        }
        return ssfFactory
    }

    /**
     * get 请求 异步
     */
    inline fun <reified T> getEnqueue(requestEntity: RequestEntity<T>) {
        try {
            if (!checkUrl(requestEntity)) {
                return
            }
            enqueue(
                    getBuilder(requestEntity.heads).url(
                            appendParams(requestEntity.url!!, requestEntity.params)
                    ).build(), requestEntity.httpCallBack
            )
        } catch (e: Exception) {
            e.stackTrace
        }
    }

    /**
     * post 请求 异步
     */
    inline fun <reified T> postFormEnqueue(requestEntity: RequestEntity<T>) {
        try {
            if (!checkUrl(requestEntity)) {
                return
            }
            enqueue(
                    getBuilder(requestEntity.heads).url(requestEntity.url!!).post(
                            getPostForm(requestEntity.params)
                    ).build(), requestEntity.httpCallBack
            )
        } catch (e: Exception) {
            e.stackTrace
        }
    }

    /**
     * post 请求 异步
     */
    inline fun <reified T> postJsonEnqueue(requestEntity: RequestEntity<T>) {
        try {
            if (!checkUrl(requestEntity)) {
                return
            }
            enqueue(
                    getBuilder(requestEntity.heads).url(requestEntity.url!!).post(
                            (requestEntity.content ?: "").toRequestBody(json)
                    ).build(), requestEntity.httpCallBack
            )
        } catch (e: Exception) {
            e.stackTrace
        }
    }

    /**
     * get 请求 同步
     */
    inline fun <reified T> getExec(requestEntity: RequestEntity<T>): Replying<T>? {
        if (!checkUrl(requestEntity)) {
            return Replying()
        }
        return execute(
                getBuilder(requestEntity.heads).url(
                        appendParams(requestEntity.url!!, requestEntity.params)
                ).build()
        )
    }

    /**
     * post 请求 同步
     */
    inline fun <reified T> postFormExec(requestEntity: RequestEntity<T>): Replying<T>? {
        if (!checkUrl(requestEntity)) {
            return Replying()
        }
        return execute(
                getBuilder(requestEntity.heads).url(requestEntity.url!!).post(
                        getPostForm(requestEntity.params)
                ).build()
        )
    }

    /**
     * post 请求 同步
     */
    inline fun <reified T> postJsonExec(requestEntity: RequestEntity<T>): Replying<T>? {
        if (!checkUrl(requestEntity)) {
            return Replying()
        }
        return execute(
                getBuilder(requestEntity.heads).url(requestEntity.url!!).post(
                        (requestEntity.content ?: "").toRequestBody(json)
                ).build()
        )
    }

    fun <T> checkUrl(requestEntity: RequestEntity<T>): Boolean {
        return !TextUtils.isEmpty(requestEntity.url)
    }

    /**
     * 同步网络请求
     */
    inline fun <reified T> execute(request: Request): Replying<T>? {
        val replying = Replying<T>()
        try {
            val response: Response = getHttpClient()!!.newCall(request).execute()
            replying.code = response.code
            if (response.code == 200) {
                val body = response.body?.string()
                body?.run {
                    RequestManager.mRequestCustomListener?.custom(0, body)
                    replying.data = JsonUtil.fromJson(this, T::class.java)
                }
            }
        } catch (e: Exception) {
            e.stackTrace
        }
        return replying
    }

    /**
     * 异步网络请求
     */
    inline fun <reified T> enqueue(request: Request, httpCallBack: RequestCallBack<T>?) {
        try {
            getHttpClient()!!.newCall(request).enqueue(object : Callback {
                override fun onFailure(@NonNull call: Call, @NonNull e: IOException) {
                    var data = "server not return message !"
                    if (call.request().body != null) {
                        data = e.message.toString()
                    }
                    httpCallBack?.onFail(errorCode, data)
                }

                @Throws(IOException::class)
                override fun onResponse(@NonNull call: Call, @NonNull response: Response) {
                    if (response.code == 200) {
                        val body = response.body
                        val data = body!!.string()
                        try {
                            val hasCustom = RequestManager.mRequestCustomListener?.custom(0, data)
                                    ?: false
                            if (!hasCustom) {
                                val content = JsonUtil.fromJson(data, T::class.java)
                                httpCallBack?.onSuccess(content)
                            } else {
                                httpCallBack?.cancel()
                            }
                        } catch (e: Exception) {
                            e.stackTrace
                            error(response, httpCallBack)
                        }
                    } else {
                        error(response, httpCallBack)
                    }
                }
            })
        } catch (e: Exception) {
            e.stackTrace
            httpCallBack?.onFail(errorCode, "request error")
        }
    }

    inline fun <reified T> error(response: Response, httpCallBack: RequestCallBack<T>?) {
        httpCallBack!!.onFail(response.code, response.message)
    }


    fun getBuilder(params: Map<String, String>?): Request.Builder {
        val builder = Request.Builder()
        params?.run {
            if (isNotEmpty()) {
                builder.headers(this.toHeaders())
            }
        }
        return builder
    }

    fun appendParams(url: String, params: HashMap<String, Any?>?): String {
        var sb = StringBuilder()
        sb.append(url).append("?")
        if (params != null && params.isNotEmpty()) {
            val keySet = params.entries
            try {
                for ((key) in keySet) {
                    val o = params[key]
                    if (o != null) {
                        sb.append(key).append("=")
                        sb.append(URLEncoder.encode(o.toString(), "utf-8"))
                        sb.append("&")
                    }
                }
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
            }
        }
        sb = sb.deleteCharAt(sb.length - 1)
        return sb.toString()
    }

    fun getPostForm(params: HashMap<String, Any?>?): FormBody {
        val builder = FormBody.Builder()
        params?.run {
            if (isNotEmpty()) {
                for (map in this.entries) {
                    builder.add(map.key, map.value.let {
                        if (it is String) {
                            it
                        } else if (it is Long || it is Double
                                || it is Int || it is Short
                                || it is Float || it is Byte) {
                            it.toString()
                        } else {
                            JsonUtil.toJson(it)
                        }
                    })
                }
            }
        }
        return builder.build()
    }
}