package com.temdiecoration.itemdecoration;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 姓名: 王进亚
 * 时间： 2018/8/29
 * 描述：
 */

public class LineAdapter extends RecyclerView.Adapter<LineAdapter.LineViewHolder> {


    @Override
    public LineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new LineViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(LineViewHolder holder, int position) {
        TextView itemView = (TextView) holder.itemView.findViewById(R.id.tv_item);
        itemView.setText("item" + position);
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    static class LineViewHolder extends RecyclerView.ViewHolder {

        public LineViewHolder(View itemView) {
            super(itemView);
        }
    }
}
