package com.td.views.vgloader

import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

/**
 * Description :
 * Created by Wang Yue on 2019-12-30.
 * Phone ：18610413765
 */
object PicLoader {

    fun show(builder: PicBuilder) {
        builder.view?.run {
            Glide.with(builder.context)
                    .apply {
                        builder.picType?.run {
                            when(this){
                                PicType.GIF -> asGif()
                                PicType.FILE -> asFile()
                                PicType.DRAWABLE -> asDrawable()
                                PicType.BITMAP -> asBitmap()
                            }
                        }
                    }
                    .load(builder.url).apply {
                if (builder.scaleType != null) {
                    when (builder.scaleType) {
                        PicScaleType.CENTER_CROP -> centerCrop()
                        PicScaleType.CENTER_INSIDE -> centerInside()
                        PicScaleType.FIT_XY -> fitCenter()
                        PicScaleType.FIT_CENTER -> fitCenter()
                        PicScaleType.CENTER_OUTSIDE -> circleCrop()
                    }
                }
                builder.getPlaceHolder()?.run {
                    placeholder(this)
                }
                builder.getError()?.run {
                    error(this)
                }
                builder.listener?.run {
                    listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                            builder.listener?.onLoad()
                            return false
                        }

                        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                            return true
                        }

                    })
                }
            }.into(this)
        }
    }
}