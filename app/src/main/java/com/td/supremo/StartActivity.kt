package com.td.supremo

import android.os.CountDownTimer
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.td.base.KBaseActivity
import com.td.base.open
import com.td.supremo.home.HomeDrawerActivity
import com.td.views.vgloader.VGBuilder
import com.td.views.vgloader.VGLoader
import com.td.views.vgloader.VGScaleType
import kotlinx.android.synthetic.main.activity_start.*


/**
 * Description : 应用启动类
 * Created by Wang Yue on ${DATE}.
 * Job number：135033
 * Phone ：18610413765
 * Email：wangyue@syswin.com
 * Person in charge :Wang Yue
 * Leader：Ding Lei
 */
class StartActivity : KBaseActivity() {

    private var imageView: ImageView? = null
    private var btnJump: Button? = null

    private val countTimer = object : CountDownTimer(0, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            btnJump?.text = (millisUntilFinished / 1000 + 1).toString()
        }

        override fun onFinish() {
            open(HomeDrawerActivity::class.java)
            finish()
        }
    }

    override fun getContentViewLayoutID(): Int {
        return R.layout.activity_start
    }

    override fun findView() {
        imageView = iv_activity_start_background
        btnJump = btn_activity_start_jump
        btnJump?.setOnClickListener {
            countTimer.cancel()
            open(HomeDrawerActivity::class.java)
            finish()
        }
    }

    override fun onCreating() {
        super.onCreating()
        VGLoader.show(VGBuilder(this, "http://cdn.duitang.com/uploads/item/201410/27/20141027205016_naAYv.thumb.700_0.gif", imageView).apply {
            placeholder(R.drawable.icon_image_loading)
            scaleType = VGScaleType.CENTER_CROP
        })
        countTimer.start()
    }


    fun test() {
        imageView?.run {
            Glide.with(this@StartActivity).asGif().transition(DrawableTransitionOptions.withCrossFade()).diskCacheStrategy
            Glide.with(this@StartActivity).asGif().transition(GenericTransitionOptions.withNoTransition()).diskCacheStrategy
            Glide.with(this@StartActivity).load("http://dmimg.5054399.com/allimg/pkm/pk/22.jpg").centerCrop().into(this)
        }
    }

    override fun isHiddenTitle(): Boolean = true

    override fun isHiddenNavigationBar(): Boolean = true

    override fun close() {
        super.close()
        countTimer.cancel()
    }
}
