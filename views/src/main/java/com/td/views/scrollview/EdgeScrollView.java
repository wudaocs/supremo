package com.td.views.scrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

/**
 * Description : 上下边缘滚动的ScrollView
 * Created by Mc on 2018/7/25.
 * Job number：135033
 * Phone ：18601413765
 * Email：wangyue@syswin.com
 * Person in charge : Mc
 * Leader：Mc
 */
public class EdgeScrollView extends ScrollView {

    private OnEdgeListener onEdgeListener;
    private View contentView;

    public EdgeScrollView(Context context) {
        this(context, null);
    }

    public EdgeScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EdgeScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        edge();
    }

    public void setOnEdgeListener(final OnEdgeListener onEdgeListener) {
        this.onEdgeListener = onEdgeListener;
        if (onEdgeListener == null) {
            return;
        }
        if (contentView == null) {
            contentView = getChildAt(0);
        }
    }

    private void edge() {
        if (contentView != null && contentView.getMeasuredHeight() <= getScrollY() + getHeight()) {
            if (onEdgeListener != null) {
                onEdgeListener.onBottom();
            }
        } else if (getScrollY() == 0) {
            if (onEdgeListener != null) {
                onEdgeListener.onTop();
            }
        }
    }
}
