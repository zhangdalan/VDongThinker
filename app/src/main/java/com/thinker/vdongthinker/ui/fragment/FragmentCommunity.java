package com.thinker.vdongthinker.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.adapter.AgencyRecyclerAdapter;
import com.thinker.vdongthinker.adapter.CommunityRecycleAdapter;
import com.thinker.vdongthinker.base.BasePresenterFragment;
import com.thinker.vdongthinker.base.Constants;
import com.thinker.vdongthinker.bean.CommunityBean;
import com.thinker.vdongthinker.bean.CourseAssessBean;
import com.thinker.vdongthinker.bean.ResponseEntity;
import com.thinker.vdongthinker.presenter.CommunityFragmentPresenter;
import com.thinker.vdongthinker.tool.Util;
import com.thinker.vdongthinker.ui.activity.CommunityDetailActivity;
import com.thinker.vdongthinker.ui.activity.CommunityPhotoActivity;
import com.thinker.vdongthinker.ui.activity.NewReleaseActivity;
import com.thinker.vdongthinker.view.CommunityFragmentView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjw on 2018/12/17.
 */

public class FragmentCommunity extends BasePresenterFragment<CommunityFragmentPresenter> implements CommunityFragmentView ,CommunityRecycleAdapter.OnCommunityClickListener,View.OnClickListener{
    private ImageView iv_touxiang,iv_search,iv_add_release;
    private RecyclerView rv_community;
    private List<CommunityBean> list_comm;
    private CommunityRecycleAdapter adapter_comm;
    Gson gson = new Gson();

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
        String json = Util.getJson("community.json",mPresenter.mContext);
        Type type = new TypeToken<ResponseEntity<List<CommunityBean>>>() {
        }.getType();
        ResponseEntity<List<CommunityBean>> entity = gson.fromJson(json, type);
        list_comm = entity.getData();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mPresenter.mContext,1);
        rv_community.setLayoutManager(gridLayoutManager);
        adapter_comm = new CommunityRecycleAdapter(mPresenter.mContext,rv_community);
        adapter_comm.setItems(list_comm);
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
        Intent intent = new Intent(mPresenter.mActivity, CommunityDetailActivity.class);
        intent.putExtra("bean",list_comm.get(position));
        startActivity(intent);
    }

    @Override
    public void onGridViewClick(int position) {
        Intent intent = new Intent(mPresenter.mActivity, CommunityPhotoActivity.class);
        intent.putExtra("bean",list_comm.get(position));
        intent.putExtra("current",position+1);
        startActivity(intent);
    }

    @Override
    public void onAssessClick(int position) {

    }

    @Override
    public void onZanClick(int position) {

    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(mPresenter.mActivity, CommunityDetailActivity.class);
        intent.putExtra("bean",list_comm.get(position));
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_add_release:

                Toast.makeText(mPresenter.mContext,"该功能暂未开放...",Toast.LENGTH_SHORT);
//                startActivity(new Intent(mPresenter.mActivity, NewReleaseActivity.class));
                break;
        }
    }
}
