package com.thinker.vdongthinker.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.adapter.CourseAssessListViewAdapter;
import com.thinker.vdongthinker.adapter.CoursePhotoGridViewAdapter;
import com.thinker.vdongthinker.base.BasePresenterActivity;
import com.thinker.vdongthinker.bean.CourseAssessBean;
import com.thinker.vdongthinker.bean.IndexMallRecyclerBean;
import com.thinker.vdongthinker.customControl.MeasureGridView;
import com.thinker.vdongthinker.customControl.MeasureListView;
import com.thinker.vdongthinker.presenter.CourseDetailPresenter;
import com.thinker.vdongthinker.view.CourseDetailView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zt on 2018/12/12.
 */

public class CourseDetailActivity extends BasePresenterActivity<CourseDetailPresenter> implements CourseDetailView,View.OnClickListener {
    private ImageView iv_back,iv_funcation,iv_call,iv_img,iv_more;
    private TextView tv_title;
    private TextView tv_course_type,tv_course_name,tv_price,tv_num;
    private TextView tv_info,tv_age,tv_date,tv_hour,tv_time;
    private TextView tv_remark,tv_address,tv_agency,tv_tel;
    private MeasureGridView gv_photo;
    private TextView tv_assess_num;
    private MeasureListView lv_assess;
    private boolean IS_MORE = false;
    private Button btn_buy,btn_assess_more;
    private CoursePhotoGridViewAdapter adapter_gv ;
    private List<Integer> list_photo;
    private CourseAssessListViewAdapter adapter_assess;
    private List<CourseAssessBean> list_assess;
    @Override
    public void initData() {
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText("课程详情");
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        iv_funcation = findViewById(R.id.iv_funcation);
        iv_funcation.setOnClickListener(this);
        iv_call = findViewById(R.id.iv_call);
        iv_call.setOnClickListener(this);
        iv_img = findViewById(R.id.iv_img);
        iv_more = findViewById(R.id.iv_more);
        iv_more.setOnClickListener(this);
        tv_course_type = findViewById(R.id.tv_course_type);
        tv_course_name = findViewById(R.id.tv_course_name);
        tv_price = findViewById(R.id.tv_price);
        tv_num= findViewById(R.id.tv_num);
        tv_info = findViewById(R.id.tv_info);
        tv_age = findViewById(R.id.tv_age);
        tv_date = findViewById(R.id.tv_date);
        tv_hour = findViewById(R.id.tv_hour);
        tv_time = findViewById(R.id.tv_time);
        tv_remark = findViewById(R.id.tv_remark);
        tv_address = findViewById(R.id.tv_address);
        tv_agency = findViewById(R.id.tv_agency);
        tv_tel = findViewById(R.id.tv_tel);
        gv_photo = findViewById(R.id.gv_photo);
        tv_assess_num = findViewById(R.id.tv_assess_num);
        lv_assess = findViewById(R.id.lv_assess);
        btn_buy = findViewById(R.id.btn_buy);
        btn_buy.setOnClickListener(this);
        btn_assess_more = findViewById(R.id.btn_assess_more);
        btn_assess_more.setOnClickListener(this);

        setList();
    }

    private void setList() {
        list_photo = new ArrayList<>();
        for (int i = 0;i<4;i++){
            list_photo.add(R.mipmap.img_defult_mall);
        }
        adapter_gv = new CoursePhotoGridViewAdapter(this);
        adapter_gv.setItems(list_photo);
        gv_photo.setAdapter(adapter_gv);
        gv_photo.setOverScrollMode(View.OVER_SCROLL_NEVER);

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
        mPresenter = new CourseDetailPresenter();
        mPresenter.init(this,this,this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_course_detail;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_more:
                if(!IS_MORE){
                    iv_more.animate().rotation(180);
                    tv_info.setMaxLines(Integer.MAX_VALUE);
                }else{
                    iv_more.animate().rotation(360);
                    tv_info.setMaxLines(2);
                }
                IS_MORE = !IS_MORE;
                break;
            case R.id.iv_call:

                break;
            case R.id.btn_buy:

                break;
            case R.id.btn_assess_more:

                break;
        }
    }
}
