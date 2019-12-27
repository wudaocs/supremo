package com.td.base

import com.td.log.L
import java.lang.reflect.ParameterizedType

/**
 * Description :
 * Created by Wang Yue on 2019-12-26.
 * Phone ：18610413765
 */
open class KMvpActivity<P> : KBaseActivity() {

    // 全局可使用的p
    var mPresenter: P? = null

    override fun creating() {
        if (this::class.java.genericSuperclass is ParameterizedType &&
                (this::class.java.genericSuperclass as ParameterizedType).actualTypeArguments.isNotEmpty()
        ) {
            try {
                // 如果包含定义的类型 则反射生成对应的p
                val mPresenterClass = (this::class.java.genericSuperclass as ParameterizedType)
                        .actualTypeArguments[0] as Class<*>
                // 生成p
                val constructor = mPresenterClass.constructors
                constructor.run {
                    if (constructor.isNotEmpty()) {
                        @Suppress("UNCHECKED_CAST")
                        mPresenter = constructor[0].newInstance() as P
                    }
                }
                val view = this
                mPresenter?.run {
                    if (this is BasePresenter<*>) {
                        if (view is BaseView) {
                            this.onAttachView(view)
                        } else {
                            L.e(content = "使用mvp框架Activity请继承BaseView")
                        }
                    }
                }
            } catch (e: Exception) {
                e.stackTrace
                L.e(content = "使用mvp框架Presenter请继承BasePresenter")
            }
        }
        super.creating()
    }

    override fun close() {
        super.close()
        mPresenter?.run {
            if (this is BasePresenter<*>) {
                this.onDetached()
            }
        }
    }

}