package com.thinker.vdongthinker.ui.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.adapter.CommunityPhotoViewAdapter;
import com.thinker.vdongthinker.base.BasePresenterActivity;
import com.thinker.vdongthinker.bean.CommunityBean;
import com.thinker.vdongthinker.presenter.CommunityPhotoPresenter;
import com.thinker.vdongthinker.tool.StatusBarUtil;
import com.thinker.vdongthinker.tool.Util;
import com.thinker.vdongthinker.view.CommunityPhotoView;

import java.util.List;

/**
 * Created by zjw on 2018/12/18.
 */

public class CommunityPhotoActivity extends BasePresenterActivity<CommunityPhotoPresenter> implements CommunityPhotoView,View.OnClickListener {
    private RelativeLayout rl_title;
    private LinearLayout ll_content,ll_assess;
    private ImageView iv_touxiang,iv_back,iv_zan;
    private TextView tv_name,tv_page,tv_type,tv_content;
    private Button btn_add,btn_send;
    private EditText et_assess;
    private ViewPager view_pager;
    private boolean IS_ZAN = false;
    private CommunityBean bean;
    private int current = 1;
    private List<String> imgs;

    @Override
    public void initData() {
        bean = (CommunityBean) getIntent().getSerializableExtra("bean");
        current = getIntent().getIntExtra("current",0);
        rl_title = findViewById(R.id.rl_title);
        ll_content = findViewById(R.id.ll_content);
        ll_assess = findViewById(R.id.ll_assess);
        iv_touxiang = findViewById(R.id.iv_touxiang);
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        iv_zan = findViewById(R.id.iv_zan);
        iv_zan.setOnClickListener(this);
        tv_name = findViewById(R.id.tv_name);
        tv_page = findViewById(R.id.tv_page);
        tv_type = findViewById(R.id.tv_type);
        tv_content = findViewById(R.id.tv_content);
        btn_add = findViewById(R.id.btn_add);
        btn_send = findViewById(R.id.btn_send);
        et_assess = findViewById(R.id.et_assess);
        view_pager = findViewById(R.id.view_pager);
        Util.buttonBeyondKeyboardLayout(ll_content,ll_assess);

        setDate();
    }

    @Override
    public void initPresenter() {
        mPresenter = new CommunityPhotoPresenter();
        mPresenter.init(this,this,this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_community_photo;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_zan:
                IS_ZAN = !IS_ZAN;
                setZan();
                break;
            case R.id.et_assess:
                Toast.makeText(this,"该功能暂未开放...",Toast.LENGTH_SHORT);
                break;
        }
    }
    private void setZan(){
        if (IS_ZAN){
            iv_zan.setImageResource(R.mipmap.icon_zan_on);
        }else{
            iv_zan.setImageResource(R.mipmap.icon_zan_no);
        }
    }
    private void setDate(){
//        iv_touxiang.
        tv_name.setText(bean.getName());
        if(bean.getIS_GUANZHU() == 0){
            btn_add.setVisibility(View.VISIBLE);
        }else{
            btn_add.setVisibility(View.GONE);
        }
        if (bean.getIS_ZAN() == 1){
            IS_ZAN = true;
        }
        tv_type.setText("#"+bean.getType()+"#");
        tv_content.setText(bean.getContent());
        setZan();
        setPhoto();
    }
    private void setPhoto(){
        current = getIntent().getIntExtra("current",1);
        imgs = bean.getList_img();
        CommunityPhotoViewAdapter adapter = new CommunityPhotoViewAdapter(imgs,this);
        view_pager.setAdapter(adapter);
        view_pager.setCurrentItem(current);
//        view_pager.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//                ProjectInfoImageActivity.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//
//            }
//        });
        tv_page.setText(current + "/" + imgs.size());
        view_pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tv_page.setText((position+1) + "/" + imgs.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void setStatusBar() {
        super.setStatusBar();
        com.jaeger.library.StatusBarUtil.setColor(this, getResources().getColor(R.color.white));
        com.jaeger.library.StatusBarUtil.setTranslucent(this, 0);
    }
}
