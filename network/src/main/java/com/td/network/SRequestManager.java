package com.td.network;

/**
 * Description : 网络请求管理类
 * Created by Mc on 2018/7/24.
 * Job number：135033
 * Phone ：18601413765
 * Email：wangyue@syswin.com
 * Person in charge : Mc
 * Leader：Mc
 */
public class SRequestManager {

    private volatile static ISRequestManager mInstance;

    public static ISRequestManager getInstance() {
        if (mInstance == null){
            synchronized (SRequestManager.class){
                if (mInstance == null){
                    mInstance = new OkHttpRequestManager();
                }
            }
        }
        return mInstance;
    }

}
