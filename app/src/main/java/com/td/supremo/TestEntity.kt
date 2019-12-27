package com.td.supremo

import java.io.Serializable

/**
 * Description :
 * Created by Wang Yue on 2018/10/17.
 * Job number：135033
 * Phone ：18610413765
 * Email：wangyue@syswin.com
 * Person in charge :Wang Yue
 * Leader：Ding Lei
 */
data class TestEntity(var name: String = "", var sex: Int = 0) : Serializable {

    override fun toString(): String {
        return "[TestEntity(name = $name, sex = $sex)]"
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}
