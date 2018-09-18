package com.temdiecoration.itemdecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.temdiecoration.itemdecoration.group.GroupInfo;

/**
 * 姓名: 王进亚
 * 时间： 2018/9/10
 * 描述：
 */

public class SectionDecoration extends RecyclerView.ItemDecoration {

    private final float mTextOffsetX;
    private Context mContext;
    private GroupInfoCallback mGroupInfoCallback;
    private float mHeaderHeight;
    private float mDividerHeight;
    private final Paint mPaint;
    private final Paint.FontMetrics mFontMetrics;

    public SectionDecoration(Context context, GroupInfoCallback groupInfoCallback) {
        mContext = context;
        mGroupInfoCallback = groupInfoCallback;

        mHeaderHeight = context.getResources().getDimension(R.dimen.timeline_item_head_height);
        mDividerHeight = context.getResources().getDimension(R.dimen.timeline_item_line);
        mTextOffsetX = context.getResources().getDimension(R.dimen.timeline_item_head_height);


        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mFontMetrics = mPaint.getFontMetrics();
    }

    public interface GroupInfoCallback {
        GroupInfo getGroupInfo(int position);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int childAdapterPosition = parent.getChildAdapterPosition(view);

        if (mGroupInfoCallback != null) {
            GroupInfo groupInfo = mGroupInfoCallback.getGroupInfo(childAdapterPosition);

            if (groupInfo != null && groupInfo.isFirstViewInGroup()) {
                outRect.top = (int) mHeaderHeight;
            } else {
                outRect.top = (int) mDividerHeight;
            }
        }
    }

//    @Override
//    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
//        super.onDraw(c, parent, state);
//
//
//        int childCount = parent.getChildCount();
//
//        for (int i = 0; i < childCount; i++) {
//            View view = parent.getChildAt(i);
//            int index = parent.getChildAdapterPosition(view);
//
//            if (mGroupInfoCallback != null) {
//                GroupInfo groupInfo = mGroupInfoCallback.getGroupInfo(index);
//
//                int left = parent.getPaddingLeft();
//                int bottom = view.getTop();
//                int right = parent.getWidth() - parent.getPaddingRight();
//
//                if (groupInfo != null && groupInfo.isFirstViewInGroup()) {//head
//                    float top = view.getTop() - mHeaderHeight;
//                    mPaint.setColor(Color.RED);
//                    c.drawRect(left, top, right, bottom, mPaint);
//
//                    mPaint.setTextSize(dp2px(mContext, 16));
//                    mPaint.setColor(Color.WHITE);
//                    float v = top + (bottom - top) / 2 - (mFontMetrics.bottom + mFontMetrics.top) / 2 - mFontMetrics.top;
//                    c.drawText(groupInfo.getTitle(), left + mTextOffsetX, v, mPaint);
//                } else {//divide
//                    float top = view.getTop() - mDividerHeight;
//
//                    mPaint.setColor(Color.GRAY);
//                    c.drawRect(left, top, right, bottom, mPaint);
//                }
//            }
//        }
//
//    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int childAdapterPosition = parent.getChildAdapterPosition(view);

            if (mGroupInfoCallback != null) {
                GroupInfo groupInfo = mGroupInfoCallback.getGroupInfo(childAdapterPosition);

                int left = parent.getPaddingLeft();
                int right = parent.getWidth() - parent.getPaddingRight();

                if (i != 0) {//不是第一个可见的item
                    //只有组内的第一个ItemView之上才绘制
                    if (groupInfo.isFirstViewInGroup()) {
                        int top = (int) (view.getTop() - mHeaderHeight);
                        int bottom = view.getTop();
                        drawHeaderRect(c, groupInfo, left, top, right, bottom);
                    }

                } else {//第一个可见的item
                    //当 ItemView 是屏幕上第一个可见的View 时，不管它是不是组内第一个View
                    //它都需要绘制它对应的 StickyHeader
                    int top = parent.getPaddingTop();

                    if (groupInfo.isLastViewInGroup()) {
                        int suggestTop = (int) (view.getBottom() - mHeaderHeight);
                        if ( suggestTop < top ) {
                            top = suggestTop;
                        }
                    }
                    int bottom = (int) (top + mHeaderHeight);

                    drawHeaderRect(c, groupInfo, left, top, right, bottom);
                }
            }
        }
    }

    private int dp2px(Context context, int dp) {

        float density = context.getResources().getDisplayMetrics().density;

        return (int) (density * dp + 0.5f);

    }

    private void drawHeaderRect(Canvas c, GroupInfo groupinfo, int left, int top, int right, int bottom) {
        mPaint.setColor(Color.RED);
        //绘制Header
        c.drawRect(left, top, right, bottom, mPaint);

        mPaint.setTextSize(dp2px(mContext, 16));
        mPaint.setColor(Color.WHITE);

        float titleX = left + mTextOffsetX;
        float titleY = top + (bottom - top) / 2 - (mFontMetrics.bottom + mFontMetrics.top) / 2 - mFontMetrics.top;
        //绘制Title
        c.drawText(groupinfo.getTitle(), titleX, titleY, mPaint);
    }
}
