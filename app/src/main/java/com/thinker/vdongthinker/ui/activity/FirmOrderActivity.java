package com.thinker.vdongthinker.ui.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.adapter.FirmOrderMallAdapter;
import com.thinker.vdongthinker.adapter.MallCarAdapter;
import com.thinker.vdongthinker.base.BasePresenterActivity;
import com.thinker.vdongthinker.presenter.FirmOrderPresenter;
import com.thinker.vdongthinker.view.FirmOrderView;

import java.util.ArrayList;
import java.util.List;

public class FirmOrderActivity extends BasePresenterActivity<FirmOrderPresenter> implements FirmOrderView,View.OnClickListener {
    private ImageView iv_back,iv_mall;
    private TextView tv_title;
    private RecyclerView rv_mall;
    private RelativeLayout rl_address,rl_shipping,rl_course_time;
    private TextView tv_add_address;
    private List<String> list_mall;
    private FirmOrderMallAdapter adapter_agency;
    private LinearLayout ll_course_mall;
    private TextView tv_agency_name,tv_mall_name,tv_mall_sign,tv_mall_price,tv_course_time;
    private int PAGE_TYPE = 0;//0购买课程，1购买商品
    @Override
    public void initPresenter() {
        mPresenter = new FirmOrderPresenter();
        mPresenter.init(this,this,this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_firm_order;
    }

    @Override
    public void initData() {
        PAGE_TYPE = getIntent().getIntExtra("PAGE_TYPE",0);

        iv_back = findViewById(R.id.iv_back);
        iv_back.setVisibility(View.VISIBLE);
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText("确认订单");
        rv_mall = findViewById(R.id.rv_mall);
        rl_address = findViewById(R.id.rl_address);
        tv_add_address = findViewById(R.id.tv_add_address);
        iv_back.setOnClickListener(this);
        tv_add_address.setOnClickListener(this);
        iv_back = findViewById(R.id.iv_back);
        iv_mall = findViewById(R.id.iv_mall);
        rl_shipping = findViewById(R.id.rl_shipping);
        rl_course_time = findViewById(R.id.rl_course_time);
        ll_course_mall = findViewById(R.id.ll_course_mall);
        tv_agency_name = findViewById(R.id.tv_agency_name);
        tv_mall_name = findViewById(R.id.tv_mall_name);
        tv_agency_name = findViewById(R.id.tv_agency_name);
        tv_mall_sign = findViewById(R.id.tv_mall_sign);
        tv_mall_price = findViewById(R.id.tv_mall_price);
        tv_course_time = findViewById(R.id.tv_course_time);

        if(PAGE_TYPE == 1){//当前页面为购买商品，隐藏掉课程布局，和课程时间布局
            ll_course_mall.setVisibility(View.GONE);
            rl_course_time.setVisibility(View.INVISIBLE);
            rv_mall.setVisibility(View.VISIBLE);
            rl_shipping.setVisibility(View.VISIBLE);
            tv_add_address.setText(getResources().getText(R.string.hint_add_address));
        }else{
            ll_course_mall.setVisibility(View.VISIBLE);
            rl_course_time.setVisibility(View.VISIBLE);
            rv_mall.setVisibility(View.GONE);
            rl_shipping.setVisibility(View.INVISIBLE);
            tv_add_address.setText("请添加学生信息");
        }
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
        adapter_agency = new FirmOrderMallAdapter(this,rv_mall);
        adapter_agency.setItems(list_mall);
        rv_mall.setAdapter(adapter_agency);
        rv_mall.setNestedScrollingEnabled(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_add_address:
                Intent intent = new Intent(FirmOrderActivity.this,SelectAddressActivity.class);
                intent.putExtra("PAGE_TYPE",PAGE_TYPE);
                startActivity(intent);
                break;
        }
    }
}
