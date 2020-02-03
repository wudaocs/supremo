@file:Suppress("DEPRECATION", "unused")

package com.td.base

import android.content.Intent
import android.text.TextUtils
import androidx.appcompat.app.ActionBar
import com.td.utils.StatusBarUtil

/**
 * Description :
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
 */
fun KRootActivity?.open(cls: Class<out KRootActivity>, block: Intent.() -> Unit = {}, finish : Boolean = false) {
    this?.run {
        val intent = Intent(this, cls)
        intent.block()
        startActivity(intent)
        if (finish){
            finish()
        }
    }
}

fun KRootActivity?.openForResult(cls: Class<out KRootActivity>, block: Intent.() -> Unit = {}, requestCode: Int = KRootActivity.REQUEST_CODE, finish : Boolean = false) {
    this?.run {
        val intent = Intent(this, cls)
        intent.block()
        startActivityForResult(intent, requestCode)
        if (finish){
            finish()
        }
    }
}

fun KRootActivity.setActionBarColorMode(idDark: Boolean) {
    if (idDark) {
        StatusBarUtil.setDarkMode(this)
    } else {
        StatusBarUtil.setLightMode(this)
    }
}

fun KRootActivity?.actionBar(builder: BarBuilder, block: () -> Unit = {}) {
    this?.run {
        val actionBar: ActionBar? = supportActionBar
        actionBar?.run {
            title = builder.title
            if (!TextUtils.isEmpty(builder.subTitle)) {
                subtitle = builder.subTitle
            }
            builder.titleTextColor?.run {
                titleColor = this
            }
            builder.logoResId?.run {
                setLogo(this)
            }

            if (builder.isBack) {
                setDisplayHomeAsUpEnabled(true)
                setHomeButtonEnabled(true)
            }
        }
    }

}