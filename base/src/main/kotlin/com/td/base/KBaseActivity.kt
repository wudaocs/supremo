package com.td.base

import android.app.Dialog
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.td.exts.whatCanDo

/**
 * Description : 业务基类 包含权限控制
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
 */
abstract class KBaseActivity : KRootActivity() {

    private var mPermissionBuilder: PermissionBuilder? = null

    override fun onCreating() {
        super.onCreating()
        findView()
        loadFunction()
    }

    /** 获取视图 */
    abstract fun findView()

    /** 加载业务功能 */
    abstract fun loadFunction()

    // 以下增加对于权限的控制 --------------------------------- ↓↓↓↓↓↓
    private val requestPermissionRequestCode = 1111;

    // 权限已经被授权
    open fun permissionsGrant() {}

    // 待处理权限
    open fun permissionsDenied(denied: MutableList<String>? = null) {
    }

    /**
     * 检查申请的权限是否包含需要申请的权限
     */
    open fun requestPermissions(vararg permissions: String) {
        requestPermissions(PermissionBuilder(*permissions))
    }

    open fun requestPermissions(permissionBuilder: PermissionBuilder) {
        val unPermissions = hasPermission(permissionBuilder.getPermission())
        // 如果无权限数组为空 则表示全部都已经获取到权限
        if (unPermissions.isEmpty()) {
            // 已经有全部权限
            permissionsGrant()
        } else {
            mPermissionBuilder = permissionBuilder
            // 把所有待申请权限的权限进行申请权限操作
            ActivityCompat.requestPermissions(this,
                    unPermissions.toTypedArray(),
                    requestPermissionRequestCode)
        }
    }

    /**
     * 判断申请的权限是否都已经获取，如果存在未获取到的权限 则返回
     * @return list 未获取到的权限
     */
    private fun hasPermission(permissions: MutableList<String>): MutableList<String> {
        val list = mutableListOf<String>()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            permissions.forEach {
                if (ContextCompat.checkSelfPermission(this, it)
                        != PackageManager.PERMISSION_GRANTED) {
                    list.add(it)
                }
            }
        }
        /**
         * ActivityCompat.shouldShowRequestPermissionRationale(activity, it)
         * 无法判断是否有权限被设置为永久禁止
         * 第一次请求权限时ActivityCompat.shouldShowRequestPermissionRationale=false;
         * 第一次请求权限被禁止，但未选择【不再提醒】ActivityCompat.shouldShowRequestPermissionRationale=true;
         * 允许某权限后ActivityCompat.shouldShowRequestPermissionRationale=false;
         * 禁止权限，并选中【禁止后不再询问】ActivityCompat.shouldShowRequestPermissionRationale=false；
         */
        return list
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            requestPermissionRequestCode -> {
                val grands = mutableListOf<String>()
                val denied = mutableListOf<String>()
                val size = if (permissions.size > grantResults.size) {
                    grantResults.size
                } else {
                    permissions.size
                }
                for (i in size - 1 downTo 0) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        // 全部同意
                        grands.add(permissions[i])
                    } else {
                        denied.add(permissions[i])
                    }
                }

                if (grands.size == grantResults.size) {
                    // 全部同意
                    permissionsGrant()
                } else {
                    mPermissionBuilder?.view.whatCanDo(
                            { // 存在权限未同意
                                permissionsDenied(denied)
                            },
                            { // TODO 可以扩展增加自定义视图
                                (it as Dialog).show()
                            }
                    )
                }
            }
            else -> {
                // Ignore all other requests.
            }
        }
    }

}