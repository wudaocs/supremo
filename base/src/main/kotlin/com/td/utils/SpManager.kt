package com.td.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.collection.SimpleArrayMap
import com.td.utils.gson.JsonUtil

/**
 * Description :
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
 */
@Suppress("unused")
class SpManager(keyName: String = "uspName") {

    private var sp: SharedPreferences? = null
    private var spEditor: SharedPreferences.Editor? = null

    companion object {
        private val SP_LIST: SimpleArrayMap<String, SpManager> = SimpleArrayMap()

        fun getInstance(keyName: String = "uspName"): SpManager {
            if (SP_LIST.isEmpty || SP_LIST[keyName] == null) {
                SP_LIST.put(keyName, SpManager(keyName))
            }
            return SP_LIST[keyName]!!
        }
    }

    init {
        sp = AppManager.getContext()?.getSharedPreferences(keyName, Context.MODE_PRIVATE)
    }

    /**  -------------------- string 处理 ---------------------------------**/
    /**
     * Put the string value in sp.
     * @param key   The key of sp.
     * @param value The value of sp.
     */
    fun put(key: String, value: String) {
        put(key, value, false)
    }

    /**
     * Return the string value in sp.
     * @param key The key of sp.
     * @return the string value if sp exists or `""` otherwise
     */
    fun getString(key: String): String {
        return getString(key, "")
    }

    /**
     * Return the string value in sp.
     * @param key          The key of sp.
     * @param defaultValue The default value if the sp doesn't exist.
     * @return the string value if sp exists or `defaultValue` otherwise
     */
    @Suppress("NOTHING_TO_LINE")
    fun getString(key: String, defaultValue: String): String {
        return get(key, defaultValue)
    }

    /**  -------------------- int 处理 ---------------------------------**/
    /**
     * Put the int value in sp.
     *
     * @param key   The key of sp.
     * @param value The value of sp.
     */
    fun put(key: String, value: Int) {
        put(key, value, false)
    }

    /**
     * Return the int value in sp.
     * @param key The key of sp.
     * @return the int value if sp exists or `-1` otherwise
     */
    fun getInt(key: String): Int {
        return getInt(key, -1)
    }

    /**
     * Return the int value in sp.
     * @param key          The key of sp.
     * @param defaultValue The default value if the sp doesn't exist.
     * @return the int value if sp exists or `defaultValue` otherwise
     */
    @Suppress("NOTHING_TO_LINE")
    fun getInt(key: String, defaultValue: Int): Int {
        return get(key, defaultValue)
    }

    /**  -------------------- Long 处理 ---------------------------------**/
    /**
     * Put the long value in sp.
     * @param key   The key of sp.
     * @param value The value of sp.
     */
    fun put(key: String, value: Long) {
        put(key, value, false)
    }

    /**
     * Return the long value in sp.
     * @param key The key of sp.
     * @return the long value if sp exists or `-1` otherwise
     */
    fun getLong(key: String): Long {
        return getLong(key, -1L)
    }

    /**
     * Return the long value in sp.
     * @param key          The key of sp.
     * @param defaultValue The default value if the sp doesn't exist.
     * @return the long value if sp exists or `defaultValue` otherwise
     */
    @Suppress("NOTHING_TO_LINE")
    fun getLong(key: String, defaultValue: Long): Long {
        return get(key, defaultValue)
    }

    /**  -------------------- Float 处理 ---------------------------------**/
    /**
     * Put the float value in sp.
     * @param key   The key of sp.
     * @param value The value of sp.
     */
    fun put(key: String, value: Float) {
        put(key, value, false)
    }

    /**
     * Return the float value in sp.
     * @param key The key of sp.
     * @return the float value if sp exists or `-1f` otherwise
     */
    fun getFloat(key: String): Float {
        return getFloat(key, -1f)
    }

    /**
     * Return the float value in sp.
     * @param key          The key of sp.
     * @param defaultValue The default value if the sp doesn't exist.
     * @return the float value if sp exists or `defaultValue` otherwise
     */
    @Suppress("NOTHING_TO_LINE")
    fun getFloat(key: String, defaultValue: Float): Float {
        return get(key, defaultValue)
    }

    /**  -------------------- Boolean 处理 ---------------------------------**/

    /**
     * Put the boolean value in sp.
     * @param key   The key of sp.
     */
    fun putBoolean(key: String) {
        put(key, false)
    }

    /**
     * Put the boolean value in sp.
     * @param key   The key of sp.
     * @param value The value of sp.
     */
    @Suppress("NOTHING_TO_LINE")
    fun put(key: String, value: Boolean) {
        put(key, value, false)
    }

    /**
     * Return the boolean value in sp.
     * @param key The key of sp.
     * @return the boolean value if sp exists or `false` otherwise
     */
    fun getBoolean(key: String): Boolean {
        return getBoolean(key, false)
    }

    /**
     * Return the boolean value in sp.
     * @param key          The key of sp.
     * @param defaultValue The default value if the sp doesn't exist.
     * @return the boolean value if sp exists or `defaultValue` otherwise
     */
    @Suppress("NOTHING_TO_LINE")
    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return get(key, defaultValue)
    }

    /**  -------------------- remove 处理 ---------------------------------**/

    /**
     * Remove the preference in sp.
     *
     * @param key The key of sp.
     */
    fun remove(key: String) {
        remove(key, false)
    }

    /**
     * Remove the preference in sp.
     *
     * @param key      The key of sp.
     * @param isCommit True to use [SharedPreferences.Editor.commit],
     * false to use [SharedPreferences.Editor.apply]
     */
    @SuppressLint("ApplySharedPref")
    fun remove(key: String, isCommit: Boolean) {
        if (isCommit) {
            sp?.edit()?.remove(key)?.commit()
        } else {
            sp?.edit()?.remove(key)?.apply()
        }
    }

    /**  -------------------- object 处理 ---------------------------------**/

    fun put(key: String, obj: Any) {
        val data = JsonUtil.toJson(obj)
        put(key, data)
    }

    inline fun <reified T> get(key: String): T? {
        return JsonUtil.fromJson(getString(key), T::class.java)
    }

    /**
     * Put the value in sp.
     */
    @SuppressLint("ApplySharedPref", "CommitPrefEdits")
    fun <T> put(key: String, value: T, isCommit: Boolean) {
        if (spEditor == null) {
            spEditor = sp?.edit()
        }
        spEditor?.run {
            when (value) {
                is String -> putString(key, value)
                is Int -> putInt(key, value)
                is Boolean -> putBoolean(key, value)
                is Long -> putLong(key, value)
                is Float -> putFloat(key, value)
                else -> throw IllegalArgumentException("type not support")
            }
        }?.run {
            if (isCommit) {
                commit()
            } else {
                apply()
            }
        }
    }

    /**
     * Return the value from sp.
     */
    @Suppress("UNCHECKED_CAST", "IMPLICIT_CAST_TO_ANY")
    fun <T> get(key: String, default: T): T = sp?.let {
        when (default) {
            is Int -> getInt(key, default)
            is Boolean -> getBoolean(key, default)
            is String -> getString(key, default)
            is Long -> getLong(key, default)
            is Float -> getFloat(key, default)
            else -> throw IllegalArgumentException("type not support")
        } as T
    } ?: default

}