package com.temdiecoration.itemdecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 姓名: 王进亚
 * 时间： 2018/9/10
 * 描述：
 */

public class TimelineItemDecoration extends RecyclerView.ItemDecoration {
    private final Paint mPaint;
    //ItemView左边的间距
    private final float mOffsetLeft;
    //时间轴结点的半径
    private final float mNodeRadius;
    //ItemView 的向上的间隔
    private float mOffsetTop;

    private Context mContext;

    public TimelineItemDecoration(Context context) {
        mContext = context;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);

        mOffsetLeft = context.getResources().getDimension(R.dimen.timeline_item_offset_left);
        mNodeRadius = context.getResources().getDimension(R.dimen.timeline_item_node_radius);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //第一个ItemView不需要在上面绘制分割线
        if (parent.getChildAdapterPosition(view) != 0) {
            outRect.top = 1;
            mOffsetTop = 1;
        }

        outRect.left = (int) mOffsetLeft;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);

            int childAdapterPosition = parent.getChildAdapterPosition(view);

            float dividerTop = view.getTop() - mOffsetTop;

            if (childAdapterPosition == 0) {//第一个没有
                dividerTop = view.getTop();
            }

            float dividerLeft = parent.getPaddingLeft();

            float dividerBottom = view.getBottom();

            float centerX = dividerLeft + mOffsetLeft / 2;

            float centerY = dividerTop + (dividerBottom - dividerTop) / 2;

            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            mPaint.setColor(Color.RED);

            if (i > 0) {
                //绘制上半部分的轴线
                c.drawLine(centerX, centerY - mNodeRadius, centerX, dividerTop, mPaint);
            }

            if (i < childCount - 1) {
                //绘制下半部分的轴线
                c.drawLine(centerX, centerY + mNodeRadius, centerX, view.getBottom(), mPaint);
            }

            mPaint.setStyle(Paint.Style.STROKE);

            //绘制圆形
            c.drawCircle(centerX, centerY, mNodeRadius, mPaint);

            mPaint.setColor(Color.parseColor("#999999"));
            //绘制 item之间的下划线
            c.drawRect(mOffsetLeft + parent.getPaddingLeft(), dividerTop, view.getRight(), view.getTop(), mPaint);
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }
}
