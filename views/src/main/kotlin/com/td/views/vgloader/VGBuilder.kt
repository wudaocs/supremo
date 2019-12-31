package com.td.views.vgloader

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.load.resource.drawable.DrawableDecoderCompat

/**
 * Description :
 * Created by Wang Yue on 2019-12-30.
 * Phone ：18610413765
 */
data class VGBuilder(val context: Context, val url: String? = null, val view: ImageView?){
    var scaleType : VGScaleType? = null

    var vgType : VGType? = null

    // 占位符 控制
    private var placeholderDrawable : Drawable? = null
    fun placeholder(resourceId : Int){
        placeholderDrawable = DrawableDecoderCompat.getDrawable(context, resourceId, context.theme)
    }

    fun placeholder(drawable : Drawable){
        placeholderDrawable = drawable
    }

    fun getPlaceHolder() = placeholderDrawable

    // 加载失败控制
    private var error : Drawable? = null
    fun error(drawable : Drawable){
        error = drawable
    }
    fun error(resourceId : Int){
        error = DrawableDecoderCompat.getDrawable(context, resourceId, context.theme)
    }
    fun getError() = error

    var listener : VGListener? = null

    // 是否支持加载缓存
    private var diskCacheStrategyType : DiskCacheStrategyType? = null
    fun diskCacheStrategyType(diskCacheStrategy : DiskCacheStrategyType){
        diskCacheStrategyType = diskCacheStrategy
    }
    fun getDiskCacheStrategyType() = diskCacheStrategyType

    fun transition(transition : VGTransitionBuilder? = null){

    }
}

enum class VGScaleType{
    CENTER_CROP,
    CENTER_INSIDE,
    FIT_XY,
    FIT_CENTER,
    CIRCLE
}

enum class VGType {
    DRAWABLE,
    BITMAP,
    GIF,
    FILE
}

enum class VGTransitionOption{
    GENERIC,
    DRAWABLE,
    BITMAP
}

enum class DiskCacheStrategyType {
    ALL,
    NONE,
    DATA,
    RESOURCE,
    AUTOMATIC
}

interface VGListener {

    fun onLoadFailed()

    fun complete()
}