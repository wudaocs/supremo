package com.tdk.network


/**
 * Description : 网络请求地址路由类，可优化为路由表
 * Created by Wang Yue on 2019-10-14.
 * Phone ：18610413765
 */
class NetUrlHelper {

    companion object {

        private const val BASE_URL = "http://api.91xinshang.com"

        private var HOST = BASE_URL
//        private var HOST by SpUtil.getInstance().string(SPConstant.KEY_BASE_URL, BASE_URL)

        fun getUrl(key: String): String = HOST.let {
            if (it.endsWith("/")) {
                it.substring(0, it.length - 1)
            } else {
                it
            }
        } +
                when (key) {
                    SELL_ADVERT -> "/xinshang/v2/goods/vest/publishAdv"
                    TO_SHIP -> "/xinshang/v2/goods/vest/toPublish"
                    TO_SHIP_PIC -> "/xinshang/v2/goods/vest/pictureList"
                    TO_SHIP_BRAND -> "/xinshang/v2/goods/vest/brandList"
                    TO_SHIP_SIZE -> "/xinshang/v2/goods/vest/cateSizeChart"
                    TO_SHIP_AUTH -> "/user/auth/check"
                    TO_SHIP_APPLY -> "/v2/user/goods/publish/apply"
                    TO_SHIP_SAVE -> "/user/goods/draft/save"
                    GET_SEND_DELIVERY_STATE -> "/xinshang/v2/goods/vest/booking/getSendDeliveryState"
                    SEND_DELIVERY -> "/xinshang/v2/goods/vest/sendDelivery"
                    VEST_SEND_DELIVERY -> "/v2/gloves/booking/addExpressBooking"
                    TO_GOODS_DETAIL -> "/user/goods/detail"
                    TO_SHIP_MODIFY -> "/v2/user/goods/publish/modify"
                    GET_KDNIAOINFO -> "/delivery/getKdNiaoInfo"
                    STATUS_CHECK -> "/user/statusCheck"
                    else -> ""
                }

        /** 获取发布首页广告接口 */
        const val SELL_ADVERT = "sell_advert"
        /** 获取发布首页主要数据（品类、成色等）接口 */
        const val TO_SHIP = "to_ship"
        /** 获取发布首页品类对应默认图片接口 */
        const val TO_SHIP_PIC = "to_ship_pic"
        /** 获取发布首页品牌接口 */
        const val TO_SHIP_BRAND = "to_ship_brand"
        /** 获取发布首页尺码接口 */
        const val TO_SHIP_SIZE = "to_ship_size"
        /** 认证接口 */
        const val TO_SHIP_AUTH = "to_ship_auth"
        /** 发布接口 */
        const val TO_SHIP_APPLY = "to_ship_apply"
        /** 修改发布数据重新提交 */
        const val TO_SHIP_MODIFY = "to_ship_modify"
        /** 保存草稿 */
        const val TO_SHIP_SAVE = "to_ship_save"
        /** 验证是否可以发货 */
        const val GET_SEND_DELIVERY_STATE = "getSendDeliveryState"
        /** 卖家发货 - 自行寄出 */
        const val SEND_DELIVERY = "order_seller_sendDelivery"
        /** 卖家发货 - 上门取件 */
        const val VEST_SEND_DELIVERY = "goods_vest_sendDelivery"
        /** 获取商品详情 */
        const val TO_GOODS_DETAIL = "goods_detail"
        const val GET_KDNIAOINFO = "getkdniaoinfo"
        /** 获取用户状态 */
        const val STATUS_CHECK = "statusCheck"
    }
}