package com.td.supremo.home

import android.os.Environment
import android.view.KeyEvent
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.mc.pluginfun.byInterfaces.PluginIManager
import com.td.base.KBaseActivity
import com.td.base.constants.PERMISSION_READ_CONTACTS
import com.td.base.constants.PERMISSION_READ_STORAGE
import com.td.base.constants.PERMISSION_WRITE_CONTACTS
import com.td.base.setActionBarColorMode
import com.td.exts.onClick
import com.td.exts.textColor
import com.td.exts.textExt
import com.td.exts.toast
import com.td.log.L
import com.td.supremo.R
import com.td.views.custom.LLEntity
import com.td.views.custom.ListLinearLayout
import com.td.views.custom.OnLLItemClickListener
import com.td.views.custom.bottom.BNEntity
import com.td.views.custom.bottom.BottomNavigationView
import com.td.views.vgloader.VGBuilder
import com.td.views.vgloader.VGLoader
import com.td.views.vgloader.VGScaleType
import kotlinx.android.synthetic.main.activity_home_drawer.*
import kotlinx.coroutines.*

/**
 * Description : 抽屉效果主页
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
 */
class HomeDrawerActivity : KBaseActivity(), OnLLItemClickListener {

    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mNavigationView: NavigationView
    private lateinit var ivUserAvatar: ImageView
    private lateinit var tvUserName: TextView
    private lateinit var tvUserDesc: TextView
    private lateinit var mBottomNavigationView: BottomNavigationView

    private val menuMenu = "菜单"
    private val menuScan = "扫一扫"

    override fun getContentViewLayoutID(): Int {
        return R.layout.activity_home_drawer
    }

    override fun findView() {
        setContainerId(R.id.fl_activity_home_drawer_container)
//        actionBar(BarBuilder("抽屉首页"))
        mDrawerLayout = dl_activity_home_drawer
        mNavigationView = nv_activity_home_drawer
        mBottomNavigationView = bnv_activity_home_drawer_bottom
        buildNavigation()
        mDrawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerStateChanged(newState: Int) {

            }

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {

            }

            override fun onDrawerClosed(drawerView: View) {
                setActionBarColorMode(false)
            }

            override fun onDrawerOpened(drawerView: View) {
                setActionBarColorMode(true)
            }

        })
    }

    /**
     * 侧边
     */
    private fun buildNavigation() {
        val headerView = View.inflate(this, R.layout.view_home_drawer_header, null)
        ivUserAvatar = headerView.findViewById(R.id.iv_view_home_drawer_header_avatar)
        tvUserName = headerView.findViewById(R.id.tv_view_home_drawer_username)
        tvUserDesc = headerView.findViewById(R.id.tv_view_home_drawer_user_desc)
        val ll: LinearLayout = headerView.findViewById(R.id.ll_view_home_drawer_menu)
        ll.addView(ListLinearLayout(this, mutableListOf(LLEntity(menuMenu, R.drawable.icon_blue_cloud, this),
                LLEntity(menuScan, R.drawable.icon_blue_fly, this))))

        val view = headerView.findViewById<View>(R.id.item_ll_bottom)

        mNavigationView.addHeaderView(headerView)
        // 侧边栏宽度
//        mNavigationView.layoutParams.width = ScreenUtil.width
    }

    override fun loadFunction() {
        VGLoader.show(VGBuilder(this, "https://images.liqucn.com/img/h1/h994/img201802021024070_info300X300.jpg", ivUserAvatar).apply {
            scaleType = VGScaleType.CIRCLE
        })
        tvUserName.textExt("小狮子")
        tvUserDesc.textExt("七月份的小狮子")
        tvUserName.textColor(R.color.colorAccent)
        tvUserDesc.textColor(R.color.colorPrimary)

        ivUserAvatar.setOnClickListener {
            mDrawerLayout.closeDrawers()
            GlobalScope.launch(Dispatchers.Main) {
                withContext(Dispatchers.IO) {
                    showPlugin()
                }
                // 打开插件页面
                PluginIManager.startStandardActivity(this@HomeDrawerActivity, "com.mc.pluginone.PluginTestMainActivity")
            }
            GlobalScope.launch(Dispatchers.IO) {
                delay(150)
                withContext(Dispatchers.Main) {
                    it.onClick {
                        L.e(content = "点击点击点击")
                    }
                }
            }
        }

        initBottom()
        requestPermissions(PERMISSION_READ_STORAGE, PERMISSION_READ_CONTACTS, PERMISSION_WRITE_CONTACTS)
    }

    override fun permissionsDenied(denied: MutableList<String>?) {
        super.permissionsDenied(denied)
    }

    private fun showPlugin() {
        // TODO 测试调用插件
        PluginIManager.loadPlugin(this@HomeDrawerActivity, Environment.getExternalStorageDirectory().absolutePath + "/plugin.apk")

    }

    private fun initBottom() {
        mBottomNavigationView.setMenu(mutableListOf(BNEntity(name = "123", block = { clickItem("123") }).apply {
            isSelector = true
        }, BNEntity(name = "456", block = { clickItem("456") }), BNEntity(name = "789", block = { clickItem("789") })))
    }

    private fun clickItem(name: String) {
        toast("点击 $name")

    }

    override fun onCreating() {
        super.onCreating()
        replace(HomeFragment())
    }

    override fun isHiddenNavigationBar(): Boolean = true

    override fun isHiddenTitle(): Boolean = true

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun click(name: String) {
        when (name) {
            menuMenu -> {
                toast(menuMenu)
            }
            menuScan -> {
                toast(menuScan)
            }
        }
    }
}