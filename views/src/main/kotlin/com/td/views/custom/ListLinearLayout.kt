package com.td.views.custom

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.td.exts.*
import com.td.views.R

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
        wh(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, Gravity.CENTER_VERTICAL)
        list?.forEach {
            //            val view = View.inflate(context, R.layout.view_ll_list_item, null)
//            it.icon.notZero {
//                view.findViewById<ImageView>(R.id.iv_view_ll_list_item_icon).bgr(it.icon)
//            }
//            it.name.notNull {
//                view.findViewById<ImageView>(R.id.mtv_view_ll_list_item_name).textExt(it.name)
//            }
//            // 加载元素
//            addView(view)
            addView(LinearLayout(context).also { ll ->
                // 子元素均为水平方向
                ll.orientation = HORIZONTAL
                ll.gravity = Gravity.CENTER_VERTICAL
                it.icon.notZero {
                    ll.addView(ImageView(context).wh(25, 25, LinearLayout::class.java).bgr(it.icon).apply {
                        (this?.layoutParams as LayoutParams).setMargins(dp(20).toInt(), 0, 0, 0)
                    })
                }
                it.name.notNull {
                    ll.addView(TextView(context).wh(height = 30, cls = LinearLayout::class.java).textExt(it.name)
                            .gravityExt(Gravity.CENTER_VERTICAL)?.apply {
                                setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16f)
                                typeface = Typeface.DEFAULT_BOLD
                                (this.layoutParams as LayoutParams).setMargins(dp(20).toInt(), 0, 0, 0)
                            })
                    it.llItemClickListener.notNull {
                        ll.setOnClickListener { _ -> it.llItemClickListener?.click(it.name!!) }
                    }
                }
            }.wh(LayoutParams.MATCH_PARENT, 50))
        }
    }

}

data class LLEntity(var name: String? = "", var icon: Int? = 0, var llItemClickListener: OnLLItemClickListener? = null)

interface OnLLItemClickListener {
    fun click(name: String)
}