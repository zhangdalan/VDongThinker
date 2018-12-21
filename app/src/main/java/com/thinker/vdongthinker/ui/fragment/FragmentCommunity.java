package com.thinker.vdongthinker.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.adapter.AgencyRecyclerAdapter;
import com.thinker.vdongthinker.adapter.CommunityRecycleAdapter;
import com.thinker.vdongthinker.base.BasePresenterFragment;
import com.thinker.vdongthinker.bean.AgencyMallRecyclerBean;
import com.thinker.vdongthinker.bean.CommunityBean;
import com.thinker.vdongthinker.presenter.CommunityFragmentPresenter;
import com.thinker.vdongthinker.ui.activity.CommunityDetailActivity;
import com.thinker.vdongthinker.ui.activity.NewReleaseActivity;
import com.thinker.vdongthinker.view.CommunityFragmentView;

import java.util.ArrayList;

/**
 * Created by zjw on 2018/12/17.
 */

public class FragmentCommunity extends BasePresenterFragment<CommunityFragmentPresenter> implements CommunityFragmentView ,CommunityRecycleAdapter.OnCommunityClickListener,View.OnClickListener{
    private ImageView iv_touxiang,iv_search,iv_add_release;
    private RecyclerView rv_community;
    private ArrayList<CommunityBean> list_mall;
    private CommunityRecycleAdapter adapter_comm;

    @Override
    public void initData() {
        iv_touxiang = contentView.findViewById(R.id.iv_touxiang);
        iv_search = contentView.findViewById(R.id.iv_search);
        rv_community = contentView.findViewById(R.id.rv_community);
        iv_add_release = contentView.findViewById(R.id.iv_add_release);
        iv_add_release.setOnClickListener(this);
        initList();
    }

    private void initList() {
        list_mall = new ArrayList<>();
        for (int i = 0;i<5;i++){
            if(i == 3 || i==4){
                list_mall.add(new CommunityBean(0));
            }
            list_mall.add(new CommunityBean(1));
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mPresenter.mContext,1);
        rv_community.setLayoutManager(gridLayoutManager);
        adapter_comm = new CommunityRecycleAdapter(mPresenter.mContext,rv_community);
        adapter_comm.setItems(list_mall);
        adapter_comm.setOnRecyclerViewItemClickListener(this);
        rv_community.setAdapter(adapter_comm);
        rv_community.setNestedScrollingEnabled(false);
    }

    @Override
    public void initPresenter() {
        mPresenter = new CommunityFragmentPresenter();
        mPresenter.init(getActivity(),getContext(),this);
    }

    @Override
    public int getLauoutID() {
        return R.layout.fragment_community;
    }

    @Override
    public void onVideoClick(int position) {

    }

    @Override
    public void onGridViewClick(int position) {

    }

    @Override
    public void onAssessClick(int position) {

    }

    @Override
    public void onZanClick(int position) {

    }

    @Override
    public void onItemClick(int position) {
        startActivity(new Intent(mPresenter.mActivity, CommunityDetailActivity.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_add_release:
                startActivity(new Intent(mPresenter.mActivity, NewReleaseActivity.class));
                break;
        }
    }
}
