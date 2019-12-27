package com.td.utils.edittext;

import android.text.TextUtils;

/**
 * Description : edittext 参数类
 * Created by Wang Yue on 2018/10/8.
 * Job number：135033
 * Phone ：18610413765
 * Email：wangyue@syswin.com
 * Person in charge :Wang Yue
 * Leader：Ding Lei
 */
public class InputTextBuilder {

    private int length;
    private String format;
    private boolean isZhNumber;
    private InputTextListener inputTextListener;

    public int getLength() {
        return length;
    }

    /**
     * 设置固定长度
     *
     * @param length 需要匹配的长度
     */
    public InputTextBuilder setLength(int length) {
        this.length = length;
        return this;
    }

    public String getFormat() {
        return format;
    }

    /**
     * 设置固定字符
     *
     * @param format 需要匹配的字符
     */
    public InputTextBuilder setFormat(String format) {
        if (!TextUtils.isEmpty(format)) {
            this.format = format;
        }
        return this;
    }

    public boolean isZhNumber() {
        return isZhNumber;
    }

    public InputTextBuilder setZhNumber(boolean zhNumber) {
        isZhNumber = zhNumber;
        return this;
    }

    public InputTextListener getInputTextListener() {
        return inputTextListener;
    }

    public InputTextBuilder setInputTextListener(InputTextListener inputTextListener) {
        this.inputTextListener = inputTextListener;
        return this;
    }

    public InputTextBuilder setInputTextListener(InputTextErrorListener inputTextListener) {
        this.inputTextListener = inputTextListener;
        return this;
    }

    public InputTextBuilder build(){
        return this;
    }
}
