package com.td.base

/**
 * Description :
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
 */
abstract class KBaseActivity : KRootActivity() {

    override fun onCreating() {
        super.onCreating()
        findView()
        loadFunction()
    }

    /** 获取视图 */
    abstract fun findView()

    /** 加载业务功能 */
    abstract fun loadFunction()


}