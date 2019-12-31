package com.td.views

import com.td.views.utils.exec
import com.td.views.utils.todo
import org.junit.Test

class ExtensionsTest {
    @Test
    fun extExec() {
        var obj: Any? = null
        obj = obj.todo {
            "12341234"
        }
        println("$obj")
        obj = "1234"
        obj = obj.exec {
            "23232323"
        }
        println("$obj")
        obj = obj.todo {
            123456
        }
        println("$obj")
    }
}