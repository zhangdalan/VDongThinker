package com.thinker.vdongthinker.ui.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.adapter.AgencyRecyclerAdapter;
import com.thinker.vdongthinker.adapter.BaseAdapterRecycler;
import com.thinker.vdongthinker.adapter.CourseIconRecyclerAdapter;
import com.thinker.vdongthinker.adapter.IndexRecyclerAdapter;
import com.thinker.vdongthinker.base.BasePresenterActivity;
import com.thinker.vdongthinker.base.Constants;
import com.thinker.vdongthinker.bean.AgencyJsonBean;
import com.thinker.vdongthinker.bean.AgencyMallRecyclerBean;
import com.thinker.vdongthinker.bean.CommunityBean;
import com.thinker.vdongthinker.bean.CourseDetailBean;
import com.thinker.vdongthinker.bean.CourseIconBean;
import com.thinker.vdongthinker.bean.IndexMallRecyclerBean;
import com.thinker.vdongthinker.bean.ResponseEntity;
import com.thinker.vdongthinker.presenter.AgencyTypePresenter;
import com.thinker.vdongthinker.presenter.CourseTypePresenter;
import com.thinker.vdongthinker.tool.Util;
import com.thinker.vdongthinker.view.AgencyTypeView;
import com.thinker.vdongthinker.view.CourseView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjw on 2018/12/12.
 */

public class AgencyTypeActivity extends BasePresenterActivity<AgencyTypePresenter> implements AgencyTypeView,View.OnClickListener{
    private TextView tv_title,tv_city;
    private ImageView iv_back,iv_search;
    private RecyclerView rv_agency,rv_type;
    private ArrayList<CourseIconBean> list_course;
    private List<AgencyJsonBean> list_mall;
    private CourseIconRecyclerAdapter adapter_type;
    private AgencyRecyclerAdapter adapter_agency;
    Gson gson = new Gson();
    private int select_position;
    private List<AgencyMallRecyclerBean>  list;

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
        rv_agency = findViewById(R.id.rv_agency);
        tv_title.setText(title);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        rv_agency.setLayoutManager(gridLayoutManager);
        adapter_agency = new AgencyRecyclerAdapter(this,rv_agency);
        setCourseType();
    }

    @Override
    public void initPresenter() {
        mPresenter = new AgencyTypePresenter();
        mPresenter.init(this,this,this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_agency_type;
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
        String json = Util.getJson("organization.json",mPresenter.mContext);
        Type type = new TypeToken<ResponseEntity<List<AgencyJsonBean>>>() {
        }.getType();
        ResponseEntity<List<AgencyJsonBean>> entity = gson.fromJson(json, type);
        list_mall = entity.getData();

        if(position == 9){
            for (int i = 0;i < list_mall.size(); i++){
                list.addAll(list_mall.get(i).getList());
            }
            adapter_agency.setItems(list);
        }else{
            adapter_agency.setItems(list_mall.get(position).getList());
        }
        rv_agency.setAdapter(adapter_agency);
        rv_agency.setNestedScrollingEnabled(false);
        adapter_agency.setOnRecyclerViewItemClickListener(new BaseAdapterRecycler.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(AgencyTypeActivity.this, AgencyDetailActivity.class);
//                intent.putExtra("type",list_course.get(select_position).getName());
                intent.putExtra("bean",list.get(position));
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
