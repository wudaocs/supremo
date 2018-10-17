package com.td.base.utils.edittext;

/**
 * Description : 输入文本监听
 * Created by Wang Yue on 2018/10/8.
 * Job number：135033
 * Phone ：18610413765
 * Email：wangyue@syswin.com
 * Person in charge :Wang Yue
 * Leader：Ding Lei
 */
public interface InputTextErrorListener extends InputTextListener,
        InputTextSizeListener, InputTextContentListener {

    // 输入格式错误
    void inputError();
}
