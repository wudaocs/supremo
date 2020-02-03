package com.td.views.utils

/**
 * Description : 
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
 */
fun <T : Any?> T.todo(block: () -> T): T? {
    return this?.run {
        block()
    }
}

fun String?.exec(block: (t: String) -> String?): String? {
    return this?.run {
        block(this)
    }
}
