package com.td.utils.gson

import com.google.gson.TypeAdapter
import com.google.gson.internal.LinkedTreeMap
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.util.*

/**
 * Description : gson 转换 map 类型
 * Created by YW on 2019-12-26.
 * Email：1809267944@qq.com
 */
class MapTypeAdapter : TypeAdapter<Any>() {

    override fun read(jsonReader: JsonReader): Any? {
        when (jsonReader.peek()) {
            JsonToken.BEGIN_ARRAY -> {
                val list = ArrayList<Any>()
                jsonReader.beginArray()
                while (jsonReader.hasNext()) {
                    list.add(read(jsonReader)!!)
                }
                jsonReader.endArray()
                return list
            }
            JsonToken.BEGIN_OBJECT -> {
                val map = LinkedTreeMap<String, Any>()
                jsonReader.beginObject()
                while (jsonReader.hasNext()) {
                    map[jsonReader.nextName()] = read(jsonReader)
                }
                jsonReader.endObject()
                return map
            }

            JsonToken.STRING -> return jsonReader.nextString()

            JsonToken.NUMBER -> {
                /**
                 * 改写数字的处理逻辑，将数字值分为整型与浮点型。
                 */
                val dbNum = jsonReader.nextDouble()

                // 数字超过long的最大值，返回浮点类型
                return if (dbNum > java.lang.Long.MAX_VALUE) {
                    dbNum
                } else dbNum.toInt()
            }
            JsonToken.BOOLEAN -> return jsonReader.nextBoolean()

            JsonToken.NULL -> {
                jsonReader.nextNull()
                return null
            }
            else -> {
                throw IllegalStateException()
            }
        }
    }

    override fun write(out: JsonWriter, value: Any) {
        // 序列化无需实现
    }

}