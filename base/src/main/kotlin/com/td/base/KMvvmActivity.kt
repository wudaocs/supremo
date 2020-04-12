package com.td.base

import com.td.log.L
import java.lang.reflect.ParameterizedType

/**
 * Description : MVVM 基类
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
 */
class KMvvmActivity<M : BaseViewModel> : KBaseActivity() {

    private var mViewModel: BaseViewModel? = null

    // 默认实例化 ViewModel
    override fun onCreating() {
        if (this::class.java.genericSuperclass is ParameterizedType &&
                (this::class.java.genericSuperclass as ParameterizedType).actualTypeArguments.isNotEmpty()
        ) {
            try {
                // 如果包含定义的类型 则反射生成对应的VM
                val mViewModelClass = (this::class.java.genericSuperclass as ParameterizedType)
                        .actualTypeArguments[0] as Class<*>
                // 生成VM
                val constructor = mViewModelClass.constructors
                constructor.run {
                    if (constructor.isNotEmpty()) {
                        @Suppress("UNCHECKED_CAST")
                        mViewModel = constructor[0].newInstance(*arrayOf<Any>(application)) as M
                    }
                }
            } catch (e: Exception) {
                e.stackTrace
                L.e(content = "使用mvvm框架ViewModel请继承BaseViewModel")
            }
        }
        super.onCreating()
    }

    override fun findView() {
    }

    override fun loadFunction() {
    }


}