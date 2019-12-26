package com.td.supremo

import android.content.Intent
import android.text.InputFilter
import android.widget.EditText
import android.widget.TextView
import com.td.utils.edittext.InputTextBuilder
import com.td.utils.edittext.InputTextErrorListener
import com.td.utils.edittext.InputTextFilter
import com.td.base.view.BaseActivity
import com.td.supremo.mvp.TestMvpActivity

/**
 * Description : 应用启动类
 * Created by Wang Yue on ${DATE}.
 * Job number：135033
 * Phone ：18610413765
 * Email：wangyue@syswin.com
 * Person in charge :Wang Yue
 * Leader：Ding Lei
 */
class StartActivity : BaseActivity() {

    private var mTextView: TextView? = null
    private var mEditText: EditText? = null

    override fun getContentViewLayoutID(): Int {
        return R.layout.activity_start
    }

    override fun findView() {
        mTextView = findViewById(R.id.tv_activity_start_prompt)
        mEditText = findViewById(R.id.et_activity_start_prompt)
        mEditText!!.filters = arrayOf<InputFilter>(InputTextFilter(InputTextBuilder().setFormat("abc")
                .setInputTextListener(object : InputTextErrorListener {
                    override fun inputText(size: Int) {

                    }

                    override fun inputText(content: String,inputWords: String) {

                    }

                    override fun inputError() {

                    }
                }).build()))
    }

    override fun creating() {
        super.creating()
        mTextView!!.postDelayed({ startActivity(Intent(this@StartActivity, TestMvpActivity::class.java)) }, 100)
    }
}
