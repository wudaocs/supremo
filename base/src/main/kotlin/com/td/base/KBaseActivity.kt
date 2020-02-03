package com.td.base

/**
 * Description :
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
 */
open class KBaseActivity : KRootActivity(), KBaseListener {

    override fun onCreating() {
        super.onCreating()
        findView()
        loadFunction()
    }

    override fun findView() {}

    override fun loadFunction() {}



}