package com.td.views.custom.bottom

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.td.exts.*
import com.td.views.R

/**
 * Description :
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
 */
class BottomNavigationView : LinearLayout {

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    private val mHeight = 50

    private val iconList: MutableList<ImageView> = mutableListOf()
    private val nameList: MutableList<TextView> = mutableListOf()

    init {
        initView()
    }

    private fun initView() {

    }

    fun setMenu(list: MutableList<BNEntity>?) {
        orientation = HORIZONTAL
        bgc(R.color.transparent)
        wh(LayoutParams.MATCH_PARENT, mHeight)
        list?.forEach {
            addView(buildItem(it))
        }
    }

    private fun buildItem(item: BNEntity) = LinearLayout(context).also { lp ->
        lp.orientation = VERTICAL
        bgc(R.color.transparent)
        // 外层
        lp.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f).apply {
            lp.gravity = Gravity.CENTER
        }
        // 里层
        lp.addView(LinearLayout(context).also { ll ->
            ll.gravity = Gravity.CENTER
            val imageHeight = if (TextUtils.isEmpty(item.name)) {
                30
            } else {
                20
            }
            item.icon.notZero {
                // 添加图片
                ll.addView(ImageView(context).wh(imageHeight, imageHeight, LinearLayout::class.java).bgr(item.icon)?.apply {
                    iconList.add(this)
                })
            }

            item.name.notNull {
                // 添加文字
                ll.addView(TextView(context).wh(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, LinearLayout::class.java)?.also {
                    it.gravity = Gravity.CENTER
                    nameList.add(it)
                }.showTextColor(item.textColorSelect, item.textColor, item.isSelector).textExt(item.name))
            }
            ll.setOnClickListener { item.block() }
        }.wh(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT))

    }
}

data class BNEntity(var icon: Int? = null, var name: String? = null, var url: String? = null, var block : () -> Unit = {}) {
    // out of range 是否超过边界
    var oor: Boolean = false
    // 是否设置selector
    var isSelector = false

    var scale: Float? = 1f

    var textColor = R.color.text_gray

    var textColorSelect = R.color.black

}
