package com.td.base.view;

/**
 * Description : 业务调用基类
 * Created by Wang Yue on 2018/9/25.
 * Job number：135033
 * Phone ：18610413765
 * Email：wangyue@syswin.com
 * Person in charge :Wang Yue
 * Leader：Ding Lei
 */
public class BaseActivity extends RootActivity {

    protected void findView(){ }

    @Override
    protected void start() {
        super.start();
        findView();
    }
}
