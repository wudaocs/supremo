package com.mc.pluginfun.byInterfaces

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import com.td.exts.whatCanDo
import com.td.log.L
import com.td.utils.copyFile
import dalvik.system.DexClassLoader
import java.io.File
import java.lang.reflect.Method

/**
 * 插装式插件控制类
 */
object PluginIManager {

    var dexClassLoader: DexClassLoader? = null

    var resources: Resources? = null

    var packageInfo: PackageInfo? = null

    const val CLASSNAME = "className"

    /**
     * 加载插件
     * @param context 打开插件的上下文
     * @param path 插件apk 存储目录 - 绝对目录
     */
    fun loadPlugin(context: Context, path: String, pluginName: String = "default"): Boolean {

        val name = "plugin.apk"
        // 先拷贝apk到插件目录
        copyFile(path, context.getDir("plugin", Context.MODE_PRIVATE).absolutePath + "/$name")

        val pluginApk = File(context.getDir("plugin", Context.MODE_PRIVATE), name)
        val apkPath = pluginApk.absolutePath

        // 拷贝完成开始加载
        var isSuccess = true
        val packageManager = context.packageManager
        packageInfo = packageManager.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES)

        val dex = context.getDir("dex", Context.MODE_PRIVATE)
        dexClassLoader = DexClassLoader(apkPath, dex.absolutePath, null, context.classLoader)

        try {
            // 获取资源
            val assetManager = AssetManager::class.java.newInstance()
            val addAssetPath: Method = AssetManager::class.java.getMethod("addAssetPath", String::class.java)
            addAssetPath.invoke(assetManager, apkPath)
            resources = Resources(assetManager, context.resources.displayMetrics, context.resources.configuration)
        } catch (e: Exception) {
            Log.e("plugin", "load plugin error")
            e.printStackTrace()
            isSuccess = false
        }
        return isSuccess
    }

    /**
     * 开启插件一般页面
     * @param activity 打开插件页面的页面
     * @param className 将要打开的插件页面名称
     * @param bundle 参数集合
     */
    fun startStandardActivity(activity: Activity?, className: String, bundle: Bundle? = null) {
        val intent = Intent(activity, PluginProxyStandardActivity::class.java).apply {
            bundle.whatCanDo(
                    { putExtra(CLASSNAME, className) },
                    {
                        val bundles = (it as Bundle).apply {
                            putExtra(CLASSNAME, className)
                        }
                        putExtras(bundles)
                    }
            )
        }
        activity?.startActivity(intent)
    }
}