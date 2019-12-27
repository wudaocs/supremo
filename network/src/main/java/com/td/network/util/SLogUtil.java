package com.td.network.util;

import android.util.Log;

import com.td.network.BuildConfig;

/**
 * Description : 日志工具类
 * Created by Mc on 2018/7/25.
 * Job number：135033
 * Phone ：18601413765
 * Email：wangyue@syswin.com
 * Person in charge : Mc
 * Leader：Mc
 */
public class SLogUtil {

    public static void e(String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, message);
        }
    }
}
