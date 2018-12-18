package com.thinker.vdongthinker.ui.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.base.BasePresenterActivity;
import com.thinker.vdongthinker.presenter.RegisterPresenter;
import com.thinker.vdongthinker.view.RegisterView;

/**
 * Created by zt on 2018/12/6.
 */

public class RegisterActivity extends BasePresenterActivity<RegisterPresenter> implements RegisterView,View.OnClickListener {
    private TextView tv_title,tv_getcode;
    private ImageView iv_back;
    private Button btn_register;
    @Override
    public int getLayoutID() {
        return R.layout.activity_register;
    }

    @Override
    public void initData() {
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText(getResources().getText(R.string.title_register));
        iv_back = findViewById(R.id.iv_back);
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setOnClickListener(this);
        tv_getcode = findViewById(R.id.tv_getcode);
        tv_getcode.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        tv_getcode.setOnClickListener(this);
        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);
    }

    @Override
    public void initPresenter() {
        mPresenter = new RegisterPresenter();
        mPresenter.init(this,this,this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_getcode:

                break;
            case R.id.btn_register:

                break;
        }
    }
}
