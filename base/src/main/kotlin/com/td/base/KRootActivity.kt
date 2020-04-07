package com.td.base

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.td.log.L
import com.td.utils.StatusBarUtil

/**
 * Description :
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
 */
abstract class KRootActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_CODE = 1100
    }

    private var mContainerId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        if (!isOpenPlugin()) {
            if (isHiddenNavigationBar()) {
                StatusBarUtil.setTransparentForWindow(this)
            }
            setActionBarColorMode(isDarkMode())
            if (isHiddenTitle()) {
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                supportActionBar?.hide()
            }
            super.onCreate(savedInstanceState)
            initIntent()
            if (getContentViewLayoutID() != 0) {
                setContentView(getContentViewLayoutID())
            } else {
                val view = getContentView()
                if (view != null) {
                    setContentView(view)
                } else {
                    L.e(content = "请设置页面视图")
                }
            }
        }
        onCreating()
    }

    override fun onResume() {
        super.onResume()
        refresh()
    }

    override fun onDestroy() {
        super.onDestroy()
        close()
    }

    /** 传递数据初始化 */
    open fun initIntent() {}

    /** 设置视图id */
    open fun getContentViewLayoutID(): Int {
        return 0
    }

    /** 设置视图 */
    open fun getContentView(): View? {
        return null
    }

    /** 创建页面中 */
    open fun onCreating() {}

    /** 刷新页面  */
    protected open fun refresh() {}

    /** 关闭页面  */
    protected open fun close() {}

    private fun hideNavigationBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window?.run {
                clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                statusBarColor = Color.TRANSPARENT
            }
        }
    }

    open fun changeNavBarWhite() {
        changeNavBarBackground(R.color.white)
    }

    @Suppress("DEPRECATION")
    open fun changeNavBarBackground(colorResId: Int) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val window: Window = this.window
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = this.resources.getColor(R.color.white)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    open fun isHiddenTitle() = false
    open fun isHiddenNavigationBar() = false
    open fun isDarkMode() = false

    // 是否开启插件化
    open fun isOpenPlugin() = false

    open fun setContainerId(containerId: Int) {
        mContainerId = containerId
    }

    open fun replace(fragment: KBaseFragment, containerId: Int = mContainerId) {
        supportFragmentManager.beginTransaction().replace(containerId, fragment).commit()
    }

}