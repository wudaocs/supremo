package com.td.base

import kotlinx.coroutines.*

/**
 * Description :
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
 */
abstract class BasePresenter<V> : CoroutineScope by CoroutineScope(SupervisorJob() + Dispatchers.Main){

    var mView: BaseView? = null

    var mModel: BaseModel? = null

    fun onAttachView(view: BaseView? = null) {
        mView = view
    }

    fun onAttachModel(model: BaseModel) {
        mModel = model
    }

    @Suppress("UNCHECKED_CAST")
    fun getView(): V? = mView as V

    /**
     * 页面销毁时默认调用
     */
    fun onDetached() {
        cancel()
        mView = null
        mModel = null
    }

    /** 切换线程 switch ui thread */
    fun ui(block : ()-> Unit){
        launch {
            block()
        }
    }

    /** 切换线程 switch child thread */
    fun ct(block : suspend ()-> Unit){
        launch (Dispatchers.IO){
            block()
        }
    }

    /**
     * 延迟处理
     */
    fun postDelay(time : Long = 0, block : suspend ()-> Unit){
        launch (Dispatchers.IO){
            delay(time)
            withContext(Dispatchers.Main){
                block()
            }
        }
    }

}