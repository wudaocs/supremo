package com.tdk.network.entity

import java.io.Serializable

/**
 * Description :
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
 */
class ResponseEntity <T> : Serializable {
    var code: Int = 0
    var msg: String? = null
    var timestamp: Long = 0
    var popInfo: Any? = null
    var data : T? = null
}