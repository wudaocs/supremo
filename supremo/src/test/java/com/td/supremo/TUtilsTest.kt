package com.td.supremo

import org.junit.Test
import java.util.concurrent.TimeUnit

/**
 * Description :
 * Created by Wang Yue on 2018/9/25.
 * Job number：135033
 * Phone ：18610413765
 * Email：wangyue@syswin.com
 * Person in charge :Wang Yue
 * Leader：Ding Lei
 */
class TUtilsTest {

    @Test
    fun sum() {
        println(TUtils().sum(3))
    }

    @Test
    fun forString(){
        println(TUtils().forString(listOf("apple","banner","tear"),2))
    }

    @Test
    fun range(){
       TUtils().ranges()
    }

    @Test
    fun whenTest(){
        TUtils().whenTest()
    }
}