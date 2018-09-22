package com.temdiecoration.itemdecoration.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.temdiecoration.itemdecoration.utils.DUtils;

/**
 * 姓名: 王进亚
 * 时间： 2018/8/29
 * 描述：
 */

public class GridLineItemDecoration extends RecyclerView.ItemDecoration {
    private int spanCount;
    private int dividerWidth;
    private int dividerWidthTop;
    private int dividerWidthBot;
    private Paint dividerPaint;

    public GridLineItemDecoration(Context context ,int lineColor, int spanCount, int dividerWidthDp) {
        this.spanCount = spanCount;

        this.dividerPaint = new Paint();
        this.dividerPaint.setColor(lineColor);

        this.dividerWidth = DUtils.dp2px(context, dividerWidthDp);
        this.dividerWidthTop = dividerWidth / 2;
        this.dividerWidthBot = dividerWidth - dividerWidthTop;
    }

    @Override
    public void getItemOffsets(Rect outRect, View child, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, child, parent, state);

        int pos = parent.getChildAdapterPosition(child);
        int column = (pos) % spanCount;// 计算这个child 处于第几列


        outRect.top = dividerWidthTop;
        outRect.bottom = dividerWidthBot;

        outRect.left = column * dividerWidth / spanCount;
        outRect.right = dividerWidth - (column + 1) * dividerWidth / spanCount;

        Log.e("getItemOffsets", "pos=" + pos + ", column=" + column + " , left=" + outRect.left + ", right="
                + outRect.right + ", dividerWidth=" + dividerWidth);
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(canvas, parent, state);

        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);

            int pos = parent.getChildAdapterPosition(child);
            int column = (pos) % spanCount;// 计算这个child 处于第几列

            int top = child.getTop();
            int right = child.getRight();
            int left = child.getLeft();
            int bottom = child.getBottom();

            //上
            canvas.drawRect(left - column * dividerWidth / spanCount, top - dividerWidthTop, right + dividerWidth - (column + 1) * dividerWidth / spanCount, top + dividerWidthTop, dividerPaint);
            //下
            canvas.drawRect(left - column * dividerWidth / spanCount, bottom, right + dividerWidth - (column + 1) * dividerWidth / spanCount, bottom + dividerWidthBot, dividerPaint);
            //左
            canvas.drawRect(left, top, left - column * dividerWidth / spanCount, bottom, dividerPaint);
            //右
            canvas.drawRect(right, top, right + dividerWidth - (column + 1) * dividerWidth / spanCount, bottom, dividerPaint);
        }
    }
}
