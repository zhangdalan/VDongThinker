package com.thinker.vdongthinker.ui.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.adapter.CourseAssessListViewAdapter;
import com.thinker.vdongthinker.adapter.CoursePhotoGridViewAdapter;
import com.thinker.vdongthinker.base.BaseFragmentPresenter;
import com.thinker.vdongthinker.base.BasePresenterFragment;
import com.thinker.vdongthinker.bean.CourseAssessBean;
import com.thinker.vdongthinker.customControl.MeasureGridView;
import com.thinker.vdongthinker.customControl.MeasureListView;
import com.thinker.vdongthinker.presenter.AgencyDetailFragmentPresenter;
import com.thinker.vdongthinker.view.AgencyDetailFragmentView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zt on 2018/12/13.
 */

public class FragmentAgencyDetail extends BasePresenterFragment<AgencyDetailFragmentPresenter> implements AgencyDetailFragmentView ,View.OnClickListener{
    private ImageView iv_bg,iv_img,iv_huiyuan,iv_more;
    private TextView tv_agency_name,tv_course_num,tv_people_num,tv_tel,tv_info;
    private TextView tv_agency,tv_assess_num;
    private Button btn_call,btn_assess_more;
    private MeasureGridView gv_photo;
    private MeasureListView lv_assess;
    private CoursePhotoGridViewAdapter adapter_gv ;
    private List<String> list_photo;
    private CourseAssessListViewAdapter adapter_assess;
    private List<CourseAssessBean> list_assess;
    private boolean IS_MORE = false;

    @Override
    public void initData() {
        iv_bg = contentView.findViewById(R.id.iv_bg);
        iv_img = contentView.findViewById(R.id.iv_img);
        iv_huiyuan = contentView.findViewById(R.id.iv_huiyuan);
        iv_more = contentView.findViewById(R.id.iv_more);
        iv_more.setOnClickListener(this);
        tv_agency_name = contentView.findViewById(R.id.tv_agency_name);
        tv_course_num = contentView.findViewById(R.id.tv_course_num);
        tv_people_num = contentView.findViewById(R.id.tv_people_num);
        tv_tel = contentView.findViewById(R.id.tv_tel);
        tv_info = contentView.findViewById(R.id.tv_info);
        tv_agency = contentView.findViewById(R.id.tv_agency);
        tv_assess_num = contentView.findViewById(R.id.tv_assess_num);
        btn_call = contentView.findViewById(R.id.btn_call);
        btn_assess_more = contentView.findViewById(R.id.btn_assess_more);
        btn_call.setOnClickListener(this);
        btn_assess_more.setOnClickListener(this);
        gv_photo = contentView.findViewById(R.id.gv_photo);
        lv_assess = contentView.findViewById(R.id.lv_assess);

        setList();
    }

    private void setList() {
        list_photo = new ArrayList<>();
        for (int i = 0;i<4;i++){
            list_photo.add("http://a.hiphotos.baidu.com/image/h%3D300/sign=194caab2b50e7bec3cda05e11f2fb9fa/960a304e251f95caf1852c0bc4177f3e6709521e.jpg");
        }
        adapter_gv = new CoursePhotoGridViewAdapter(mPresenter.mContext);
        adapter_gv.setItems(list_photo);
        gv_photo.setAdapter(adapter_gv);
        gv_photo.setOverScrollMode(View.OVER_SCROLL_NEVER);

        list_assess = new ArrayList<>();
        for (int i = 0;i<2;i++){
            list_assess.add(new CourseAssessBean("","小仙女啊","2019-01-01","声明：本文内容由互联网用户自发贡献自行上传，本网站不拥有所有权，未作人工编辑处理，也不承担相关法律责任。"));
        }
        adapter_assess = new CourseAssessListViewAdapter(mPresenter.mContext);
        adapter_assess.setItems(list_assess);
        lv_assess.setAdapter(adapter_assess);
        lv_assess.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    @Override
    public void initPresenter() {
        mPresenter = new AgencyDetailFragmentPresenter();
        mPresenter.init(getActivity() ,getContext() ,this);
    }

    @Override
    public int getLauoutID() {
        return R.layout.fragment_agency_detail;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_call:

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
            case R.id.btn_assess_more:

                break;
        }
    }
}
