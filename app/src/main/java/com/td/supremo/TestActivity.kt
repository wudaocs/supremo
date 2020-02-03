package com.td.supremo

import android.widget.EditText
import android.widget.TextView
import com.td.base.view.BaseActivity

/**
 * Description : 测试类
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
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
