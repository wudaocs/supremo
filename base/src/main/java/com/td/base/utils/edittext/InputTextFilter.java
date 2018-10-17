package com.td.base.utils.edittext;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;

/**
 * Description : 通用设置固定字符格式适配器
 * Created by Wang Yue on 2018/10/8.
 * Job number：135033
 * Phone ：18610413765
 * Email：wangyue@syswin.com
 * Person in charge :Wang Yue
 * Leader：Ding Lei
 */
public class InputTextFilter implements InputFilter {

    // 对比格式，满足格式允许输入
    private String FORMAT = "";

    // 默认设置的字符长度
    private int LENGTH = 0;

    private boolean isZhNumber = false;
    // 回调监听
    private InputTextListener mInputTextListener;

    @SuppressWarnings("unused")
    private InputTextFilter() {
    }

    public InputTextFilter(InputTextBuilder inputTextBuilder) {
        if (inputTextBuilder != null) {
            FORMAT = inputTextBuilder.getFormat();
            LENGTH = inputTextBuilder.getLength();
            isZhNumber = inputTextBuilder.isZhNumber();
            mInputTextListener = inputTextBuilder.getInputTextListener();
        }
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

        Log.v("InputTextFilter", "输入的文字source= " + source + "  开始位置start= " + start
                + "  结束位置end= " + end + "  当前显示的内容dest= " + dest + "  当前开始位置dstart= " + dstart + "  当前结束位置dend= " + dend);
        boolean f = !TextUtils.isEmpty(FORMAT);
        boolean l = LENGTH != 0;
        // 显示的字符
        StringBuilder showWords = new StringBuilder();
        // 添加操作
        if (source.length() >= 0) {
            if (!TextUtils.isEmpty(source) && (f || l)) {// 两个条件任意符合则拦截
                if (f) { // 字符限制,包含无效字符则直接返回
                    if (isBreak(source)) return dest;
                }
                if (l) {// 长度限制
                    source = splitContent(source, end, dest, dstart, dend);
                }
            }
            // 添加之后的字符
            if (l) {
                // 三种替换方式 1. 从头部替换 2. 从尾部替换 3. 中间替换
                if (dstart == 0) {
                    // 第一种
                    showWords.append(dest).replace(start, end, source.toString());
                } else if (end - dend <= 1) {
                    // 第三种
                    showWords.append(dest.toString().substring(0, dstart))
                            .append(source)
                            .append(dest.toString().substring(dstart, dend));
                } else {
                    // 第二种
                    showWords.append(dest).insert(dstart, source);
                }
            } else {
                Log.v("InputTextFilter", "真正输入的内容= " + getRealWords(source, end, dstart, dend));
                showWords.append(dest).insert(dend, getRealWords(source, end, dstart, dend));
            }
        } else {// 删除操作
            showWords.append(dest.toString().substring(0, dstart));
            if (dend < dest.length()) {
                showWords.append(dest.toString().substring(dend, dest.length()));
            }
        }
        callback(source, showWords);
        return TextUtils.isEmpty(source) ? null : source;
    }

    /**
     * 验证字符是否符合格式
     *
     * @param source 字符
     * @return 是否符合格式
     */
    private boolean isBreak(CharSequence source) {
        boolean isBreak = true;
        if (!TextUtils.isEmpty(FORMAT)) {
            for (int i = 0, fl = FORMAT.length() - 1; i < fl; i++) {
                if (TextUtils.equals(String.valueOf(FORMAT.charAt(i)), source) || FORMAT.contains(source)) {
                    Log.v("InputTextFilter", "输入内容不包含无效字符");
                    isBreak = false;
                }
            }
        }
        if (isBreak) {
            if (mInputTextListener != null && mInputTextListener instanceof InputTextErrorListener) {
                ((InputTextErrorListener) mInputTextListener).inputError();
            }
            return true;
        }
        return false;
    }

    /**
     * 回调
     *
     * @param content   变化内容
     * @param showWords 显示内容
     */
    private void callback(CharSequence content, StringBuilder showWords) {
        int size = isZhNumber ? getWordCount(showWords.toString()) : showWords.length();
        Log.v("InputTextFilter", "content= " + content + "  size=" + size + " showWords= " + showWords + "|");
        if (mInputTextListener != null) {
            if (mInputTextListener instanceof InputTextErrorListener) {
                ((InputTextErrorListener) mInputTextListener).inputText(size);
                ((InputTextErrorListener) mInputTextListener).inputText(showWords.toString(),
                        content.toString());
            } else if (mInputTextListener instanceof InputTextContentListener) {
                ((InputTextContentListener) mInputTextListener).inputText(showWords.toString(),
                        content.toString());
            } else if (mInputTextListener instanceof InputTextSizeListener) {
                ((InputTextSizeListener) mInputTextListener).inputText(size);
            }
        }
    }

    /**
     * 分割参数
     *
     * @param source 数据源
     * @param end    字符结束位置
     * @return 需要插入的字符数据
     */
    private CharSequence splitContent(CharSequence source, int end, Spanned dest, int dstart, int dend) {
        CharSequence realWords = getRealWords(source, end, dstart, dend);
        if (!(realWords.length() + dest.length() <= LENGTH)) {
            // 超过长度截取
            realWords = realWords.subSequence(0, LENGTH - dest.length());
        }
        return realWords;
    }

    /**
     * 获取真实输入字符
     *
     * @param source 输入源
     * @param dend   位数
     * @return 真实输入内容
     */
    private CharSequence getRealWords(CharSequence source, int end, int dstart, int dend) {
        return dstart == 0 ? source : source.subSequence(source.length() - (dend - dstart), source.length());
//        return end == (dend - dstart) ? source : dstart == 0 ? source :source.subSequence(dend, source.length());
    }

    /**
     * 获取字符长度 中文两个英文一个
     *
     * @param s 原始字符
     * @return 字符长度
     */
    private int getWordCount(String s) {
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            int ascii = Character.codePointAt(s, i);
            if (ascii >= 0 && ascii <= 255)
                length++;
            else
                length += 2;
        }
        return length;
    }
}
