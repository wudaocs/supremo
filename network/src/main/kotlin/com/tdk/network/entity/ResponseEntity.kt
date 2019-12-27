package com.tdk.network.entity

import java.io.Serializable

/**
 * Description :
 * Created by Wang Yue on 2019-11-22.
 * Phone ：18610413765
 */
class ResponseEntity <T> : Serializable {
    var code: Int = 0
    var msg: String? = null
    var timestamp: Long = 0
    var popInfo: Any? = null
    var data : T? = null
}