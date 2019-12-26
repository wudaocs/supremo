package com.td.utils.gson

import android.text.TextUtils
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

/**
 * Description : json 转换工具类
 * Created by YW on 2019-12-26.
 * Email：1809267944@qq.com
 */
object JsonUtil {

    private var mGson: Gson? = Gson()

    private var mMapGson: Gson? = Gson()

    private fun getGson(): Gson? {
        if (mGson == null) mGson = Gson()
        return mGson
    }

    /**
     * 对象转换成String
     *
     * @param src 待转换的对象
     * @return json string
     */
    fun toJson(src: Any?): String {
        return if (src == null) {
            ""
        } else getGson()!!.toJson(src)
    }

    fun <T> fromJson(json: String, cls: Class<T>): T? {
        return try {
            if (TextUtils.isEmpty(json)) {
                null
            } else getGson()!!.fromJson(json, cls)
        } catch (e: JsonSyntaxException) {
            null
        }
    }

    /**
     * json string 转换成object
     * @param json 待转换的json string
     * @param typeOfT 需要转换的类型
     * @param <T> 需要转换的反省
     * @return 返回的转换结果
     */
    fun <T> fromJson(json: String, typeOfT: Type): T? {
        return try {
            if (TextUtils.isEmpty(json)) {
                null
            } else getGson()!!.fromJson<T>(json, typeOfT)
        } catch (e: JsonSyntaxException) {
            null
        }
    }

    fun <T> formJsonMap(json: String, typeOfT: Type): T? {
        if (mMapGson == null) {
            mMapGson = GsonBuilder().registerTypeAdapter(object : TypeToken<Map<String, Any>>() {
            }.type, MapTypeAdapter()).create()
        }
        return mMapGson!!.fromJson(json, typeOfT)
    }

    fun <T> fromJsonList(json: String, cls: Class<T>): List<T>? {
        try {
            return if (TextUtils.isEmpty(json)) {
                null
            } else {
                val list = ArrayList<T>()
                val array = JsonParser().parse(json).asJsonArray
                if (null != array && array.size() > 0) {
                    val iterator = array.iterator()
                    while (iterator.hasNext()) {
                        val elem = iterator.next() as JsonElement
                        list.add(getGson()!!.fromJson(elem, cls)!!)
                    }
                }
                list
            }
        } catch (e: JsonSyntaxException) {
            return null
        }
    }
}