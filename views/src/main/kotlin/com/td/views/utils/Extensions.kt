package com.td.views.utils

/**
 * Description :
 * Created by Wang Yue on 2019-12-30.
 * Phone ：18610413765
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
