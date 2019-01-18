package com.thinker.vdongthinker.ui.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.adapter.BaseAdapterRecycler;
import com.thinker.vdongthinker.adapter.CourseIconRecyclerAdapter;
import com.thinker.vdongthinker.adapter.CourseRecyclerAdapter;
import com.thinker.vdongthinker.adapter.IndexRecyclerAdapter;
import com.thinker.vdongthinker.base.BasePresenterActivity;
import com.thinker.vdongthinker.base.Constants;
import com.thinker.vdongthinker.bean.CourseDetailBean;
import com.thinker.vdongthinker.bean.CourseIconBean;
import com.thinker.vdongthinker.bean.CourseJsonBean;
import com.thinker.vdongthinker.bean.IndexMallRecyclerBean;
import com.thinker.vdongthinker.bean.ResponseEntity;
import com.thinker.vdongthinker.presenter.CourseTypePresenter;
import com.thinker.vdongthinker.tool.Util;
import com.thinker.vdongthinker.view.CourseView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zt on 2018/12/12.
 */

public class CourseTypeActivity extends BasePresenterActivity<CourseTypePresenter> implements CourseView ,View.OnClickListener{
    private TextView tv_title,tv_city;
    private ImageView iv_back,iv_search;
    private RecyclerView rv_course,rv_type;
    private ArrayList<CourseIconBean> list_course;
    private List<CourseJsonBean> list_mall;
    private CourseIconRecyclerAdapter adapter_type;
    private CourseRecyclerAdapter adapter_course;
    private int select_position;
    private List<CourseDetailBean> list;
    @Override
    public void initData() {
        String title = getIntent().getStringExtra("type");
        select_position = getIntent().getIntExtra("position",0);

        tv_title = findViewById(R.id.tv_title);
        tv_title.setText(title);
        tv_city = findViewById(R.id.tv_city);
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        iv_search = findViewById(R.id.iv_search);
        rv_type = findViewById(R.id.rv_type);
        rv_course = findViewById(R.id.rv_course);
        tv_title.setText(title);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        rv_course.setLayoutManager(gridLayoutManager);
        adapter_course = new CourseRecyclerAdapter(mPresenter.mContext,rv_course);
        setCourseType();
    }

    @Override
    public void initPresenter() {
        mPresenter = new CourseTypePresenter();
        mPresenter.init(this,this,this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_course_type;
    }

    private void setCourseType(){
        list_course = new ArrayList<>();
        for (int i = 0;i<10;i++){
            list_course.add(new CourseIconBean(Constants.course_icon[i],Constants.course_name[i]));
        }
        GridLayoutManager gridLayoutManager_c = new GridLayoutManager(this,5);
        rv_type.setLayoutManager(gridLayoutManager_c);
        adapter_type = new CourseIconRecyclerAdapter(this,rv_type);
        adapter_type.setItems(list_course);
        rv_type.setAdapter(adapter_type);
        adapter_type.setOnRecyclerViewItemClickListener(new BaseAdapterRecycler.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                tv_title.setText(list_course.get(position).getName());
                select_position = position;
                setList(position);
            }
        });

        setList(select_position);


    }

    private void setList(int position) {
        list = new ArrayList<>();
        //商品列表
        String json_course = Util.getJson("course.json",mPresenter.mContext);
        Type type_course = new TypeToken<ResponseEntity<List<CourseJsonBean>>>() {
        }.getType();
        ResponseEntity<List<CourseJsonBean>> entity_course = gson.fromJson(json_course, type_course);
        list_mall = entity_course.getData();

        if(position == 9){
            for (int i = 0;i < list_mall.size(); i++){
                list.addAll(list_mall.get(i).getList());
            }
            adapter_course.setItems(list);
        }else{
            adapter_course.setItems(list_mall.get(position).getList());
        }
        rv_course.setAdapter(adapter_course);
        rv_course.setNestedScrollingEnabled(false);
        adapter_course.setOnRecyclerViewItemClickListener(new BaseAdapterRecycler.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //跳转商品详情
                Intent intent = new Intent(mPresenter.mContext,CourseDetailActivity.class);
                intent.putExtra("bean",list.get(position));
                intent.putExtra("type",list_mall.get(select_position).getName());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }

}
