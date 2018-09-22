#RecyclerView 下划线
### 垂直线性布局

```
mDecor = new LineItemDecoration(this, 1, Color.parseColor("#00FFFF"));

mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
mRecyclerView.addItemDecoration(mDecor);
```
### 网格布局功能
```
mDecor = new GridLineItemDecoration(this,Color.GRAY, 3, 2);
mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
mRecyclerView.addItemDecoration(mDecor);
mRecyclerView.setAdapter(new LineAdapter());
```
<div><img src='https://github.com/YaYaG/ItemDecoration/tree/yayaG/imgs/grid.jpg' width="300px" style='border: #f1f1f1 solid 1px'/></div>

