package com.td.utils.edittext;

/**
 * Description :
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
 */
public interface InputTextContentListener extends InputTextListener {

    // 输入内容
    void inputText(String content, String inputWords);
}
