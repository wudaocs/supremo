package com.td.supremo.mvp

import com.td.base.BasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

/**
 * Description :
 * Created by Wang Yue on 2019-12-26.
 * Phone ：18610413765
 */
class TestMvpPresenter : BasePresenter<TestView>() {

    fun getData() {
        ct {
            val data = reduce()
            ui {
                getView()?.updateText(data.toString())
            }
        }
    }

    private suspend fun plus(): Int {
        delay(1000)
        return 221
    }

    private suspend fun reduce(): Int {
        return withContext(Dispatchers.IO) {
            delay(1000)
            2233
        }
    }

}