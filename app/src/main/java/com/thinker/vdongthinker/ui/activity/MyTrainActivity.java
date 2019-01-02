package com.thinker.vdongthinker.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.adapter.MallCarAdapter;
import com.thinker.vdongthinker.adapter.MyAgencyAdapter;
import com.thinker.vdongthinker.adapter.MyCourseAdapter;
import com.thinker.vdongthinker.base.BasePresenterActivity;
import com.thinker.vdongthinker.bean.MyAgencyBean;
import com.thinker.vdongthinker.bean.MyCourseBean;
import com.thinker.vdongthinker.presenter.AgencyDetailPresenter;
import com.thinker.vdongthinker.presenter.MyTrainPresenter;
import com.thinker.vdongthinker.ui.fragment.FragmentAgencyCourse;
import com.thinker.vdongthinker.ui.fragment.FragmentAgencyDetail;
import com.thinker.vdongthinker.ui.fragment.MyPageAdapter;
import com.thinker.vdongthinker.view.AgencyDetailView;
import com.thinker.vdongthinker.view.MyCommunityView;
import com.thinker.vdongthinker.view.MyTrainView;

import java.util.ArrayList;

/**
 * Created by zjw on 2018/12/13.
 */

public class MyTrainActivity extends BasePresenterActivity<MyTrainPresenter> implements MyTrainView,View.OnClickListener,MyCourseAdapter.OnMallItemClickListener {
    private ImageView iv_back;
    private LinearLayout ll_course,ll_agency;
    private TextView tv_title,tv_course,tv_agency;
    private View line1,line2;
    private RecyclerView rv_course,rv_agency;
    private ArrayList<MyCourseBean> list_course;
    private ArrayList<MyAgencyBean> list_agency;
    private MyCourseAdapter adapter_course;
    private MyAgencyAdapter adapter_agency;

    @Override
    public void initData() {
        iv_back = findViewById(R.id.iv_back);
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText("我的培训");
        ll_course = findViewById(R.id.ll_course);
        ll_agency = findViewById(R.id.ll_agency);
        line1 = findViewById(R.id.line1);
        line2 = findViewById(R.id.line2);
        rv_course = findViewById(R.id.rv_course);
        rv_agency = findViewById(R.id.rv_agency);
        iv_back.setOnClickListener(this);
        iv_back.setVisibility(View.VISIBLE);
        ll_course.setOnClickListener(this);
        ll_agency.setOnClickListener(this);
        tv_course = findViewById(R.id.tv_course);
        tv_agency = findViewById(R.id.tv_agency);
        tv_course.setTextColor(getResources().getColor(R.color.text_orange));
        line1.setVisibility(View.VISIBLE);
        tv_agency.setTextColor(getResources().getColor(R.color.text_black));
        line2.setVisibility(View.INVISIBLE);
        rv_course.setVisibility(View.VISIBLE);
        rv_agency.setVisibility(View.GONE);
        setList();
    }

    private void setList() {
        list_course = new ArrayList<>();
        for (int i = 0;i<10;i++){
            list_course.add(new MyCourseBean());
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        rv_course.setLayoutManager(gridLayoutManager);
        adapter_course = new MyCourseAdapter(this,rv_course);
        adapter_course.setItems(list_course);
        rv_course.setAdapter(adapter_course);
        rv_course.setNestedScrollingEnabled(false);

        list_agency = new ArrayList<>();
        for (int i = 0;i<10;i++){
            list_agency.add(new MyAgencyBean());
        }
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(this,1);
        rv_agency.setLayoutManager(gridLayoutManager1);
        adapter_agency = new MyAgencyAdapter(this,rv_agency);
        adapter_agency.setItems(list_agency);
        rv_agency.setAdapter(adapter_agency);
        rv_agency.setNestedScrollingEnabled(false);
    }

    @Override
    public void initPresenter() {
        mPresenter = new MyTrainPresenter();
        mPresenter.init(this,this,this);

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_my_train;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_course:
                tv_course.setTextColor(getResources().getColor(R.color.text_orange));
                line1.setVisibility(View.VISIBLE);
                tv_agency.setTextColor(getResources().getColor(R.color.text_black));
                line2.setVisibility(View.INVISIBLE);
                rv_course.setVisibility(View.VISIBLE);
                rv_agency.setVisibility(View.GONE);
                break;
            case R.id.ll_agency:
                tv_course.setTextColor(getResources().getColor(R.color.text_black));
                line1.setVisibility(View.INVISIBLE);
                tv_agency.setTextColor(getResources().getColor(R.color.text_orange));
                line2.setVisibility(View.VISIBLE);
                rv_course.setVisibility(View.GONE);
                rv_agency.setVisibility(View.VISIBLE);

                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    public void onDeleteListener(int position) {

    }

    @Override
    public void onAssessListener(int position) {

    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
