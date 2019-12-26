package com.td.supremo.mvp

import com.td.base.BasePresenter
import kotlinx.coroutines.delay

/**
 * Description :
 * Created by Wang Yue on 2019-12-26.
 * Phone ：18610413765
 */
class TestMvpPresenter : BasePresenter() {

    fun getData() {
        ct {
            val data = plus()
//            val data = withContext(Dispatchers.IO){plus()}
            ui {
                getView()?.updateText(data.toString())
            }
        }
    }

    fun getView(): TestMvpActivity? = mView as TestMvpActivity

    suspend fun plus(): Int {
        delay(10000)
        return 221
    }

}