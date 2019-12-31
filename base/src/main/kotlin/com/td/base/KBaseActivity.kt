package com.td.base

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

}