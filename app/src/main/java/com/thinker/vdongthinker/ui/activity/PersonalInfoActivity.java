package com.thinker.vdongthinker.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.base.BasePresenterActivity;
import com.thinker.vdongthinker.presenter.PersonalInfoPresenter;
import com.thinker.vdongthinker.view.PersonalInfoView;

/**
 * Created by zjw on 2018/12/25.
 */
public class PersonalInfoActivity extends BasePresenterActivity<PersonalInfoPresenter> implements PersonalInfoView ,View.OnClickListener {
    private ImageView iv_back,iv_function;
    private TextView tv_title;
    private ImageView iv_touxiang;
    private TextView tv_name,tv_num;
    private LinearLayout ll_my_course,ll_student,ll_yijian,ll_set,ll_info;
    @Override
    public void initPresenter() {
        mPresenter = new PersonalInfoPresenter();
        mPresenter.init(this,this,this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_personal_info;
    }

    @Override
    public void initData() {
        iv_back = findViewById(R.id.iv_back);
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setOnClickListener(this);
        iv_function = findViewById(R.id.iv_function);
        iv_function.setVisibility(View.VISIBLE);
        iv_function.setImageResource(R.mipmap.icon_message);
        iv_function.setOnClickListener(this);
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText("个人中心");
        iv_touxiang = findViewById(R.id.iv_touxiang);
        tv_name = findViewById(R.id.tv_name);
        tv_num = findViewById(R.id.tv_num);
        ll_my_course = findViewById(R.id.ll_my_course);
        ll_student = findViewById(R.id.ll_student);
        ll_yijian = findViewById(R.id.ll_yijian);
        ll_set = findViewById(R.id.ll_set);
        ll_info = findViewById(R.id.ll_info);
        ll_my_course.setOnClickListener(this);
        ll_student.setOnClickListener(this);
        ll_yijian.setOnClickListener(this);
        ll_set.setOnClickListener(this);
        ll_info.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_my_course:
                startActivity(new Intent(this,MyTrainActivity.class));
                break;
            case R.id.ll_student:
                Intent intent = new Intent(this,SelectAddressActivity.class);
                intent.putExtra("PAGE_TYPE",0);
                break;
            case R.id.ll_yijian:
                startActivity(new Intent(this,YiJianActivity.class));
                break;
            case R.id.ll_info:
                startActivity(new Intent(this,MyCommunityActivity.class));
                break;
            case R.id.iv_function:
                startActivity(new Intent(this,MessageActivity.class));
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
