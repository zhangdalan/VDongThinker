package com.thinker.vdongthinker.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.adapter.BaseAdapterRecycler;
import com.thinker.vdongthinker.adapter.CourseIconRecyclerAdapter;
import com.thinker.vdongthinker.adapter.IndexRecyclerAdapter;
import com.thinker.vdongthinker.base.BasePresenterFragment;
import com.thinker.vdongthinker.base.Constants;
import com.thinker.vdongthinker.bean.CourseIconBean;
import com.thinker.vdongthinker.bean.IndexMallRecyclerBean;
import com.thinker.vdongthinker.presenter.CoursePresenter;
import com.thinker.vdongthinker.presenter.IndexPresenter;
import com.thinker.vdongthinker.tool.GlideImageLoader;
import com.thinker.vdongthinker.ui.activity.CourseTypeActivity;
import com.thinker.vdongthinker.view.CourseView;
import com.thinker.vdongthinker.view.IndexView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zt on 2018/12/11.
 */

public class FragmentCourse extends BasePresenterFragment<CoursePresenter> implements CourseView,OnBannerListener ,BaseAdapterRecycler.OnRecyclerViewItemClickListener,View.OnClickListener{
    private TextView tv_city;
    private ImageView iv_search,iv_back;
    private RecyclerView rv_course,rv_1,rv_2,rv_3;
    private TextView tv_show1,tv_show2,tv_show3;
    private Banner banner_show1,banner_show2,banner_show3;
    private ArrayList<String> imgs;
    private ArrayList<IndexMallRecyclerBean> list_1;
    private ArrayList<IndexMallRecyclerBean> list_2;
    private ArrayList<IndexMallRecyclerBean> list_3;
    private ArrayList<CourseIconBean> list_course;
    private IndexRecyclerAdapter adapter_1;
    private IndexRecyclerAdapter adapter_2;
    private IndexRecyclerAdapter adapter_3;
    private CourseIconRecyclerAdapter adapter_c;

    private TextView tv_more1,tv_more2,tv_more3;
    @Override
    public void initData() {
        tv_city = contentView.findViewById(R.id.tv_city);
        iv_search = contentView.findViewById(R.id.iv_search);
        iv_back = contentView.findViewById(R.id.iv_back);
        iv_back.setVisibility(View.GONE);
        rv_course = contentView.findViewById(R.id.rv_course);
        rv_1 = contentView.findViewById(R.id.rv_1);
        rv_2 = contentView.findViewById(R.id.rv_2);
        rv_3 = contentView.findViewById(R.id.rv_3);
        tv_show1 = contentView.findViewById(R.id.tv_show1);
        tv_show2 = contentView.findViewById(R.id.tv_show2);
        tv_show3 = contentView.findViewById(R.id.tv_show3);
        banner_show1 = contentView.findViewById(R.id.banner_show1);
        banner_show2 = contentView.findViewById(R.id.banner_show2);
        banner_show3 = contentView.findViewById(R.id.banner_show3);
        tv_more1 = contentView.findViewById(R.id.tv_more1);
        tv_more2 = contentView.findViewById(R.id.tv_more2);
        tv_more3 = contentView.findViewById(R.id.tv_more3);
        tv_more1.setOnClickListener(this);
        tv_more2.setOnClickListener(this);
        tv_more3.setOnClickListener(this);
        setRecycler();
        setBannerDate();
    }

    @Override
    public void initPresenter() {
        mPresenter = new CoursePresenter();
        mPresenter.init(getActivity() ,getContext() ,this);
    }

    @Override
    public int getLauoutID() {
        return R.layout.fragment_course;
    }

    @Override
    public void OnBannerClick(int position) {

    }
    private void setRecycler() {
        list_course = new ArrayList<>();
        for (int i = 0;i<10;i++){
            list_course.add(new CourseIconBean(Constants.course_icon[i],Constants.course_name[i]));
        }
        GridLayoutManager gridLayoutManager_c = new GridLayoutManager(getContext(),5);
        rv_course.setLayoutManager(gridLayoutManager_c);
        adapter_c = new CourseIconRecyclerAdapter(getContext(),rv_course);
        adapter_c.setItems(list_course);
        rv_course.setAdapter(adapter_c);
        adapter_c.setOnRecyclerViewItemClickListener(new BaseAdapterRecycler.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), CourseTypeActivity.class);
                intent.putExtra("type",list_course.get(position).getName());
                startActivity(intent);
            }
        });


        list_1 = new ArrayList<>();
        for (int i = 0;i<4;i++){
            list_1.add(new IndexMallRecyclerBean("商品标题","1000","1111"));
        }
        list_2 = new ArrayList<>();
        for (int i = 0;i<4;i++){
            list_2.add(new IndexMallRecyclerBean("商品标题","2000","2222"));
        }
        list_3 = new ArrayList<>();
        for (int i = 0;i<4;i++){
            list_3.add(new IndexMallRecyclerBean("商品标题","3000","3333"));
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getContext(),2);
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getContext(),2);
        rv_1.setLayoutManager(gridLayoutManager);
        rv_2.setLayoutManager(gridLayoutManager1);
        rv_3.setLayoutManager(gridLayoutManager2);
        adapter_1 = new IndexRecyclerAdapter(getContext(),rv_1);
        adapter_1.setItems(list_1);
        rv_1.setAdapter(adapter_1);
        adapter_2 = new IndexRecyclerAdapter(getContext(),rv_2);
        adapter_2.setItems(list_2);
        rv_2.setAdapter(adapter_2);
        adapter_3 = new IndexRecyclerAdapter(getContext(),rv_3);
        adapter_3.setItems(list_3);
        rv_3.setAdapter(adapter_3);

        adapter_1.setOnRecyclerViewItemClickListener(this);
        adapter_2.setOnRecyclerViewItemClickListener(this);
        adapter_3.setOnRecyclerViewItemClickListener(this);
    }
    private void setBannerDate(){
        imgs = new ArrayList<>();
        imgs.add("");
        imgs.add("");
        imgs.add("");
        /*banner*/
        banner_show1.setIndicatorGravity(BannerConfig.CENTER);
        banner_show1.setOnBannerListener(this);
        banner_show1.setImages(imgs).setImageLoader(new GlideImageLoader(true)).start() ;
        banner_show2.setIndicatorGravity(BannerConfig.CENTER);
        banner_show2.setOnBannerListener(this);
        banner_show2.setImages(imgs).setImageLoader(new GlideImageLoader(true)).start() ;
        banner_show3.setIndicatorGravity(BannerConfig.CENTER);
        banner_show3.setOnBannerListener(this);
        banner_show3.setImages(imgs).setImageLoader(new GlideImageLoader(true)).start() ;
    }

    //recycler
    @Override
    public void onItemClick(View view, int position) {
        switch (view.getId()){
            case R.id.rv_course:
                Intent intent = new Intent(getActivity(), CourseTypeActivity.class);
                intent.putExtra("type",list_course.get(position).getName());
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_more1:

                break;
            case R.id.tv_more2:

                break;
            case R.id.tv_more3:

                break;
        }
    }
}
