package com.tdk.network.entity

/**
 * Description : 网络相应实体类
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
 */
class RequestSignEntity<T> : RequestEntity<T>() {

    init {
        addPostFormSignContent()
    }
}