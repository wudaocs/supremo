package com.td.supremo.mvp

import com.td.base.KMvpActivity
import com.td.supremo.R
import kotlinx.android.synthetic.main.activity_mvp_main.*

/**
 * Description :
 * Created by Wang Yue on 2019-12-26.
 * Phone ：18610413765
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