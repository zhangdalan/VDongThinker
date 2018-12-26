package com.thinker.vdongthinker.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.base.BasePresenterActivity;
import com.thinker.vdongthinker.presenter.MainPresenter;
import com.thinker.vdongthinker.ui.fragment.FragmentAgency;
import com.thinker.vdongthinker.ui.fragment.FragmentCommunity;
import com.thinker.vdongthinker.ui.fragment.FragmentController;
import com.thinker.vdongthinker.ui.fragment.FragmentCourse;
import com.thinker.vdongthinker.ui.fragment.FragmentIndex;
import com.thinker.vdongthinker.ui.fragment.FragmentMall;
import com.thinker.vdongthinker.ui.fragment.MyPageAdapter;
import com.thinker.vdongthinker.view.MainView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BasePresenterActivity<MainPresenter> implements MainView ,View.OnClickListener{
    private RelativeLayout rl_index,rl_course,rl_organization,rl_community,rl_mall;
    private ImageView iv_index,iv_course,iv_organization,iv_community,iv_mall;
    private TextView tv_index,tv_course,tv_organization,tv_community,tv_mall;
    private ViewPager fl_content;
    private MyPageAdapter fragment_adapter;
    @Override
    public void initPresenter() {
        mPresenter = new MainPresenter();
        mPresenter.init(this,this,this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        rl_index = findViewById(R.id.rl_index);
        rl_index.setOnClickListener(this);
        rl_course = findViewById(R.id.rl_course);
        rl_course.setOnClickListener(this);
        rl_organization = findViewById(R.id.rl_organization);
        rl_organization.setOnClickListener(this);
        rl_community = findViewById(R.id.rl_community);
        rl_community.setOnClickListener(this);
        rl_mall = findViewById(R.id.rl_mall);
        rl_mall.setOnClickListener(this);
        iv_index = findViewById(R.id.iv_index);
        iv_course = findViewById(R.id.iv_course);
        iv_organization = findViewById(R.id.iv_organization);
        iv_community = findViewById(R.id.iv_community);
        iv_mall = findViewById(R.id.iv_mall);
        tv_index = findViewById(R.id.tv_index);
        tv_course = findViewById(R.id.tv_course);
        tv_organization = findViewById(R.id.tv_organization);
        tv_community = findViewById(R.id.tv_community);
        tv_mall = findViewById(R.id.tv_mall);
        fl_content = findViewById(R.id.fl_content);
        ArrayList<Fragment> list_fragment = new ArrayList<Fragment>();
        list_fragment.add(new FragmentIndex()) ;
        list_fragment.add(new FragmentCourse()) ;
        list_fragment.add(new FragmentAgency()) ;
        list_fragment.add(new FragmentCommunity()) ;
//        list_fragment.add(new FragmentMall()) ;

        FragmentManager fm = getSupportFragmentManager();
        fragment_adapter = new MyPageAdapter(fm,list_fragment);
        fl_content.setAdapter(fragment_adapter);
        fl_content.setCurrentItem(0);
        fl_content.setOffscreenPageLimit(4);
        fl_content.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        onClick(rl_index);
                        break;
                    case 1:
                        onClick(rl_course);
                        break;
                    case 2:
                        onClick(rl_organization);
                        break;
                    case 3:
                        onClick(rl_community);
                        break;
//                    case 4:
//                        onClick(rl_mall);
//                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //首页刚进来展示的fragment界面
        selectClear();
        iv_index.setImageResource(R.mipmap.icon_index_on);
        tv_index.setTextColor(getResources().getColor(R.color.background_orange));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_index:
                selectClear();
                iv_index.setImageResource(R.mipmap.icon_index_on);
                tv_index.setTextColor(getResources().getColor(R.color.background_orange));
                fl_content.setCurrentItem(0);
                break;
            case R.id.rl_course:
                selectClear();
                iv_course.setImageResource(R.mipmap.icon_course_on);
                tv_course.setTextColor(getResources().getColor(R.color.background_orange));
                fl_content.setCurrentItem(1);
                break;
            case R.id.rl_organization:
                selectClear();
                iv_organization.setImageResource(R.mipmap.icon_agency_on);
                tv_organization.setTextColor(getResources().getColor(R.color.background_orange));
                fl_content.setCurrentItem(2);
                break;
            case R.id.rl_community:
                selectClear();
                iv_community.setImageResource(R.mipmap.icon_community_on);
                tv_community.setTextColor(getResources().getColor(R.color.background_orange));
                fl_content.setCurrentItem(3);
                break;
//            case R.id.rl_mall:
//                selectClear();
//                iv_mall.setImageResource(R.mipmap.icon_mall_on);
//                tv_mall.setTextColor(getResources().getColor(R.color.background_orange));
//                fl_content.setCurrentItem(4);
//                break;
        }
    }

    private void selectClear(){
        iv_index.setImageResource(R.mipmap.icon_index_no);
        tv_index.setTextColor(getResources().getColor(R.color.text_black));
        iv_course.setImageResource(R.mipmap.icon_course_no);
        tv_course.setTextColor(getResources().getColor(R.color.text_black));
        iv_organization.setImageResource(R.mipmap.icon_agency_no);
        tv_organization.setTextColor(getResources().getColor(R.color.text_black));
        iv_community.setImageResource(R.mipmap.icon_community_no);
        tv_community.setTextColor(getResources().getColor(R.color.text_black));
//        iv_mall.setImageResource(R.mipmap.icon_mall_no);
//        tv_mall.setTextColor(getResources().getColor(R.color.text_black));
    }
}
