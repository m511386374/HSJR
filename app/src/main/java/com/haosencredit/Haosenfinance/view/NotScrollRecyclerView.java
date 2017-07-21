package com.haosencredit.Haosenfinance.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * 禁止滑动RecyclerView
 * Created by caoqianghui on 2016/10/11.
 */

public class NotScrollRecyclerView extends RecyclerView {

    public NotScrollRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //设置不滚动
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
