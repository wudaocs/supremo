package com.td.base

import android.text.TextUtils
import androidx.appcompat.app.ActionBar

/**
 * Description : 基础base
 * Created by Wang Yue on 2019-12-26.
 * Phone ：18610413765
 */
open class KBaseActivity : KRootActivity(), KBaseListener {

    override fun creating() {
        super.creating()
        findView()
        loadFunction()
    }

    override fun findView() {}

    override fun loadFunction() {}

    open fun setBar(actionBar: ActionBar?, builder: BarBuilder, block: () -> Unit = {}) {
        actionBar?.run {
            title = builder.title
            if (!TextUtils.isEmpty(builder.subTitle)) {
                subtitle = builder.subTitle
            }
            builder.titleTextColor?.run {
                titleColor = this
            }
            builder.logoResId?.run {
                setLogo(this)
            }



            if (builder.isBack){
                setDisplayHomeAsUpEnabled(true)
                setHomeButtonEnabled(true)
            }
        }
    }

}