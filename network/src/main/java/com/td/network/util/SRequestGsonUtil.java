package com.td.network.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

/**
 * Description : 
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
 */
public class SRequestGsonUtil {

    private static Gson mGson = new Gson();

    private static Gson getGson() {
        return mGson == null ? (mGson = new Gson()) : mGson;
    }

    /**
     * transform src into json
     *
     * @param src input object what want convert to json
     * @return json string
     */
    public static String toJson(Object src) {
        if (src == null) {
            return "";
        }
        return getGson().toJson(src);
    }

    /**
     * This method deserializes the Json read from the specified reader into an object of the
     * specified class.
     *
     * @param json the string from which the object is to be deserialized
     * @param cls  the class of T
     * @param <T>  the type of the desired object
     * @return an object of type T from the string.
     */
    public static <T> T fromJson(String json, Class<T> cls) {
        try {
            if (TextUtils.isEmpty(json)) {
                return null;
            }
            return getGson().fromJson(json, cls);
        } catch (JsonSyntaxException e) {
            return null;
        }
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        try {
            if (TextUtils.isEmpty(json)) {
                return null;
            }
            return getGson().fromJson(json, typeOfT);
        } catch (JsonSyntaxException e) {
            return null;
        }
    }

}
