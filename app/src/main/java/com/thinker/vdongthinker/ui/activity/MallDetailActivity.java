package com.thinker.vdongthinker.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.adapter.CourseAssessListViewAdapter;
import com.thinker.vdongthinker.adapter.CoursePhotoGridViewAdapter;
import com.thinker.vdongthinker.base.BasePresenterActivity;
import com.thinker.vdongthinker.bean.CourseAssessBean;
import com.thinker.vdongthinker.customControl.MeasureListView;
import com.thinker.vdongthinker.presenter.MallDetailPresenter;
import com.thinker.vdongthinker.tool.GlideImageLoader;
import com.thinker.vdongthinker.view.MallDetailView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zt on 2018/12/14.
 */

public class MallDetailActivity extends BasePresenterActivity<MallDetailPresenter> implements MallDetailView ,OnBannerListener,View.OnClickListener{
    private ImageView iv_back,iv_car;
    private TextView tv_mall,tv_detail,tv_assess;
    private View line_mall,line_detail,line_assess;
    private Banner banner_mall;
    private TextView tv_price,tv_mall_name,tv_buy_num,tv_address,tv_kuaidi;
    private ImageView iv_detail;
    private TextView tv_assess_num;
    private Button btn_assess_more,btn_add_car,btn_buy;
    private MeasureListView lv_assess;
    private ArrayList<String> imgs;
    private ScrollView sv;
    private LinearLayout ll_detail,ll_assess;

    private CourseAssessListViewAdapter adapter_assess;
    private List<CourseAssessBean> list_assess;
    @Override
    public void initData() {
        sv = findViewById(R.id.sv);
        ll_detail = findViewById(R.id.ll_detail);
        ll_assess = findViewById(R.id.ll_assess);
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        iv_car = findViewById(R.id.iv_car);
        iv_car.setOnClickListener(this);
        tv_mall = findViewById(R.id.tv_mall);
        tv_mall.setOnClickListener(this);
        tv_detail = findViewById(R.id.tv_detail);
        tv_detail.setOnClickListener(this);
        tv_assess = findViewById(R.id.tv_assess);
        tv_assess.setOnClickListener(this);
        line_mall = findViewById(R.id.line_mall);
        line_detail = findViewById(R.id.line_detail);
        line_assess = findViewById(R.id.line_assess);
        banner_mall = findViewById(R.id.banner_mall);
        tv_price = findViewById(R.id.tv_price);
        tv_mall_name = findViewById(R.id.tv_mall_name);
        tv_buy_num = findViewById(R.id.tv_buy_num);
        tv_address = findViewById(R.id.tv_address);
        tv_kuaidi = findViewById(R.id.tv_kuaidi);
        iv_detail = findViewById(R.id.iv_detail);
        tv_assess_num = findViewById(R.id.tv_assess_num);
        btn_assess_more = findViewById(R.id.btn_assess_more);
        btn_add_car = findViewById(R.id.btn_add_car);
        btn_buy = findViewById(R.id.btn_buy);
        lv_assess = findViewById(R.id.lv_assess);
        setBanner();
        setList();
        setClear();
        tv_mall.setTextColor(getResources().getColor(R.color.white));
        line_mall.setVisibility(View.VISIBLE);
    }

    private void setBanner() {
        imgs = new ArrayList<>();
        imgs.add("");
        imgs.add("");
        imgs.add("");
        /*banner*/
        banner_mall.setIndicatorGravity(BannerConfig.CENTER);
        banner_mall.setOnBannerListener(this);
        banner_mall.setImages(imgs).setImageLoader(new GlideImageLoader(true)).start() ;
    }
    private void setList() {
        list_assess = new ArrayList<>();
        for (int i = 0;i<2;i++){
            list_assess.add(new CourseAssessBean("","小仙女啊","2019-01-01","声明：本文内容由互联网用户自发贡献自行上传，本网站不拥有所有权，未作人工编辑处理，也不承担相关法律责任。"));
        }
        adapter_assess = new CourseAssessListViewAdapter(this);
        adapter_assess.setItems(list_assess);
        lv_assess.setAdapter(adapter_assess);
        lv_assess.setOverScrollMode(View.OVER_SCROLL_NEVER);

    }

    @Override
    public void initPresenter() {
        mPresenter = new MallDetailPresenter();
        mPresenter.init(this,this,this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_mall_detail;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_mall:
                setClear();
                tv_mall.setTextColor(getResources().getColor(R.color.white));
                line_mall.setVisibility(View.VISIBLE);
                sv.scrollTo(0,banner_mall.getTop());
                break;
            case R.id.tv_detail:
                setClear();
                tv_detail.setTextColor(getResources().getColor(R.color.white));
                line_detail.setVisibility(View.VISIBLE);
                sv.scrollTo(0,ll_detail.getTop());
                break;
            case R.id.tv_assess:
                setClear();
                tv_assess.setTextColor(getResources().getColor(R.color.white));
                line_assess.setVisibility(View.VISIBLE);
                sv.scrollTo(0,ll_assess.getTop());
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
    private void setClear(){
        tv_mall.setTextColor(getResources().getColor(R.color.text_black));
        tv_detail.setTextColor(getResources().getColor(R.color.text_black));
        tv_assess.setTextColor(getResources().getColor(R.color.text_black));
        line_mall.setVisibility(View.INVISIBLE);
        line_detail.setVisibility(View.INVISIBLE);
        line_assess.setVisibility(View.INVISIBLE);
    }

    @Override
    public void OnBannerClick(int position) {

    }
}
