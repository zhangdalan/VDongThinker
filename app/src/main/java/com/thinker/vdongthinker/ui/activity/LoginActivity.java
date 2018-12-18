package com.thinker.vdongthinker.ui.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.base.BaseActivity;
import com.thinker.vdongthinker.base.BasePresenterActivity;
import com.thinker.vdongthinker.presenter.LoginPresenter;
import com.thinker.vdongthinker.view.LoginView;

/**
 * Created by zt on 2018/12/6.
 */

public class LoginActivity extends BasePresenterActivity<LoginPresenter> implements LoginView ,View.OnClickListener{
    private TextView tv_title,tv_code,tv_password,tv_getcode,tv_forget_password,tv_function;
//    private ImageView iv_back;
    private View view_code,view_password;
    private EditText et_code,et_phone;
    private LinearLayout ll_login_code,ll_login_password;
    private int LOGIN_WAYS = 0;//登录方式，0验证码1密码
    @Override
    public void initPresenter() {
        mPresenter = new LoginPresenter() ;
        mPresenter.init(this ,this ,this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_login;
    }
    @Override
    public void initData() {
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText(getResources().getText(R.string.title_login));
        tv_function = findViewById(R.id.tv_function);
        tv_function.setText(getResources().getText(R.string.title_register));
        tv_function.setOnClickListener(this);
        tv_code = findViewById(R.id.tv_code);
        tv_code.setOnClickListener(this);
        tv_password = findViewById(R.id.tv_password);
        tv_password.setOnClickListener(this);
        tv_getcode = findViewById(R.id.tv_getcode);
        tv_getcode.setOnClickListener(this);
        tv_getcode.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        tv_forget_password = findViewById(R.id.tv_forget_password);
        tv_forget_password.setOnClickListener(this);
//        iv_back = findViewById(R.id.iv_back);
//        iv_back.setOnClickListener(this);
        view_code = findViewById(R.id.view_code);
        view_password = findViewById(R.id.view_password);
        et_code = findViewById(R.id.et_code);
        et_phone = findViewById(R.id.et_phone);
        ll_login_code = findViewById(R.id.ll_login_code);
        ll_login_code.setVisibility(View.VISIBLE);
        ll_login_password = findViewById(R.id.ll_login_password);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_code:
                LOGIN_WAYS = 0;
                ll_login_code.setVisibility(View.VISIBLE);
                ll_login_password.setVisibility(View.GONE);
                view_code.setVisibility(View.VISIBLE);
                view_password.setVisibility(View.INVISIBLE);
                break;
            case R.id.tv_password:
                LOGIN_WAYS = 1;
                ll_login_code.setVisibility(View.GONE);
                ll_login_password.setVisibility(View.VISIBLE);
                view_code.setVisibility(View.INVISIBLE);
                view_password.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_function:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
            case R.id.btn_login:

                break;
            case R.id.tv_getcode:

                break;
            case R.id.tv_forget_password:

                break;
        }
    }
}
