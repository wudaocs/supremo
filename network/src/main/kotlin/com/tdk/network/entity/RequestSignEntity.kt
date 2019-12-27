package com.tdk.network.entity

/**
 * Description : 网络请求参数类
 * Created by Wang Yue on 2019-10-12.
 * Phone ：18610413765
 * Email：wangyue@91sph.com
 */
class RequestSignEntity<T> : RequestEntity<T>() {

    init {
        addPostFormSignContent()
    }
}