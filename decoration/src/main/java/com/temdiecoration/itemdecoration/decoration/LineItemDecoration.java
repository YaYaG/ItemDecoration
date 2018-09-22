package com.temdiecoration.itemdecoration.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.temdiecoration.itemdecoration.utils.DUtils;

/**
 * 姓名: 王进亚
 * 时间： 2018/8/29
 * 描述：
 */

public class LineItemDecoration extends RecyclerView.ItemDecoration {
    private int lineHeight = 1;
    private final Paint mPaint;

    public LineItemDecoration(Context context,int lineHeightDp, int lineColor) {
        this.lineHeight = DUtils.dp2px(context,lineHeightDp);
        mPaint = new Paint();
        mPaint.setColor(lineColor);
    }

    //默认 是 灰色
    public LineItemDecoration(Context context) {
        this.lineHeight = DUtils.dp2px(context,1);
        mPaint = new Paint();
        mPaint.setColor(Color.GRAY);
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int childAdapterPosition = parent.getChildAdapterPosition(view);
        if(childAdapterPosition > 0){
            outRect.set(0, lineHeight, 0, 0);
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int right = parent.getRight() - parent.getPaddingRight();
        int left = parent.getLeft() + parent.getPaddingLeft();

        int itemCount = parent.getChildCount();
        for (int i = 0; i < itemCount; i++) {
            View view = parent.getChildAt(i);

            int top = view.getTop();
            int bottom = top - lineHeight;

            c.drawRect(left, top, right, bottom, mPaint);
        }
    }
}
