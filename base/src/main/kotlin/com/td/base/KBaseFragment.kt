package com.td.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Description :
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
 */
abstract class KBaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return getContentView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        created()
    }

    abstract fun getContentView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View?

    abstract fun created()
}