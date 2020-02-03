package com.td.supremo

import androidx.multidex.MultiDexApplication
import com.td.utils.AppManager

/**
 * Description : application
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
 */
class AppApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        AppManager.initApp(this)
    }
}