package com.td.base.view;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.td.base.contract.RootContract;

/**
 * Description : 所有Activity类的基类
 * Created by Mc on 2018/7/25.
 * Job number：135033
 * Phone ：18601413765
 * Email：wangyue@syswin.com
 * Person in charge : Mc
 * Leader：Mc
 */
public abstract class RootActivity extends AppCompatActivity implements RootContract.RootView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initIntent();
        if (getContentViewLayoutID() != 0) {
            setContentView(getContentViewLayoutID());
        } else if (getContentView() != null) {
            setContentView(getContentView());
        }
        creating();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        close();
    }

    protected int getContentViewLayoutID() {
        return 0;
    }

    protected View getContentView() {
        return null;
    }

    /** 传递数据初始化 */
    protected void initIntent() {}

    /** 创建页面中 */
    protected void creating() {}

    /** 刷新页面 */
    protected void refresh(){}

    /** 关闭页面 */
    protected void close(){}
}
