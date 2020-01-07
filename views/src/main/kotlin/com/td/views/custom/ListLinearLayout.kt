package com.td.views.custom

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.td.exts.*
import com.td.views.vgloader.VGBuilder
import com.td.views.vgloader.VGLoader

/**
 * Description :
 * Created by Wang Yue on 2020-01-07.
 * Phone ：18610413765
 */
@SuppressLint("ViewConstructor")
class ListLinearLayout(context: Context?, private val list: MutableList<LLEntity>?, private val direction: Int = VERTICAL,
                       attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        createList()
    }

    private fun createList() {
        // 设置方向
        orientation = direction
        list?.forEach {
            // 加载元素
            addView(LinearLayout(context).apply {
                // 子元素均为水平方向
                orientation = HORIZONTAL
                it.icon.notZero {
                    this.addView(ImageView(context).wh(100, 100).bgr(it.icon))
                }
                it.icon.zero {
                    it.url.notNull {
                        this.addView(ImageView(context).wh(100, 100).apply {
                            VGLoader.show(VGBuilder(context, it.url, (this as ImageView)))
                        })
                    }
                }
                it.name.notNull {
                    this.addView(TextView(context).wh(height = 30).textExt(it.name).gravityExt(Gravity.CENTER))
                }
            }.wh(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
        }
    }

}

data class LLEntity(var name: String? = "", var icon: Int? = 0) {
    var url: String? = null
}