package com.thinker.vdongthinker.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.adapter.AgencyRecyclerAdapter;
import com.thinker.vdongthinker.adapter.CommunityRecycleAdapter;
import com.thinker.vdongthinker.base.BasePresenterFragment;
import com.thinker.vdongthinker.base.Constants;
import com.thinker.vdongthinker.bean.AgencyMallRecyclerBean;
import com.thinker.vdongthinker.bean.CommunityBean;
import com.thinker.vdongthinker.bean.CourseAssessBean;
import com.thinker.vdongthinker.presenter.CommunityFragmentPresenter;
import com.thinker.vdongthinker.ui.activity.CommunityDetailActivity;
import com.thinker.vdongthinker.ui.activity.CommunityPhotoActivity;
import com.thinker.vdongthinker.ui.activity.NewReleaseActivity;
import com.thinker.vdongthinker.view.CommunityFragmentView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjw on 2018/12/17.
 */

public class FragmentCommunity extends BasePresenterFragment<CommunityFragmentPresenter> implements CommunityFragmentView ,CommunityRecycleAdapter.OnCommunityClickListener,View.OnClickListener{
    private ImageView iv_touxiang,iv_search,iv_add_release;
    private RecyclerView rv_community;
    private ArrayList<CommunityBean> list_comm;
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
        list_comm = new ArrayList<>();
        List<String> list_img = new ArrayList<>();
        list_img.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545734398144&di=b61f3bd602ec411b25cc1a110bedf65c&imgtype=0&src=http%3A%2F%2Fimg67.jc35.com%2F9%2F20161027%2F636131770524317707568.jpg");
        list_img.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545734398143&di=c34340ac0ac072ae759730606b77a2bc&imgtype=0&src=http%3A%2F%2Fdiscovery.cctv.com%2F20070204%2Fimages%2F1170549068764_0013.jpg");
        list_img.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545734398142&di=849789a816d2889dc74effbab85f0473&imgtype=0&src=http%3A%2F%2Fi0.hexun.com%2F2018-11-04%2F195094376.jpg");
        list_img.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545734398142&di=e8c086a741dd73089c44715b8aba0411&imgtype=0&src=http%3A%2F%2Fimg2.jiemian.com%2F101%2Foriginal%2F20161219%2F148212991849952700_a580x330.jpg");
        list_img.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545734398144&di=b61f3bd602ec411b25cc1a110bedf65c&imgtype=0&src=http%3A%2F%2Fimg67.jc35.com%2F9%2F20161027%2F636131770524317707568.jpg");
        list_img.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545734398144&di=b61f3bd602ec411b25cc1a110bedf65c&imgtype=0&src=http%3A%2F%2Fimg67.jc35.com%2F9%2F20161027%2F636131770524317707568.jpg");
        list_img.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545734398144&di=b61f3bd602ec411b25cc1a110bedf65c&imgtype=0&src=http%3A%2F%2Fimg67.jc35.com%2F9%2F20161027%2F636131770524317707568.jpg");
        list_img.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545734398143&di=c34340ac0ac072ae759730606b77a2bc&imgtype=0&src=http%3A%2F%2Fdiscovery.cctv.com%2F20070204%2Fimages%2F1170549068764_0013.jpg");
        List<CourseAssessBean> list_assess = new ArrayList<>();
        for (int i = 0;i<10;i++){
            list_assess.add(new CourseAssessBean("","小仙女啊","2019-01-01","声明：本文内容由互联网用户自发贡献自行上传，本网站不拥有所有权，未作人工编辑处理，也不承担相关法律责任。"));
        }
        for (int i = 0;i<5;i++){
            if(i == 3 || i==4){
                        list_comm.add(new CommunityBean(0,"中思科技IT技术中心","机器人","","机器人大赛长文本长文本长文本机器人大赛长文本长文本长文本机器人大赛长文本长文本长文本",list_img
                        ,list_assess,Constants.TEXT_VEDIO_URL,"",i,0));
            }
            list_comm.add(new CommunityBean(1,"中思科技IT技术中心","机器人","","机器人大赛长文本长文本长文本机器人大赛长文本长文本长文本机器人大赛长文本长文本长文本",list_img
                    ,list_assess,Constants.TEXT_VEDIO_URL,"",i,0));
        }
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
