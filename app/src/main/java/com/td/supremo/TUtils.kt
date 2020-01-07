package com.td.supremo

/**
 * Description :
 * Created by Wang Yue on 2018/9/25.
 * Job number：135033
 * Phone ：18610413765
 * Email：wangyue@syswin.com
 * Person in charge :Wang Yue
 * Leader：Ding Lei
 */
class TUtils {

    fun loopTest(){
         for (i in 1..3){
            println("i $i")
             abc@for (j in 1..3){
                println("j $j")
                for (k in 1..3) {
                    println("k $k")
                    if (k == 2) break@abc
                }
            }
        }
    }


    companion object {
        fun teststatic(){}
    }

    fun sum(a: Int, b: String = "1"): Int? {
        return a + b.toInt()
    }

    fun forString(args: List<String>, a: Int): Int {
        val items = args
        if (a == 1) {
            for (item in items) {
                println(item)
            }
        } else if(a == 2){
            for (index in items.indices) {
                println("item at $index is ${items[index]}")
            }
        }
        return items.size
    }

    fun ranges() {
        for (x in 1..10 step 2) {
            println(x)
        }
        for (x in 9 downTo 0 step 3) {
            println(x)
        }
        for (x in 1 until 2){
            println(x)
        }
    }

    fun whenTest(){
        val item = mutableSetOf<String>()
//        val item = setOf("apple", "banana", "kiwi", "orange")
        when {
            "orange" in item -> println("juicy")
            "apple" in item -> println("apple is fine too")
            else -> item.add("banana")
        }
        println(item.toString())
    }
}