package com.td.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * Description :
 * Created by Wang Yue on 2019-12-26.
 * Phone ：18610413765
 */
abstract class KRootActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initIntent()
        if (getContentViewLayoutID() != 0) {
            setContentView(getContentViewLayoutID())
        } else if (getContentView() != null) {
            setContentView(getContentView())
        }
        creating()
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
    open fun initIntent(){}
    /** 设置视图id */
    open fun getContentViewLayoutID() : Int {return 0}
    /** 设置视图 */
    open fun getContentView() : View? {return null}
    /** 创建页面中 */
    open fun creating(){}
    /** 刷新页面  */
    protected open fun refresh() {}
    /** 关闭页面  */
    protected open fun close() {}

}