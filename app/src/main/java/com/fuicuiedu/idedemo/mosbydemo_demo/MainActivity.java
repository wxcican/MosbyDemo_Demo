package com.fuicuiedu.idedemo.mosbydemo_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView{
    @BindView(R.id.main_lv)
    ListView mainLv;
    @BindView(R.id.main_prb)
    ProgressBar mainPrb;

    private List<String> mData;
    private ArrayAdapter<String> adapter;

    /**
     * 视图与业务(分析)
     * <p>
     * 视图（可见即为视图）：
     * progressbar 加载动画
     * Listview 展示数据
     * butten 点击加载
     * <p>
     * 视图操作（也属于视图）：
     * progressbar 显示
     * progressbar 隐藏
     * setData 设置数据（改变listview）
     * <p>
     * 业务：
     * 获取到数据并且通知视图改变
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mData = new ArrayList<>();
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,mData);
        mainLv.setAdapter(adapter);
    }


    @OnClick(R.id.main_btn)
    public void onClick() {
        //点击加载数据
        //此处依然传mainactivity，但是此时mainactivity是做接口mainview的实现类而存在的。
        //当activity生命周期结束后，仍然会以接口的实现类而存在。
        new MainPresenter(this).loadDate();
    }

    @Override
    public void showPrb() {
        mainPrb.setVisibility(View.VISIBLE);
        mainLv.setVisibility(View.GONE);
    }

    @Override
    public void hidePrb() {
        mainPrb.setVisibility(View.GONE);
        mainLv.setVisibility(View.VISIBLE);
    }

    @Override
    public void setData(List<String> datas) {
        //设置数据
        adapter.addAll(datas);
        adapter.notifyDataSetChanged();
    }
}
