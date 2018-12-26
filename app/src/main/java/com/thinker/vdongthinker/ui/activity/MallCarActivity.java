package com.thinker.vdongthinker.ui.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.adapter.AgencyRecyclerAdapter;
import com.thinker.vdongthinker.adapter.MallCarAdapter;
import com.thinker.vdongthinker.base.BasePresenterActivity;
import com.thinker.vdongthinker.bean.AgencyMallRecyclerBean;
import com.thinker.vdongthinker.presenter.MallCarPresenter;
import com.thinker.vdongthinker.view.MallCarView;

import java.util.ArrayList;
import java.util.List;

public class MallCarActivity extends BasePresenterActivity<MallCarPresenter> implements MallCarView ,View.OnClickListener,MallCarAdapter.OnMallItemClickListener {
    private ImageView iv_back;
    private TextView tv_title,tv_function;
    private RecyclerView rv_mall;
    private CheckBox cb_select_all;
    private TextView tv_price_all;
    private Button btn_buy,btn_delete;
    private LinearLayout ll_manage;
    private boolean IS_DELETE = false;
    private List<String> list_mall;
    private MallCarAdapter adapter_agency;

    @Override
    public void initPresenter() {
        mPresenter = new MallCarPresenter();
        mPresenter.init(this,this,this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_mall_car;
    }

    @Override
    public void initData() {
        iv_back = findViewById(R.id.iv_back);
        iv_back.setVisibility(View.VISIBLE);
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText("购物车");
        tv_function = findViewById(R.id.tv_function);
        tv_function.setVisibility(View.VISIBLE);
        tv_function.setText("管理");
        rv_mall = findViewById(R.id.rv_mall);
        cb_select_all = findViewById(R.id.cb_select_all);
        tv_price_all = findViewById(R.id.tv_price_all);
        btn_buy = findViewById(R.id.btn_buy);
        btn_delete = findViewById(R.id.btn_delete);
        ll_manage = findViewById(R.id.ll_manage);
        iv_back.setOnClickListener(this);
        tv_function.setOnClickListener(this);
        btn_buy.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        cb_select_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        setList();
    }

    private void setList() {
        //商品列表
        list_mall = new ArrayList<>();
        for (int i = 0;i<10;i++){
            list_mall.add("");
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        rv_mall.setLayoutManager(gridLayoutManager);
        adapter_agency = new MallCarAdapter(this,rv_mall);
        adapter_agency.setItems(list_mall);
        rv_mall.setAdapter(adapter_agency);
        rv_mall.setNestedScrollingEnabled(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_function:
                if(IS_DELETE){//如果当前状态为删除
                    tv_function.setText("管理");
                    ll_manage.setVisibility(View.VISIBLE);
                    btn_delete.setVisibility(View.GONE);
                }else{
                    tv_function.setText("完成");
                    ll_manage.setVisibility(View.GONE);
                    btn_delete.setVisibility(View.VISIBLE);
                }
                IS_DELETE = !IS_DELETE;
                break;
            case R.id.btn_buy:
                startActivity(new Intent(MallCarActivity.this,FirmOrderActivity.class));
                break;
        }
    }

    @Override
    public void onNumChangeClick(int num) {
        //计算全部价格
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
