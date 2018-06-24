package bwei.com.zhou3_demo;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.util.List;

import bwei.com.zhou3_demo.base.BaseActivity;
import bwei.com.zhou3_demo.base.BasePresenter;

public class MainActivity extends BaseActivity<BasePresenter> implements LoginView {

    private static final String TAG ="Activity----" ;
    private RecyclerView recyclerView;
    @Override
    protected BasePresenter providePresenter() {
        final bwei.com.zhou3_demo.presenter presenter = new presenter(this);

        presenter.login1();
        return presenter;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        recyclerView = findViewById(R.id.main_recycler);
    }

    @Override
    protected int LayoutId() {
        return R.layout.activity_main;
    }
    @Override
    protected void initData() {

    }
    @Override
    public void getsuccceed(String json) {

         Gson gson = new Gson();
         MainBean mainBean = gson.fromJson(json, MainBean.class);
        final List<MainBean.DataBeanX.DataBean> list = mainBean.getData().getData();
        Log.d(TAG,"数据"+list.get(1).getTitle());
        final MyAdapter myAdapter = new MyAdapter(list);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        //点击条目
        myAdapter.setOnItemClickListtener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("删除");
                dialog.setMessage("确定删除吗？");
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        list.remove(position);
                        myAdapter.notifyDataSetChanged();
                    }
                });
                dialog.setNegativeButton("取消",null);
                dialog.show();
            }
        });
        recyclerView.setAdapter(myAdapter);

    }

    @Override
    public void geteeror(Exception error) {

    }
}
