package com.thinker.vdongthinker.ui.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.jaeger.library.StatusBarUtil;
import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.adapter.AgencyRecyclerAdapter;
import com.thinker.vdongthinker.adapter.BaseAdapterRecycler;
import com.thinker.vdongthinker.adapter.IndexRecyclerAdapter;
import com.thinker.vdongthinker.base.BasePresenterActivity;
import com.thinker.vdongthinker.bean.AgencyJsonBean;
import com.thinker.vdongthinker.bean.AgencyMallRecyclerBean;
import com.thinker.vdongthinker.bean.IndexMallRecyclerBean;
import com.thinker.vdongthinker.bean.ResponseEntity;
import com.thinker.vdongthinker.customControl.AutoTagView;
import com.thinker.vdongthinker.customControl.HotTagView;
import com.thinker.vdongthinker.presenter.SearchPresenter;
import com.thinker.vdongthinker.tool.Util;
import com.thinker.vdongthinker.view.SearchView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BasePresenterActivity<SearchPresenter> implements SearchView ,View.OnClickListener {
    private ImageView iv_back,iv_search;
    private TextView tv_cancle,tv_search_no,tv_hot,tv_topic;
    private EditText et_search;
    private AutoTagView atv_topic_hot,atv_topic;
    private int PAGE_TYPE = 0;//0发布，1首页，2机构
    private LinearLayout ll_type;
    private LinearLayout ll_course,ll_agency,ll_search_content,ll_hot,ll_join;
    private TextView tv_course,tv_agency;
    private View line1,line2;
    private RecyclerView rv_course,rv_agency;
    private List<IndexMallRecyclerBean> list_course;
    private List<AgencyJsonBean> list_agency;
    private IndexRecyclerAdapter adapter_course;
    private AgencyRecyclerAdapter adapter_agency;

    @Override
    public void initPresenter() {
        mPresenter = new SearchPresenter();
        mPresenter.init(this,this,this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_search;
    }

    @Override
    public void initData() {
        PAGE_TYPE = getIntent().getIntExtra("PAGE_TYPE",0);

        iv_back = findViewById(R.id.iv_back);
        iv_search = findViewById(R.id.iv_search);
        tv_cancle = findViewById(R.id.tv_cancle);
        et_search = findViewById(R.id.et_search);
        atv_topic_hot = findViewById(R.id.atv_topic_hot);
        atv_topic = findViewById(R.id.atv_topic);
        tv_search_no = findViewById(R.id.tv_search_no);
        atv_topic = findViewById(R.id.atv_topic);
        tv_hot = findViewById(R.id.tv_hot);
        tv_topic = findViewById(R.id.tv_topic);
        ll_hot = findViewById(R.id.ll_hot);
        ll_join = findViewById(R.id.ll_join);
        ll_type = findViewById(R.id.ll_type);
        ll_course = findViewById(R.id.ll_course);
        ll_agency = findViewById(R.id.ll_agency);
        ll_search_content = findViewById(R.id.ll_search_content);
        tv_course = findViewById(R.id.tv_course);
        tv_agency = findViewById(R.id.tv_agency);
        rv_course = findViewById(R.id.rv_course);
        rv_agency = findViewById(R.id.rv_agency);
        line1 = findViewById(R.id.line1);
        line2 = findViewById(R.id.line2);
        ll_course.setOnClickListener(this);
        ll_agency.setOnClickListener(this);

        ll_search_content.setVisibility(View.GONE);
        tv_search_no.setVisibility(View.GONE);
        iv_back.setOnClickListener(this);
        iv_search.setOnClickListener(this);
        tv_cancle.setOnClickListener(this);
        if (PAGE_TYPE == 1){
            setDateIndex();
        }else if (PAGE_TYPE == 2){
            setDateAgency();
        }else{
            setDateRealse();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
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
            case R.id.iv_search:
                ll_hot.setVisibility(View.GONE);
                ll_join.setVisibility(View.GONE);
                if(PAGE_TYPE == 1){//首页
                    ll_search_content.setVisibility(View.VISIBLE);
                    ll_type.setVisibility(View.VISIBLE);
                    rv_course.setVisibility(View.VISIBLE);
                    rv_agency.setVisibility(View.GONE);
                    setCourseList();
                    setAgencyList();
                }else if(PAGE_TYPE == 2){//机构
                    ll_search_content.setVisibility(View.VISIBLE);
                    ll_type.setVisibility(View.GONE);
                    rv_course.setVisibility(View.GONE);
                    rv_agency.setVisibility(View.VISIBLE);
                    setAgencyList();
                }else{//发布

                }
                break;
        }
    }

    @Override
    public void setStatusBar() {
        super.setStatusBar();
        //设置字体颜色为黑色
//        StatusBarUtil.setImmersiveStatusBar(this,true);
        //设置状态栏透明
//        StatusBarUtil.setTranslucentStatus(this);
        //设置状态栏的颜色
//        StatusBarUtil.setStatusBarColor(this, R.color.white);
        StatusBarUtil.setLightMode(this);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.white));
        StatusBarUtil.setTranslucent(this, 0);
    }

    /**
     * 发布搜索跳转
     */
    private void setDateRealse(){

    }

    /**
     * 首页搜索跳转
     */
    private void setDateIndex() {
        tv_hot.setText("#推荐类别#");
        String[] tags = new String[]{"哈喽啊", "小金毛", "哈哈", "啦啦啦", "萨瓦迪卡", "欢迎大家", "走吧"};
        for (int i = 0; i < tags.length; i++) {
            HotTagView tagView = new HotTagView(this);
            tagView.setTagText(tags[i]);
            tagView.setTextColor(getResources().getColor(R.color.text_gray));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(15, 15, 25, 10);
            tagView.setLayoutParams(params);
//            tv.setBackgroundResource(R.drawable.text_background);
            atv_topic_hot.addView(tagView);
        }

        tv_topic.setText("历史搜索");
        tv_topic.setTextColor(getResources().getColor(R.color.text_black));
        String[] tags1 = new String[]{"哈喽啊", "小金毛", "哈哈", "啦啦啦", "萨瓦迪卡", "欢迎大家", "走吧"};
        for (int i = 0; i < tags.length; i++) {
            TextView tagView = new TextView(this);
            tagView.setText(tags[i]);
            tagView.setTextColor(getResources().getColor(R.color.text_black));
            tagView.setTextSize(14);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(15, 15, 15, 10);
            tagView.setLayoutParams(params);
            tagView.setBackgroundResource(R.drawable.bg_corners_gray2);
            atv_topic.addView(tagView);
        }
    }

    /**
     * 机构搜索跳转
     */
    private void setDateAgency(){
        tv_hot.setText("#推荐类别#");
        String[] tags = new String[]{"哈喽啊", "小金毛", "哈哈", "啦啦啦", "萨瓦迪卡", "欢迎大家", "走吧"};
        for (int i = 0; i < tags.length; i++) {
            HotTagView tagView = new HotTagView(this);
            tagView.setTagText(tags[i]);
            tagView.setTextColor(getResources().getColor(R.color.text_gray));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(15, 15, 15, 10);
            tagView.setLayoutParams(params);
//            tv.setBackgroundResource(R.drawable.text_background);
            atv_topic_hot.addView(tagView);
        }

        tv_topic.setText("历史搜索");
        tv_topic.setTextColor(getResources().getColor(R.color.text_black));
        String[] tags1 = new String[]{"哈喽啊", "小金毛", "哈哈", "啦啦啦", "萨瓦迪卡", "欢迎大家", "走吧"};
        for (int i = 0; i < tags.length; i++) {
            TextView tagView = new TextView(this);
            tagView.setText(tags[i]);
            tagView.setTextColor(getResources().getColor(R.color.text_black));
            tagView.setTextSize(14);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(15, 15, 15, 10);
            tagView.setLayoutParams(params);
            tagView.setBackgroundResource(R.drawable.bg_corners_gray2);
            atv_topic.addView(tagView);
        }
    }

    /**
     * 课程列表
     */
    private void setCourseList(){
        list_course = new ArrayList<>();
        for (int i = 0;i<12;i++){
            list_course.add(new IndexMallRecyclerBean("商品标题","1000","1111"));
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        rv_course.setLayoutManager(gridLayoutManager);
        adapter_course = new IndexRecyclerAdapter(this,rv_course);
        adapter_course.setItems(list_course);
        rv_course.setAdapter(adapter_course);
        adapter_course.setOnRecyclerViewItemClickListener(new BaseAdapterRecycler.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(mPresenter.mContext,CourseDetailActivity.class));
            }
        });
    }

    /**
     * 机构列表
     */
    private void setAgencyList(){
        String json = Util.getJson("organization.json",mPresenter.mContext);
        Type type = new TypeToken<ResponseEntity<List<AgencyJsonBean>>>() {
        }.getType();
        ResponseEntity<List<AgencyJsonBean>> entity = gson.fromJson(json, type);
        list_agency = entity.getData();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        rv_agency.setLayoutManager(gridLayoutManager);
        adapter_agency = new AgencyRecyclerAdapter(this,rv_agency);
        adapter_agency.setItems(list_agency.get(0).getList());
        rv_agency.setAdapter(adapter_agency);
        adapter_agency.setOnRecyclerViewItemClickListener(new BaseAdapterRecycler.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(mPresenter.mContext,AgencyDetailActivity.class));
            }
        });
    }
}
