package com.thinker.vdongthinker.ui.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.adapter.MallCarAdapter;
import com.thinker.vdongthinker.adapter.SelectAddressAdapter;
import com.thinker.vdongthinker.base.BasePresenterActivity;
import com.thinker.vdongthinker.presenter.SelectAddressPresenter;
import com.thinker.vdongthinker.view.AddAddressView;
import com.thinker.vdongthinker.view.SelectAddressView;

import java.util.ArrayList;
import java.util.List;

public class SelectAddressActivity extends BasePresenterActivity<SelectAddressPresenter> implements SelectAddressView ,View.OnClickListener {
    private ImageView iv_back;
    private TextView tv_title,tv_function;
    private RecyclerView rv_address;
    private List<String> list_address;
    private SelectAddressAdapter adapter;
    private int PAGE_TYPE = 0;//0课程购买，1商品购买

    @Override
    public void initPresenter() {
        mPresenter = new SelectAddressPresenter();
        mPresenter.init(this,this,this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_select_address;
    }

    @Override
    public void initData() {

        PAGE_TYPE = getIntent().getIntExtra("PAGE_TYPE",0);
        iv_back = findViewById(R.id.iv_back);
        iv_back.setVisibility(View.VISIBLE);
        tv_title = findViewById(R.id.tv_title);
        if (PAGE_TYPE == 0){
            tv_title.setText("选择学生");
        }else{
            tv_title.setText("选择收货地址");
        }
        tv_function = findViewById(R.id.tv_function);
        tv_function.setVisibility(View.VISIBLE);
        tv_function.setText("添加");
        iv_back.setOnClickListener(this);
        tv_function.setOnClickListener(this);
        rv_address = findViewById(R.id.rv_address);
        setList();
    }

    private void setList() {
        list_address = new ArrayList<>();
        for (int i = 0;i<10;i++){
            list_address.add("");
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        rv_address.setLayoutManager(gridLayoutManager);
        if (PAGE_TYPE == 0){
            adapter = new SelectAddressAdapter(this,rv_address,true);
        }else{
            adapter = new SelectAddressAdapter(this,rv_address,false);
        }
        adapter.setItems(list_address);
        rv_address.setAdapter(adapter);
        rv_address.setNestedScrollingEnabled(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_function:
                Intent intent = new Intent(this,AddAddressActivity.class);
                intent.putExtra("PAGE_TYPE",PAGE_TYPE);
                startActivity(intent);
                break;

        }
    }
}
