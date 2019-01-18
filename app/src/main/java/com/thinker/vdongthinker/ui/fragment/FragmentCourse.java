package com.thinker.vdongthinker.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.adapter.BaseAdapterRecycler;
import com.thinker.vdongthinker.adapter.CourseIconRecyclerAdapter;
import com.thinker.vdongthinker.adapter.CourseRecyclerAdapter;
import com.thinker.vdongthinker.adapter.IndexRecyclerAdapter;
import com.thinker.vdongthinker.base.BasePresenterFragment;
import com.thinker.vdongthinker.base.Constants;
import com.thinker.vdongthinker.bean.CourseDetailBean;
import com.thinker.vdongthinker.bean.CourseIconBean;
import com.thinker.vdongthinker.bean.CourseJsonBean;
import com.thinker.vdongthinker.bean.IndexMallRecyclerBean;
import com.thinker.vdongthinker.bean.ResponseEntity;
import com.thinker.vdongthinker.presenter.CoursePresenter;
import com.thinker.vdongthinker.presenter.IndexPresenter;
import com.thinker.vdongthinker.tool.GlideImageLoader;
import com.thinker.vdongthinker.tool.Util;
import com.thinker.vdongthinker.ui.activity.CourseDetailActivity;
import com.thinker.vdongthinker.ui.activity.CourseTypeActivity;
import com.thinker.vdongthinker.view.CourseView;
import com.thinker.vdongthinker.view.IndexView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.lang.reflect.Type;
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
    private List<CourseJsonBean> list;
    private List<CourseDetailBean> list_1;
    private List<CourseDetailBean> list_2;
    private List<CourseDetailBean> list_3;
    private ArrayList<CourseIconBean> list_course;
    private CourseRecyclerAdapter adapter_1;
    private CourseRecyclerAdapter adapter_2;
    private CourseRecyclerAdapter adapter_3;
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
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });

        String json_course = Util.getJson("course.json",mPresenter.mContext);
        Type type_course = new TypeToken<ResponseEntity<List<CourseJsonBean>>>() {
        }.getType();
        ResponseEntity<List<CourseJsonBean>> entity_course = gson.fromJson(json_course, type_course);
        list = entity_course.getData();
        tv_show1.setText(list.get(0).getName());
        tv_show2.setText(list.get(1).getName());
        tv_show3.setText(list.get(2).getName());
        list_1 = list.get(0).getList().size()>3?list.get(0).getList().subList(0,4):list.get(0).getList();
        list_2 = list.get(1).getList().size()>3?list.get(1).getList().subList(0,4):list.get(1).getList();
        list_3 = list.get(2).getList().size()>3?list.get(2).getList().subList(0,4):list.get(2).getList();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        rv_1.setLayoutManager(gridLayoutManager);
        adapter_1 = new CourseRecyclerAdapter(getContext(),rv_1);
        adapter_1.setItems(list_1);
        rv_1.setAdapter(adapter_1);
        adapter_1.setOnRecyclerViewItemClickListener(new BaseAdapterRecycler.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //跳转商品详情
                Intent intent = new Intent(mPresenter.mContext,CourseDetailActivity.class);
                intent.putExtra("bean",list_1.get(position));
                intent.putExtra("type",tv_show1.getText().toString());
                startActivity(intent);
            }
        });
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getContext(),2);
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getContext(),2);
        rv_2.setLayoutManager(gridLayoutManager1);
        rv_3.setLayoutManager(gridLayoutManager2);
        adapter_2 = new CourseRecyclerAdapter(getContext(),rv_2);
        adapter_2.setItems(list_2);
        rv_2.setAdapter(adapter_2);
        adapter_3 = new CourseRecyclerAdapter(getContext(),rv_3);
        adapter_3.setItems(list_3);
        rv_3.setAdapter(adapter_3);

        adapter_2.setOnRecyclerViewItemClickListener(new BaseAdapterRecycler.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //跳转商品详情
                Intent intent = new Intent(mPresenter.mContext,CourseDetailActivity.class);
                intent.putExtra("bean",list_2.get(position));
                intent.putExtra("type",tv_show2.getText().toString());
                startActivity(intent);
            }
        });
        adapter_3.setOnRecyclerViewItemClickListener(new BaseAdapterRecycler.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //跳转商品详情
                Intent intent = new Intent(mPresenter.mContext,CourseDetailActivity.class);
                intent.putExtra("bean",list_3.get(position));
                intent.putExtra("type",tv_show3.getText().toString());
                startActivity(intent);
            }
        });
    }
    private void setBannerDate(){
        List<Integer> imgs1 = new ArrayList<>();
        List<Integer> imgs2 = new ArrayList<>();
        List<Integer> imgs3 = new ArrayList<>();
        imgs1.add(R.drawable.img_course_b1);
        imgs2.add(R.drawable.img_course_b2);
        imgs3.add(R.drawable.img_course_b3);
        /*banner*/
        banner_show1.setIndicatorGravity(BannerConfig.CENTER);
        banner_show1.setOnBannerListener(this);
        banner_show1.setImages(imgs1).setImageLoader(new GlideImageLoader(true)).start() ;
        banner_show2.setIndicatorGravity(BannerConfig.CENTER);
        banner_show2.setOnBannerListener(this);
        banner_show2.setImages(imgs2).setImageLoader(new GlideImageLoader(true)).start() ;
        banner_show3.setIndicatorGravity(BannerConfig.CENTER);
        banner_show3.setOnBannerListener(this);
        banner_show3.setImages(imgs3).setImageLoader(new GlideImageLoader(true)).start() ;
    }

    //recycler
    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(getActivity(), CourseDetailActivity.class);
//        intent.putExtra("type",list_course.get(position).getName());
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_more1:
                Intent intent = new Intent(getActivity(), CourseTypeActivity.class);
                intent.putExtra("type",list_course.get(0).getName());
                intent.putExtra("position",0);
                startActivity(intent);
                break;
            case R.id.tv_more2:
                Intent intent1 = new Intent(getActivity(), CourseTypeActivity.class);
                intent1.putExtra("type",list_course.get(1).getName());
                intent1.putExtra("position",1);
                startActivity(intent1);
                break;
            case R.id.tv_more3:
                Intent intent2 = new Intent(getActivity(), CourseTypeActivity.class);
                intent2.putExtra("type",list_course.get(2).getName());
                intent2.putExtra("position",2);
                startActivity(intent2);
                break;
        }
    }
}
