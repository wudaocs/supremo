package com.td.base

/**
 * Description :
 * Created by Wang Yue on 2020-01-02.
 * Phone ：18610413765
 */
data class BarBuilder(var title : String){
    var subTitle : String?= null
    var titleTextColor : Int? = null
    var logoResId : Int? = null
    var isBack : Boolean = true
    var isHome : Boolean = false
}