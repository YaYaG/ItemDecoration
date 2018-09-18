package com.temdiecoration.itemdecoration;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.temdiecoration.itemdecoration.group.GroupInfo;

public class MainActivity extends AppCompatActivity implements SectionDecoration.GroupInfoCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.addItemDecoration(new LineItemDecoration(1, Color.RED));
//        recyclerView.addItemDecoration(new TimelineItemDecoration(this));
//        recyclerView.setAdapter(new LineAdapter());

        recyclerView.addItemDecoration(new SectionDecoration(this, this));
        recyclerView.setAdapter(new GroupAdapter());
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
