package com.td.base.view;

/**
 * Description : 业务调用基类
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
 */
public class BaseActivity extends RootActivity {

    protected void findView(){ }

    @Override
    protected void creating() {
        super.creating();
        findView();
    }
}
