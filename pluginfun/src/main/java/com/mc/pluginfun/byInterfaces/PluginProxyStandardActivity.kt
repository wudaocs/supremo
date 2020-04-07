package com.mc.pluginfun.byInterfaces

import android.content.res.Resources
import com.td.base.KBaseActivity
import java.lang.Exception


/**
 * 默认占坑页面 可重复启动
 */
class PluginProxyStandardActivity : KBaseActivity() {

    // 待启动页面名称 - 类名
    var pluginPage: String? = null

    override fun findView() {
        // 视图交给插件处理
        pluginPage = intent.getStringExtra("className")
        try {
            val proxyObj = getPluginClassLoader().loadClass(pluginPage).getConstructor(*arrayOf<Class<*>>()).newInstance(*arrayOf<Any>())
            val proxy = proxyObj as IPluginRelatedListener

            proxy.attach(this)

            proxy.onPCreate(null)

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun loadFunction() {
    }

    override fun isOpenPlugin(): Boolean = true

    private fun getPluginClassLoader(): ClassLoader {
        return PluginIManager.dexClassLoader ?: super.getClassLoader()
    }

    override fun getResources(): Resources {
        return PluginIManager.resources ?: super.getResources()
    }


}