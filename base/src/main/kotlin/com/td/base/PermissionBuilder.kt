package com.td.base

import android.app.Dialog

/**
 * Description : Permission 控制参数
 * Created by YW on 2020/4/9 .
 * Email : 1809267944@qq.com
 */
class PermissionBuilder(vararg permissions: String) {

    private var list: MutableList<String>? = null

    init {
        permissions.forEach {
            addPermission(it)
        }
    }

    private fun addPermission(permission: String) {
        if (list == null) {
            list = mutableListOf()
        }
        list?.add(permission)
    }

    fun getPermission(): MutableList<String> {
        return list ?: mutableListOf()
    }

    // 自定义弹出框
    var view: Dialog? = null


}