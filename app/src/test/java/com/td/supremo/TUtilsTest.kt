package com.td.supremo

import org.junit.Test

/**
 * Description : 
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
 */
class TUtilsTest {

    @Test
    fun loopTest(){
        TUtils().loopTest()
    }

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