package com.td.views.vgloader

import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

/**
 * Description : vg 取自 Van Gogh 梵高
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
 */
object VGLoader {

    fun show(builder: VGBuilder) {
        builder.view?.run {
            Glide.with(builder.context)
                    .apply {
                        asType(this, builder.vgType)
                    }
                    .load(builder.url).apply {
                        asScaleType(builder)
                        builder.getPlaceHolder()?.run {
                            placeholder(this)
                        }
                        builder.getError()?.run {
                            error(this)
                        }
                        setDiskCache(builder)
                        listener(builder)
                    }.into(this)
        }
    }

    /**
     * 设置缓存方式
     */
    private fun RequestBuilder<Drawable>.setDiskCache(builder: VGBuilder) {
        builder.getDiskCacheStrategyType()?.run {
            when (this) {
                DiskCacheStrategyType.ALL -> diskCacheStrategy(DiskCacheStrategy.ALL)
                DiskCacheStrategyType.NONE -> diskCacheStrategy(DiskCacheStrategy.NONE)
                DiskCacheStrategyType.DATA -> diskCacheStrategy(DiskCacheStrategy.DATA)
                DiskCacheStrategyType.RESOURCE -> diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                DiskCacheStrategyType.AUTOMATIC -> diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            }
        }
    }

    /**
     * 设置显示类型
     */
    private fun asType(requestManager: RequestManager, vgType: VGType?): RequestManager {
        return requestManager.apply {
            vgType?.run {
                when (this) {
                    VGType.GIF -> asGif()
                    VGType.FILE -> asFile()
                    VGType.DRAWABLE -> asDrawable()
                    VGType.BITMAP -> asBitmap()
                }
            }
        }
    }

    /**
     * 设置缩放类型
     */
    private fun RequestBuilder<Drawable>.asScaleType(builder: VGBuilder) {
        if (builder.scaleType != null) {
            when (builder.scaleType) {
                VGScaleType.CENTER_CROP -> centerCrop()
                VGScaleType.CENTER_INSIDE -> centerInside()
                VGScaleType.FIT_XY -> fitCenter()
                VGScaleType.FIT_CENTER -> fitCenter()
                VGScaleType.CIRCLE -> circleCrop()
            }
        }
    }

    /**
     * 设置回调监听
     */
    private fun RequestBuilder<Drawable>.listener(builder: VGBuilder) {
        builder.listener?.run {
            listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    builder.listener?.onLoadFailed()
                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    builder.listener?.complete()
                    return true
                }

            })
        }
    }

}