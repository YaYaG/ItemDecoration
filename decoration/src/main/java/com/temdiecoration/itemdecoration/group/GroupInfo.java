package com.temdiecoration.itemdecoration.group;

/**
 * 姓名: 王进亚
 * 时间： 2018/9/10
 * 描述：
 */

public class GroupInfo {
    //组号
    private int mGroupID;
    // 组内 Header 的 title
    private String mTitle;

    //ItemView 在组内的位置
    private int position;

    // 组的成员个数
    private int mGroupLength;

    public GroupInfo(int groupID, String title) {
        mGroupID = groupID;
        mTitle = title;
    }

    //是否 是第一个出现的位置
    private  boolean isFirstViewInGroup;

    public int getGroupID() {
        return mGroupID;
    }

    public void setGroupID(int groupID) {
        mGroupID = groupID;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isFirstViewInGroup() {
        return position == 0;
    }

    public void setFirstViewInGroup(boolean firstViewInGroup) {
        isFirstViewInGroup = firstViewInGroup;
    }

    public boolean isLastViewInGroup () {
        return position == mGroupLength - 1 && position >= 0;
    }

    public void setGroupLength(int groupLength) {
        this.mGroupLength = groupLength;
    }
}
