package com.td.log

import android.util.Log
import com.td.base.BuildConfig

/**
 * Description : 日志输出
 * Created by Wang Yue on 2019-12-26.
 * Phone ：18610413765
 */
object L {

    private const val TAG = "logPrint"

    @JvmStatic
    fun v(tag: String = TAG, content: String?) {
        if (BuildConfig.DEBUG) {
            content?.run {
                Log.v(tag, content)
            }
        }
    }

    @JvmStatic
    fun i(tag: String = TAG, content: String?) {
        if (BuildConfig.DEBUG) {
            content?.run {
                Log.i(tag, content)
            }
        }
    }

    @JvmStatic
    fun d(tag: String = TAG, content: String?) {
        if (BuildConfig.DEBUG) {
            content?.run {
                Log.d(tag, content)
            }
        }
    }

    @JvmStatic
    fun e(tag: String = TAG, content: String?) {
        if (BuildConfig.DEBUG) {
            content?.run {
                Log.e(tag, content)
            }
        }
    }
}