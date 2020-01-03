package com.td.supremo.home

import android.view.KeyEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.td.base.KBaseActivity
import com.td.base.BarBuilder
import com.td.supremo.R
import com.td.supremo.flutter.FunctionFlutterActivity
import com.td.utils.ScreenUtil
import com.td.views.vgloader.VGBuilder
import com.td.views.vgloader.VGLoader
import com.td.views.vgloader.VGScaleType
import kotlinx.android.synthetic.main.activity_home_drawer.*
import kotlinx.coroutines.*

/**
 * Description :
 * Created by Wang Yue on 2018/9/26.
 * Job number：135033
 * Phone ：18610413765
 * Email：wangyue@syswin.com
 * Person in charge :Wang Yue
 * Leader：Ding Lei
 */
class HomeDrawerActivity : KBaseActivity() {

    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mNavigationView: NavigationView
    private lateinit var ivUserAvatar: ImageView
    private lateinit var tvUserName: TextView
    private lateinit var tvUserDesc: TextView

    override fun getContentViewLayoutID(): Int {
        return R.layout.activity_home_drawer
    }

    override fun findView() {
        setContainerId(R.id.fl_activity_home_drawer_container)
//        setBar(supportActionBar, BarBuilder("抽屉首页"))
        mDrawerLayout = dl_activity_home_drawer
        mNavigationView = nv_activity_home_drawer

        val headerView = View.inflate(this, R.layout.view_home_drawer_header, null)
        ivUserAvatar = headerView.findViewById(R.id.iv_view_home_drawer_header_avatar)
        tvUserName = headerView.findViewById(R.id.tv_view_home_drawer_username)
        tvUserDesc = headerView.findViewById(R.id.tv_view_home_drawer_user_desc)
        mNavigationView.addHeaderView(headerView)
        mNavigationView.layoutParams.width = ScreenUtil.width

        VGLoader.show(VGBuilder(this, "https://images.liqucn.com/img/h1/h994/img201802021024070_info300X300.jpg", ivUserAvatar).apply {
            scaleType = VGScaleType.CIRCLE
        })

        tvUserName.text = "小狮子"
        tvUserDesc.text = "七月份的小狮子"

        ivUserAvatar.setOnClickListener {
            mDrawerLayout.closeDrawers()
            GlobalScope.launch (Dispatchers.IO){
                delay(150)
                withContext(Dispatchers.Main){
                    open(FunctionFlutterActivity::class.java)
                }
            }
        }

        mDrawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener{
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

    override fun creating() {
        super.creating()
        replace(HomeFragment())
    }

    override fun isHiddenNavigationBar(): Boolean = true

    override fun isHiddenTitle(): Boolean = true

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawers()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}