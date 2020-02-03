package com.td.network;

import com.td.network.entity.GetEntity;
import com.td.network.entity.PostEntity;

/**
 * Description : 网络返回数据格式
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
 */
public interface ISRequestManager<T> {

    void post(PostEntity<T> postEntity);

    void get(GetEntity<T> getEntity);
}
