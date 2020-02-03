package com.td.utils.edittext;

/**
 * Description : 输入文本监听
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
 */
public interface InputTextErrorListener extends InputTextListener,
        InputTextSizeListener, InputTextContentListener {

    // 输入格式错误
    void inputError();
}
