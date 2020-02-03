package com.td.base

/**
 * Description :
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
 */
data class BarBuilder(var title : String){
    var subTitle : String?= null
    var titleTextColor : Int? = null
    var logoResId : Int? = null
    var isBack : Boolean = true
    var isHome : Boolean = false
}