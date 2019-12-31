package com.td.supremo

import com.td.base.KBaseActivity
import com.td.supremo.flutter.FunctionFlutterActivity

/**
 * Description :
 * Created by Wang Yue on 2018/9/26.
 * Job number：135033
 * Phone ：18610413765
 * Email：wangyue@syswin.com
 * Person in charge :Wang Yue
 * Leader：Ding Lei
 */
class HomeActivity : KBaseActivity() {

    override fun getContentViewLayoutID(): Int {
        return R.layout.activity_home
    }

    override fun findView() {

    }

    override fun creating() {
        super.creating()
        open(FunctionFlutterActivity::class.java)
    }
}