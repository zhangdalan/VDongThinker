package com.thinker.vdongthinker.ui.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.adapter.MallCarAdapter;
import com.thinker.vdongthinker.base.BasePresenterActivity;
import com.thinker.vdongthinker.presenter.MallCarPresenter;
import com.thinker.vdongthinker.view.MallCarView;

public class MallCarActivity extends BasePresenterActivity<MallCarPresenter> implements MallCarView ,View.OnClickListener,MallCarAdapter.OnMallItemClickListener {
    private ImageView iv_back;
    private TextView tv_title,tv_function;
    private RecyclerView rv_mall;
    private CheckBox cb_select_all;
    private TextView tv_price_all;
    private Button btn_buy;
    private boolean IS_DELETE = false;
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
        iv_back.setOnClickListener(this);
        tv_function.setOnClickListener(this);
        btn_buy.setOnClickListener(this);
        cb_select_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_function:
                if(IS_DELETE){//如果当前状态为删除
                    tv_function.setText("管理");
                }else{
                    tv_function.setText("删除");

                }
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
