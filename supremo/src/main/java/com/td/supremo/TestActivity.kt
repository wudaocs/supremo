package com.td.supremo

import android.text.InputFilter
import android.widget.EditText
import android.widget.TextView
import com.td.base.utils.edittext.InputTextBuilder
import com.td.base.utils.edittext.InputTextErrorListener
import com.td.base.utils.edittext.InputTextFilter
import com.td.base.view.BaseActivity

/**
 * Description :
 * Created by Wang Yue on 2018/9/25.
 * Job number：135033
 * Phone ：18610413765
 * Email：wangyue@syswin.com
 * Person in charge :Wang Yue
 * Leader：Ding Lei
 */
class TestActivity : BaseActivity() {

    private var mTextView: TextView? = null
    private var mEditText: EditText? = null

    override fun getContentViewLayoutID(): Int {
        return R.layout.activity_start
    }

    override fun findView() {
        super.findView()
        mTextView = findViewById(R.id.tv_activity_start_prompt)
        mEditText = findViewById(R.id.et_activity_start_prompt)
        mEditText!!.filters = arrayOf<InputFilter>(InputTextFilter(InputTextBuilder()
                .setLength(10)
//                .setFormat("abc")
                .setInputTextListener(object : InputTextErrorListener {
                    override fun inputText(size: Int) {
                    }

                    override fun inputText(content: String?, inputWords: String?) {
                    }

                    override fun inputError() {
                    }
                }).build()))
    }

    override fun start() {
        super.start()
        //        mTextView.postDelayed(new Runnable() {
        //            @Override
        //            public void run() {
        //                startActivity(new Intent(TestActivity.this, TestActivity.class));
        //            }
        //        },100);
    }
}
