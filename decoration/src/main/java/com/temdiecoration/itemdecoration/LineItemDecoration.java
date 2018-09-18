package com.temdiecoration.itemdecoration;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 姓名: 王进亚
 * 时间： 2018/8/29
 * 描述：
 */

public class LineItemDecoration extends RecyclerView.ItemDecoration {
    private int lineHeight = 1;
    private final Paint mPaint;

    public LineItemDecoration(int lineHeight, int lineColor) {
        this.lineHeight = lineHeight;

        mPaint = new Paint();
        mPaint.setColor(lineColor);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0, lineHeight, 0, 0);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int right = parent.getRight();
        int left = parent.getLeft();

        int itemCount = parent.getChildCount();

        for (int i = 0; i < itemCount; i++) {
            View view = parent.getChildAt(i);

            int top = view.getTop();
            int bottom = top - lineHeight;

            c.drawRect(left, top, right, bottom, mPaint);
        }
    }
}
