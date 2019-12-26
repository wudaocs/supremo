package com.td.base

import kotlinx.coroutines.*

/**
 * Description :
 * Created by Wang Yue on 2019-12-26.
 * Phone ：18610413765
 */
abstract class BasePresenter : CoroutineScope by CoroutineScope(SupervisorJob() + Dispatchers.Main){

    var mView: BaseView? = null

    var mModel: BaseModel? = null

    fun onAttachView(view: BaseView? = null) {
        mView = view
    }

    fun onAttachModel(model: BaseModel) {
        mModel = model
    }

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

    fun ct1(block : suspend CoroutineScope.()-> Unit){
        launch (Dispatchers.IO){
            block()
        }
    }

}