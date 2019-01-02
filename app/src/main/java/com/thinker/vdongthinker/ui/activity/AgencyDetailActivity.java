package com.thinker.vdongthinker.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.base.BasePresenterActivity;
import com.thinker.vdongthinker.presenter.AgencyDetailPresenter;
import com.thinker.vdongthinker.ui.fragment.FragmentAgency;
import com.thinker.vdongthinker.ui.fragment.FragmentAgencyCourse;
import com.thinker.vdongthinker.ui.fragment.FragmentAgencyDetail;
import com.thinker.vdongthinker.ui.fragment.FragmentCourse;
import com.thinker.vdongthinker.ui.fragment.FragmentIndex;
import com.thinker.vdongthinker.ui.fragment.MyPageAdapter;
import com.thinker.vdongthinker.view.AgencyDetailView;

import java.util.ArrayList;

/**
 * Created by zjw on 2018/12/13.
 */

public class AgencyDetailActivity extends BasePresenterActivity<AgencyDetailPresenter> implements AgencyDetailView,View.OnClickListener {
    private ImageView iv_back,iv_funcation;
    private TextView tv_detail,tv_course;
    private ViewPager vp_content;
    private MyPageAdapter fragment_adapter;

    @Override
    public void initData() {
        iv_back = findViewById(R.id.iv_back);
        iv_funcation = findViewById(R.id.iv_function);
        tv_detail = findViewById(R.id.tv_detail);
        tv_course = findViewById(R.id.tv_course);
        vp_content = findViewById(R.id.vp_content);

        vp_content = findViewById(R.id.vp_content);
        ArrayList<Fragment> list_fragment = new ArrayList<Fragment>();
        list_fragment.add(new FragmentAgencyDetail()) ;
        list_fragment.add(new FragmentAgencyCourse()) ;

        FragmentManager fm = getSupportFragmentManager();
        fragment_adapter = new MyPageAdapter(fm,list_fragment);
        vp_content.setAdapter(fragment_adapter);
        vp_content.setCurrentItem(0);
        vp_content.setOffscreenPageLimit(2);
        vp_content.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 0){
                    onClick(tv_detail);
                }else{
                    onClick(tv_course);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //首页刚进来展示的fragment界面
        tv_detail.setTextColor(getResources().getColor(R.color.white));
        tv_course.setTextColor(getResources().getColor(R.color.text_black));

    }

    @Override
    public void initPresenter() {
        mPresenter = new AgencyDetailPresenter();
        mPresenter.init(this,this,this);

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_agency_detail;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_detail:
                tv_detail.setTextColor(getResources().getColor(R.color.white));
                tv_course.setTextColor(getResources().getColor(R.color.text_black));
                vp_content.setCurrentItem(0);
                break;
            case R.id.tv_course:
                tv_detail.setTextColor(getResources().getColor(R.color.text_black));
                tv_course.setTextColor(getResources().getColor(R.color.white));
                vp_content.setCurrentItem(1);

                break;
        }
    }
}
