package com.thinker.vdongthinker.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.adapter.CommunityAssessListViewAdapter;
import com.thinker.vdongthinker.adapter.CommunityPhotoGridViewAdapter;
import com.thinker.vdongthinker.adapter.CourseAssessListViewAdapter;
import com.thinker.vdongthinker.adapter.CoursePhotoGridViewAdapter;
import com.thinker.vdongthinker.base.BasePresenterActivity;
import com.thinker.vdongthinker.bean.CourseAssessBean;
import com.thinker.vdongthinker.customControl.MeasureGridView;
import com.thinker.vdongthinker.customControl.MeasureListView;
import com.thinker.vdongthinker.presenter.CommunityDetailPresenter;
import com.thinker.vdongthinker.tool.Util;
import com.thinker.vdongthinker.view.CommunityDetailView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zt on 2018/12/18.
 */

public class CommunityDetailActivity extends BasePresenterActivity<CommunityDetailPresenter> implements CommunityDetailView,CommunityAssessListViewAdapter.OnReplayClickListener,View.OnClickListener {
    private ImageView iv_back;
    private TextView tv_name,tv_type,tv_content,tv_assess_num;
    private EditText et_assess;
    private View view_background;
    private MeasureGridView gv_photo;
    private MeasureListView lv_assess;
    private LinearLayout layout_send;
    private FrameLayout fl_send;
    private ArrayList<Integer> list_photo;
    private CommunityPhotoGridViewAdapter adapter_gv;
    private List<CourseAssessBean> list_assess;
    private CommunityAssessListViewAdapter adapter_assess;
    private InputMethodManager imm;

    @Override
    public void initData() {
        iv_back = findViewById(R.id.iv_back);
        tv_name = findViewById(R.id.tv_name);
        et_assess = findViewById(R.id.et_assess);
        et_assess.setOnClickListener(this);
        view_background = findViewById(R.id.view_background);
        view_background.setBackgroundResource(R.color.trans);
        view_background.setOnClickListener(this);
        tv_type = findViewById(R.id.tv_type);
        tv_content = findViewById(R.id.tv_content);
        tv_assess_num = findViewById(R.id.tv_assess_num);
        gv_photo = findViewById(R.id.gv_photo);
        lv_assess = findViewById(R.id.lv_assess);
        layout_send = findViewById(R.id.layout_send);
        fl_send = findViewById(R.id.fl_send);
        Util.buttonBeyondKeyboardLayout(fl_send,layout_send);
        setList();
        onReplay(0);
    }

    private void setList() {
        list_photo = new ArrayList<>();
        List<Integer> list_show = new ArrayList<>();
        for (int i = 0;i<4;i++){
            list_photo.add(R.mipmap.img_defult_mall);
            if(i<3){
                list_show.add(R.mipmap.img_defult_mall);
            }
        }
        adapter_gv = new CommunityPhotoGridViewAdapter(this,list_photo.size());
        adapter_gv.setItems(list_show);
        gv_photo.setAdapter(adapter_gv);
        gv_photo.setOverScrollMode(View.OVER_SCROLL_NEVER);
        gv_photo.setVisibility(View.VISIBLE);
        gv_photo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(CommunityDetailActivity.this,CommunityPhotoActivity.class));
            }
        });

        list_assess = new ArrayList<>();
        for (int i = 0;i<10;i++){
            list_assess.add(new CourseAssessBean("","小仙女啊","2019-01-01","声明：本文内容由互联网用户自发贡献自行上传，本网站不拥有所有权，未作人工编辑处理，也不承担相关法律责任。"));
        }
        adapter_assess = new CommunityAssessListViewAdapter(this);
        adapter_assess.setItems(list_assess);
        adapter_assess.setOnReplayClickListener(this);
        lv_assess.setAdapter(adapter_assess);
        lv_assess.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    @Override
    public void initPresenter() {
        mPresenter = new CommunityDetailPresenter();
        mPresenter.init(this,this,this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_community_detail;
    }



    @Override
    public void onReplay(int position) {
        //评论列表中点击回复，调出软键盘
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

        view_background.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.view_background:
//                view_background.setBackgroundResource(R.color.trans);
                view_background.setVisibility(View.GONE);
                break;
            case R.id.et_assess:
//                view_background.setBackgroundResource(R.color.trans_30black);
                view_background.setVisibility(View.VISIBLE);
                break;

        }
    }
}
