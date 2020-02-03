package com.td.base.contract;

import com.td.base.interfaces.IBaseView;
import com.td.base.interfaces.IPresenter;

/**
 * Description : mvp接口
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
 */
public interface RootContract {

    interface RootView extends IBaseView {
    }

    interface Presenter extends IPresenter {

    }
}
