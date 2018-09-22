package com.temdiecoration.itemdecoration;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.temdiecoration.itemdecoration.decoration.GridLineItemDecoration;
import com.temdiecoration.itemdecoration.decoration.LineItemDecoration;
import com.temdiecoration.itemdecoration.group.GroupInfo;

public class MainActivity extends AppCompatActivity implements SectionDecoration.GroupInfoCallback {

    private RecyclerView mRecyclerView;
    private RecyclerView.ItemDecoration mDecor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDecor = new LineItemDecoration(this, 1, Color.RED);
        mRecyclerView.addItemDecoration(mDecor);

        mRecyclerView.setAdapter(new LineAdapter());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mRecyclerView.removeItemDecoration(mDecor);

        switch (item.getItemId()) {
            case R.id.menu_linear:
                mDecor = new LineItemDecoration(this, 1, Color.parseColor("#00FFFF"));
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                mRecyclerView.addItemDecoration(mDecor);
                mRecyclerView.setAdapter(new LineAdapter());
                break;
            case R.id.menu_grid:
                mDecor = new GridLineItemDecoration(this,Color.GRAY, 3, 2);
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
                mRecyclerView.addItemDecoration(mDecor);
                mRecyclerView.setAdapter(new LineAdapter());
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public GroupInfo getGroupInfo(int position) {
        /**
         * 分组逻辑，这里为了测试每5个数据为一组。大家可以在实际开发中
         * 替换为真正的需求逻辑
         */
        int groupId = position / 5;
        int index = position % 5;
        GroupInfo groupInfo = new GroupInfo(groupId, groupId + "");
        groupInfo.setPosition(index);
        groupInfo.setGroupLength(5);
        return groupInfo;
    }
}
