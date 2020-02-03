package com.td.supremo.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.td.base.KBaseFragment
import com.td.supremo.R

/**
 * Description : 主页Fragment
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
 */
class HomeFragment : KBaseFragment() {

    override fun getContentView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun created() {

    }


}