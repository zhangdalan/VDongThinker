package com.thinker.vdongthinker.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.adapter.BaseAdapterRecycler;
import com.thinker.vdongthinker.adapter.IndexRecyclerAdapter;
import com.thinker.vdongthinker.base.BasePresenterFragment;
import com.thinker.vdongthinker.bean.IndexMallRecyclerBean;
import com.thinker.vdongthinker.presenter.AgencyCourseFragmentPresenter;
import com.thinker.vdongthinker.presenter.AgencyDetailFragmentPresenter;
import com.thinker.vdongthinker.ui.activity.CourseDetailActivity;
import com.thinker.vdongthinker.view.AgencyCourseFragmentView;
import com.thinker.vdongthinker.view.AgencyDetailFragmentView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zt on 2018/12/13.
 */

public class FragmentAgencyCourse extends BasePresenterFragment<AgencyCourseFragmentPresenter> implements AgencyCourseFragmentView,BaseAdapterRecycler.OnRecyclerViewItemClickListener {

    private RecyclerView rv_course;
    private IndexRecyclerAdapter adapter_course;
    private List<IndexMallRecyclerBean> list_mall;
    @Override
    public void initData() {
        rv_course = contentView.findViewById(R.id.rv_course);
        setRecycler();
    }

    private void setRecycler() {
        //商品列表
        list_mall = new ArrayList<>();
        for (int i = 0;i<20;i++){
            list_mall.add(new IndexMallRecyclerBean("商品标题","1000","1111"));
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mPresenter.mContext,2);
        rv_course.setLayoutManager(gridLayoutManager);
        adapter_course = new IndexRecyclerAdapter(mPresenter.mContext,rv_course);
        adapter_course.setItems(list_mall);
        adapter_course.setOnRecyclerViewItemClickListener(this);
        rv_course.setAdapter(adapter_course);
        rv_course.setNestedScrollingEnabled(false);
    }

    @Override
    public void initPresenter() {
        mPresenter = new AgencyCourseFragmentPresenter();
        mPresenter.init(getActivity(),getContext(),this);
    }

    @Override
    public int getLauoutID() {
        return R.layout.fragment_agency_course;
    }

    @Override
    public void onItemClick(View view, int position) {
        //跳转商品详情
        Intent intent = new Intent(mPresenter.mActivity,CourseDetailActivity.class);
        intent.putExtra("","");
        startActivity(intent);
    }
}
