package com.td.supremo.mvp

import com.td.base.KMvpActivity
import com.td.supremo.R
import kotlinx.android.synthetic.main.activity_mvp_main.*

/**
 * Description : 测试mvp页面
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
 */
class TestMvpActivity : KMvpActivity<TestMvpPresenter>(), TestView {

    override fun getContentViewLayoutID(): Int {
        return R.layout.activity_mvp_main
    }

    override fun findView() {
        super.findView()
    }

    override fun loadFunction() {
        super.loadFunction()
        mPresenter?.getData()

    }

    override fun updateText(text: String?) {
        text?.run {
            tv_activity_mvp_main.text = this
        }
    }


}