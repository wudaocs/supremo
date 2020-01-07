package com.td.supremo

import androidx.multidex.MultiDexApplication
import com.td.utils.AppManager

/**
 * Description :
 * Created by Wang Yue on 2020-01-02.
 * Phone ：18610413765
 */
class AppApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        AppManager.initApp(this)
    }
}