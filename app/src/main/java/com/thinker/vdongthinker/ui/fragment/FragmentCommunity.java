package com.thinker.vdongthinker.ui.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.adapter.AgencyRecyclerAdapter;
import com.thinker.vdongthinker.adapter.CommunityRecycleAdapter;
import com.thinker.vdongthinker.base.BasePresenterFragment;
import com.thinker.vdongthinker.bean.AgencyMallRecyclerBean;
import com.thinker.vdongthinker.bean.CommunityBean;
import com.thinker.vdongthinker.presenter.CommunityFragmentPresenter;
import com.thinker.vdongthinker.view.CommunityFragmentView;

import java.util.ArrayList;

/**
 * Created by zjw on 2018/12/17.
 */

public class FragmentCommunity extends BasePresenterFragment<CommunityFragmentPresenter> implements CommunityFragmentView ,CommunityRecycleAdapter.OnCommunityClickListener{
    private ImageView iv_touxiang,iv_search;
    private RecyclerView rv_community;
    private ArrayList<CommunityBean> list_mall;
    private CommunityRecycleAdapter adapter_comm;

    @Override
    public void initData() {
        iv_touxiang = contentView.findViewById(R.id.iv_touxiang);
        iv_search = contentView.findViewById(R.id.iv_search);
        rv_community = contentView.findViewById(R.id.rv_community);

        initList();
    }

    private void initList() {
        //商品列表
        list_mall = new ArrayList<>();
        for (int i = 0;i<5;i++){
            if(i == 3 || i==4){
                list_mall.add(new CommunityBean(0));
            }
            list_mall.add(new CommunityBean(1));
        }
        adapter_comm = new CommunityRecycleAdapter(mPresenter.mContext,rv_community);
        adapter_comm.setItems(list_mall);
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
}
