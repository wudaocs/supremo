package com.td.utils

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Process
import android.provider.Settings
import android.telephony.TelephonyManager
import android.text.TextUtils
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException
import java.net.Inet4Address
import java.net.NetworkInterface
import java.util.*

/**
 * Description : app 应用工具类
 * Created by Wang Yue on 2019-09-25.
 * Phone ：18610413765
 * Email：wangyue@91sph.com
 */
@Suppress("unused")
object AppManager {

    private var mApplication: Application? = null
    // 包名称
    private var mPackageName: String = ""
    // 包管理器
    private var mPackageManager: PackageManager? = null
    //版本号
    private var mVersionName: String? = null
    //版本build号
    private var mVersionCode: String? = null

    /**
     * 初始化系统操作工具类
     */
    fun initApp(application: Application) {
        mApplication = application
        ScreenUtil.init(application.applicationContext)
    }

    fun getContext(): Context? {
        checkNotNull(mApplication) { "UAppManager initApp not called!!!" }
        return mApplication?.applicationContext
    }

    /**
     * 判断服务是否存活
     *
     * @param className 类名
     * @return boolean true 正在运行 false 停止运行
     */
    @Suppress("DEPRECATION")
    fun isServiceRunning(className: String): Boolean {
        var isRunning = false
        try {
            val activityManager = mApplication?.let {
                it.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            }
            val serviceList = activityManager?.getRunningServices(Integer.MAX_VALUE)
            serviceList?.run {
                if (isNotEmpty()) {
                    var i = 0
                    val count = size
                    while (i < count) {
                        if (TextUtils.equals(this[i].service.className, className)) {
                            isRunning = true
                            break
                        }
                        i++
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return isRunning
    }

    /**
     * 获取当前进程名字
     * @return 当前进程名称，获取失败返回 unknown
     */
    fun getProcessName(): String? {
        var processName = "unknown"
        var mBufferedReader: BufferedReader? = null
        try {
            val file = File("/proc/" + Process.myPid() + "/" + "cmdline")
            mBufferedReader = BufferedReader(FileReader(file))
            processName = mBufferedReader.readLine()
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim { it <= ' ' }
            }
            return processName
        } catch (e: Exception) {
            val pid = Process.myPid()
            try {
                val activityManager = mApplication?.let {
                    it.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
                }
                activityManager?.run {
                    for (appProcess in runningAppProcesses) {
                        if (appProcess.pid == pid) {
                            return appProcess.processName
                        }
                    }
                }
            } catch (e1: Exception) {
                e1.printStackTrace()
            }
            return processName
        } finally {
            if (mBufferedReader != null) {
                try {
                    mBufferedReader.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    /**
     * 判断应用是否存活
     */
    fun isAppAlive(): Boolean {
        val activityManager = mApplication?.let {
            it.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        }
        activityManager?.run {
            val appProcesses = runningAppProcesses
            val packageName = mApplication?.packageName
            packageName?.let {
                for (appProcess in appProcesses) {
                    if (TextUtils.equals(it, appProcess.processName)) {
                        if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                            return true
                        }
                    }
                }
            }
        }
        return false
    }

    /**
     * 获取当前应用包名
     * @return 包名
     */
    @Suppress("NOTHING_TO_LINE")
    fun getPackageName(): String? {
        try {
            if (TextUtils.isEmpty(mPackageName)) {
                mApplication?.run {
                    mPackageName = getPackageName(applicationContext)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return mPackageName
    }

    /**
     * 获取上下文对应的包名
     * @param context 上下文
     * @return 包名
     */
    @Suppress("NOTHING_TO_LINE")
    fun getPackageName(context: Context): String {
        try {
            return context.packageName
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

    /**
     * 获取包管理类
     * @return 包资源管理器操作实体
     */
    @Suppress("NOTHING_TO_LINE")
    fun getPackageManager(): PackageManager? {
        try {
            if (mPackageManager == null) {
                mPackageManager = mApplication?.packageManager
            }
            return mPackageManager
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    /**
     * 获取包信息
     * @return 包信息
     */
    @Synchronized
    fun getPackageInfo(): PackageInfo? {
        try {
            mPackageManager = getPackageManager()
            return mPackageManager?.run {
                val name = getPackageName()
                name?.run {
                    getPackageInfo(this, PackageManager.GET_META_DATA)
                }
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * 获取应用信息
     */
    @Suppress("NOTHING_TO_LINE")
    fun getApplicationInfo(): ApplicationInfo? {
        try {
            mPackageManager = getPackageManager()
            return mPackageManager?.run {
                val name = getPackageName()
                name?.run {
                    getApplicationInfo(this, PackageManager.GET_META_DATA)
                }
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * 获取清单文件中的元数据
     * @param name the key of value
     */
    fun getBooleanMetaData(name: String): Boolean {
        return getMetaData().getBoolean(name)
    }

    /**
     * 获取清单文件中的元数据
     * @param name the key of value
     */
    fun getStringMetaData(name: String): String? {
        return getMetaData().getString(name)
    }

    /**
     * 获取清单文件中的元数据
     * @param name the key of value
     */
    fun getIntMetaData(name: String): Int {
        return getIntMetaData(name, 0)
    }

    /**
     * 获取清单文件中的元数据
     * @param name the key of value
     */
    @Suppress("NOTHING_TO_LINE")
    fun getIntMetaData(name: String, defaultValue: Int): Int {
        return getMetaData().getInt(name, defaultValue)
    }

    /**
     * 获取清单文件中的所有元数据
     * @return 返回bundle对象
     */
    private fun getMetaData(): Bundle {
        try {
            return getApplicationInfo()?.metaData ?: Bundle()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return Bundle()
    }

    /**
     * 获取版本号
     */
    fun getVersion(): String {
        return mVersionName?.apply {
            getPackageInfo()?.run {
                mVersionName = versionName
            }
        } ?: ""
    }

    /**
     * 获取build号
     */
    @Suppress("DEPRECATION")
    fun getVersionCode(): String {
        return mVersionCode?.apply {
            getPackageInfo()?.run {
                mVersionCode = versionCode.toString()
            }
        } ?: "-1"
    }

    /**
     * 获取手机ip
     */
    fun getPhoneIp(): String {
        try {
            val en = NetworkInterface.getNetworkInterfaces() ?: return ""
            while (en.hasMoreElements()) {
                val inetAddresses = en.nextElement().inetAddresses
                while (inetAddresses.hasMoreElements()) {
                    val inetAddress = inetAddresses.nextElement()
                    if (!inetAddress.isLoopbackAddress && inetAddress is Inet4Address) {
                        return inetAddress.getHostAddress()
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

    /**
     * 获取设备唯一标识
     * @param context 上下文
     * @return 设备唯一标识
     */
    @Suppress("DEPRECATION")
    @SuppressLint("MissingPermission", "HardwareIds")
    fun getDeviceId(context: Context): String {
        val key = "u_DeviceId"
        if (TextUtils.isEmpty(SpManager.getInstance().getString(key))) {
            var deviceId1: String? = null
            var deviceId2: String? = null
            try {
                val telephonyManager: TelephonyManager? =
                        context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                telephonyManager?.run {
                    deviceId1 = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        imei
                    } else {
                        deviceId
                    }
                }
                if (TextUtils.isEmpty(deviceId1)) {
                    deviceId1 = Settings.Secure.getString(
                            context.contentResolver,
                            Settings.Secure.ANDROID_ID
                    )
                } else {
                    SpManager.getInstance().put(key, deviceId1!!)
                    return deviceId1!!
                }
                // 由于厂商和型号问题存在相同结果情况 都为9774d56d682e549c
                if (!TextUtils.isEmpty(deviceId1) && "9774d56d682e549c" != deviceId1) {
                    deviceId1 =
                            UUID.nameUUIDFromBytes(deviceId1!!.toByteArray(charset("utf8"))).toString()
                } else {
                    // MTK平台获取方式，如果不是此平台，则是高通平台
                    deviceId1 = getOperatorBySlot(telephonyManager, "getDeviceIdGemini", 0)
                    deviceId2 = getOperatorBySlot(telephonyManager, "getDeviceIdGemini", 1)
                }
                // 如果获取MTK平台失败，则获取高通平台的
                if (TextUtils.isEmpty(deviceId1) && TextUtils.isEmpty(deviceId2)) {
                    deviceId1 = getOperatorBySlot(telephonyManager, "getDeviceId", 0)
                    deviceId2 = getOperatorBySlot(telephonyManager, "getDeviceId", 1)
                }
                // 优先卡1，如果卡1获取不到，则用卡2，都获取不到，则用设备id,最后考虑自动生成
                return deviceId1?.let {
                    if (!TextUtils.isEmpty(it) && TextUtils.equals(it, "000000000000000")) {
                        null
                    } else {
                        SpManager.getInstance().put(key, it)
                        it
                    }
                } ?: (deviceId2?.apply {
                    SpManager.getInstance().put(key, this)
                } ?: UUID.randomUUID().toString())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return SpManager.getInstance().getString(key)
    }

    private fun getOperatorBySlot(
            telephony: TelephonyManager?, predictedMethodName: String, slotID: Int
    ): String? {
        return telephony?.let {
            var inumeric: String? = null
            try {
                val telephonyClass = Class.forName(telephony.javaClass.name)
                val parameter = arrayOfNulls<Class<*>>(1)
                parameter[0] = Int::class.javaPrimitiveType
                val getSimID = telephonyClass.getMethod(predictedMethodName, *parameter)
                val obParameter = arrayOfNulls<Any>(1)
                obParameter[0] = slotID
                getSimID.invoke(telephony, *obParameter)?.run {
                    inumeric = toString()
                }
            } catch (e: Exception) {
            }
            inumeric
        }
    }

    /**
     * 获取当前手机系统语言。
     *
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN”
     */
    fun getSystemLanguage(): String {
        return Locale.getDefault().language
    }

    /**
     * 获取设备名称
     */
    fun getDeviceName(): String = Build.MODEL

    /**
     * 获取当前手机系统版本号
     * @return  系统版本号
     */
    fun getSystemVersion(): String = Build.VERSION.RELEASE

    /**
     * 获取手机厂商
     * @return  手机厂商
     */
    fun getDeviceBrand(): String = Build.BRAND

}