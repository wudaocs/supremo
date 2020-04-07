package com.td.utils

import android.R
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.annotation.ColorInt
import androidx.annotation.IntRange
import com.td.log.L
import java.util.regex.Pattern

object StatusBarUtil {

    @Volatile
    private var mStatusBarHeight: Int? = null

    /**
     * 设置状态栏颜色
     *
     * @param color    状态栏颜色值
     * @param alpha    状态栏透明度
     */
    fun setColor(activity: Activity, @ColorInt color: Int, @IntRange(from = 0, to = 255) alpha: Int = 0) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            activity.window.statusBarColor = cipherColor(color, alpha)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            setTranslucentView(activity.window.decorView as ViewGroup, color, alpha)
            setRootView(activity, true)
        }
    }

    /**
     * 设置状态栏渐变颜色
     *
     * @param view     目标View
     */
    fun setGradientColor(activity: Activity, view: View) {
        val decorView = activity.window.decorView as ViewGroup
        val fakeStatusBarView = decorView.findViewById<View>(R.id.custom)
        if (fakeStatusBarView != null) {
            decorView.removeView(fakeStatusBarView)
        }
        setRootView(activity, false)
        setTransparentForWindow(activity)
        setPaddingTop(activity, view)
    }

    /**
     * 设置透明状态栏
     *
     * @param activity 目标界面
     */
    fun setTransparentForWindow(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.window.statusBarColor = Color.TRANSPARENT
            activity.window
                    .decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.window
                    .setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }

    /**
     * 增加View的paddingTop,增加的值为状态栏高度 (智能判断，并设置高度)
     *
     * @param context 目标Context
     * @param view    需要增高的View
     */
    fun setPaddingTop(context: Context, view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val lp = view.layoutParams
            if (lp != null && lp.height > 0 && view.paddingTop == 0) {
                lp.height += getStatusBarHeight(context)
                view.setPadding(view.paddingLeft, view.paddingTop + getStatusBarHeight(context),
                        view.paddingRight, view.paddingBottom)
            }
        }
    }

    /**
     * 设置状态栏darkMode,字体颜色及icon变黑(目前支持MIUI6以上,Flyme4以上,Android M以上)
     */
    fun setDarkMode(activity: Activity) {
        darkMode(activity.window, true)
    }

    /**
     * 设置状态栏darkMode,字体颜色及icon变亮(目前支持MIUI6以上,Flyme4以上,Android M以上)
     */
    fun setLightMode(activity: Activity) {
        darkMode(activity.window, false)
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun darkMode(window: Window?, dark: Boolean) {
        window?.run {
            if (isFlyme4) {
                setModeForFlyme4(window, dark)
            } else if (isMIUI6 == true) {
                setModeForMIUI6(window, dark)
            }
            darkModeForM(window, dark)
        }
    }

    /**
     * android 6.0设置字体颜色
     *
     * @param window 目标window
     * @param dark   亮色 or 暗色
     */
    private fun darkModeForM(window: Window, dark: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var systemUiVisibility = window.decorView.systemUiVisibility
            systemUiVisibility = if (dark) {
                systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            }
            window.decorView.systemUiVisibility = systemUiVisibility
        }
    }

    /**
     * 设置MIUI6+的状态栏的darkMode,darkMode时候字体颜色及icon
     * @param dark   亮色 or 暗色
     */
    @SuppressLint("PrivateApi")
    private fun setModeForMIUI6(window: Window, dark: Boolean) {
        val clazz: Class<out Window> = window.javaClass
        try {
            val layoutParams = Class.forName("android.view.MiuiWindowManager\$LayoutParams")
            val field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE")
            val darkModeFlag = field.getInt(layoutParams)
            val extraFlagField = clazz.getMethod("setExtraFlags", Int::class.javaPrimitiveType, Int::class.javaPrimitiveType)
            extraFlagField.invoke(window, if (dark) darkModeFlag else 0, darkModeFlag)
        } catch (e: Exception) {
            L.e("StatusBar", "darkIcon: failed")
        }
    }

    /**
     * 设置Flyme4+的状态栏的darkMode,darkMode时候字体颜色及icon
     * @param dark   亮色 or 暗色
     */
    private fun setModeForFlyme4(window: Window, dark: Boolean) {
        try {
            val lp = window.attributes
            val darkFlag = WindowManager.LayoutParams::class.java.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON")
            val meizuFlags = WindowManager.LayoutParams::class.java.getDeclaredField("meizuFlags")
            darkFlag.isAccessible = true
            meizuFlags.isAccessible = true
            val bit = darkFlag.getInt(null)
            var value = meizuFlags.getInt(lp)
            value = if (dark) {
                value or bit
            } else {
                value and bit.inv()
            }
            meizuFlags.setInt(lp, value)
            window.attributes = lp
        } catch (e: Exception) {
            L.e("StatusBar", "darkIcon: failed")
        }
    }

    /**
     * 判断是否Flyme4以上
     */
    private val isFlyme4: Boolean
        get() = (Build.FINGERPRINT.contains("Flyme_OS_4") || Build.VERSION.INCREMENTAL.contains("Flyme_OS_4")
                || Pattern.compile("Flyme OS [4|5]", Pattern.CASE_INSENSITIVE).matcher(Build.DISPLAY).find())

    /**
     * 判断是否MIUI6以上
     */
    private val isMIUI6: Boolean? = null
        @SuppressLint("PrivateApi")
        get() = try {
            if (field == null) {
                val clz = Class.forName("android.os.SystemProperties")
                val mtd = clz.getMethod("get", String::class.java)
                var value = mtd.invoke(null, "ro.miui.ui.version.name") as String
                value = value.replace("[vV]".toRegex(), "")
                val version = value.toInt()
                version >= 6
            } else {
                field
            }
        } catch (e: Exception) {
            false
        }

    /**
     * 计算alpha色值
     *
     * @param color 状态栏颜色值
     * @param alpha 状态栏透明度
     */
    private fun cipherColor(@ColorInt color: Int, alpha: Int): Int {
        if (alpha == 0) {
            return color
        }
        val a = 1 - alpha / 255f
        var red = color shr 16 and 0xff
        var green = color shr 8 and 0xff
        var blue = color and 0xff
        red = (red * a + 0.5).toInt()
        green = (green * a + 0.5).toInt()
        blue = (blue * a + 0.5).toInt()
        return 0xff shl 24 or (red shl 16) or (green shl 8) or blue
    }

    /**
     * 创建透明View
     *
     * @param viewGroup 目标视图
     * @param color     状态栏颜色值
     * @param alpha     状态栏透明度
     */
    private fun setTranslucentView(viewGroup: ViewGroup, @ColorInt color: Int, @IntRange(from = 0, to = 255) alpha: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val cipherColor = cipherColor(color, alpha)
            var translucentView = viewGroup.findViewById<View>(R.id.custom)
            if (translucentView == null && cipherColor != 0) {
                translucentView = View(viewGroup.context)
                translucentView.id = R.id.custom
                val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(viewGroup.context))
                viewGroup.addView(translucentView, params)
            }
            translucentView?.setBackgroundColor(cipherColor)
        }
    }

    /**
     * 设置根布局参数
     * @param fitSystemWindows 是否预留toolbar的高度
     */
    private fun setRootView(activity: Activity, fitSystemWindows: Boolean) {
        val parent = activity.findViewById<ViewGroup>(R.id.content)
        var i = 0
        val count = parent.childCount
        while (i < count) {
            val childView = parent.getChildAt(i)
            if (childView is ViewGroup) {
                childView.setFitsSystemWindows(fitSystemWindows)
                childView.clipToPadding = fitSystemWindows
            }
            i++
        }
    }

    /**
     * 获取状态栏高度
     */
    private fun getStatusBarHeight(context: Context): Int {
        if (mStatusBarHeight == null) {
            val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
            mStatusBarHeight = context.resources.getDimensionPixelSize(resourceId)
        }
        return mStatusBarHeight!!
    }
}