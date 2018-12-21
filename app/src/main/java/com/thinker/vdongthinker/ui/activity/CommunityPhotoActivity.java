package com.thinker.vdongthinker.ui.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.base.BasePresenterActivity;
import com.thinker.vdongthinker.presenter.CommunityPhotoPresenter;
import com.thinker.vdongthinker.tool.Util;
import com.thinker.vdongthinker.view.CommunityPhotoView;

/**
 * Created by zt on 2018/12/18.
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
    @Override
    public void initData() {
        rl_title = findViewById(R.id.rl_title);
        ll_content = findViewById(R.id.ll_content);
        ll_assess = findViewById(R.id.ll_assess);
        iv_touxiang = findViewById(R.id.iv_touxiang);
        iv_back = findViewById(R.id.iv_back);
        iv_zan = findViewById(R.id.iv_zan);
        tv_name = findViewById(R.id.tv_name);
        tv_page = findViewById(R.id.tv_page);
        tv_type = findViewById(R.id.tv_type);
        tv_content = findViewById(R.id.tv_content);
        btn_add = findViewById(R.id.btn_add);
        btn_send = findViewById(R.id.btn_send);
        et_assess = findViewById(R.id.et_assess);
        view_pager = findViewById(R.id.view_pager);
        Util.buttonBeyondKeyboardLayout(ll_content,ll_assess);
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
            case R.id.iv_zan:
                IS_ZAN = !IS_ZAN;
                if (IS_ZAN){
                    iv_zan.setImageResource(R.mipmap.icon_zan_on);
                }else{
                    iv_zan.setImageResource(R.mipmap.icon_zan_no);
                }
                break;
        }
    }
}
