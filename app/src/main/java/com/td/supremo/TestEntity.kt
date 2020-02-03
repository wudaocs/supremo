package com.td.supremo

import java.io.Serializable

/**
 * Description : 测试功能数据结构
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
 */
data class TestEntity(var name: String = "", var sex: Int = 0) : Serializable {

    override fun toString(): String {
        return "[TestEntity(name = $name, sex = $sex)]"
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}
