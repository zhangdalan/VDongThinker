package com.thinker.vdongthinker.ui.activity;

import android.graphics.Paint;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.base.BasePresenterActivity;
import com.thinker.vdongthinker.presenter.FindPasswordPresenter;
import com.thinker.vdongthinker.view.FindPasswordView;

/**
 * Created by zt on 2018/12/7.
 */

public class FindPasswordActivity extends BasePresenterActivity<FindPasswordPresenter> implements FindPasswordView,View.OnClickListener {
    private ImageView iv_back;
    private TextView tv_title;
    private EditText et_phone,et_code,et_password,et_password_agin;
    private TextView tv_getcode;
    private Button btn_finish;
    @Override
    public void initData() {
        iv_back = findViewById(R.id.iv_back);
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setOnClickListener(this);
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText(getResources().getText(R.string.title_find_password));
        et_phone = findViewById(R.id.et_phone);
        et_code = findViewById(R.id.et_code);
        et_password = findViewById(R.id.et_password);
        et_password_agin = findViewById(R.id.et_password_agin);
        tv_getcode = findViewById(R.id.tv_getcode);
        tv_getcode.setOnClickListener(this);
        tv_getcode.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        btn_finish = findViewById(R.id.btn_finish);
        btn_finish.setOnClickListener(this);
    }

    @Override
    public void initPresenter() {
        mPresenter = new FindPasswordPresenter();
        mPresenter.init(this,this,this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_find_password;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_getcode:

                break;
            case R.id.btn_finish:

                break;
        }
    }
}
