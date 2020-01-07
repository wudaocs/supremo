package com.td.supremo

import android.text.InputFilter
import android.widget.EditText
import android.widget.TextView
import com.td.utils.edittext.InputTextBuilder
import com.td.utils.edittext.InputTextErrorListener
import com.td.utils.edittext.InputTextFilter
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
        return R.layout.activity_home
    }

    override fun findView() {
        super.findView()
    }

    override fun creating() {
        super.creating()
        //        mTextView.postDelayed(new Runnable() {
        //            @Override
        //            public void run() {
        //                startActivity(new Intent(TestActivity.this, TestActivity.class));
        //            }
        //        },100);
        var testEntity : TestEntity = TestEntity("Kotlin",0)
        var java = testEntity.copy(name = "Java")
    }

    fun test(obj: Any):Int?{
        when("x"){
            "y" -> print("x==1")
        }

        var test = 1 until 6
        var test2 = test.reversed()

        for(i in 1 downTo  10){

        }

        return null
    }
}
