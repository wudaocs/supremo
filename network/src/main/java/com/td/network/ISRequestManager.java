package com.td.network;

import com.td.network.entity.GetEntity;
import com.td.network.entity.PostEntity;

/**
 * Description : 网络管理接口
 * Created by Mc on 2018/7/24.
 * Job number：135033
 * Phone ：18601413765
 * Email：wangyue@syswin.com
 * Person in charge : Mc
 * Leader：Mc
 */
public interface ISRequestManager<T> {

    void post(PostEntity<T> postEntity);

    void get(GetEntity<T> getEntity);
}
