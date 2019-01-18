package com.thinker.vdongthinker.ui.activity;

import android.graphics.Paint;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.base.BasePresenterActivity;
import com.thinker.vdongthinker.presenter.RegisterPresenter;
import com.thinker.vdongthinker.presenter.SetPasswordPresenter;
import com.thinker.vdongthinker.view.RegisterView;
import com.thinker.vdongthinker.view.SetPasswordView;

/**
 * Created by zt on 2018/12/6.
 */

public class SetPasswordActivity extends BasePresenterActivity<SetPasswordPresenter> implements SetPasswordView,View.OnClickListener {
    private TextView tv_title,tv_getcode;
    private ImageView iv_back;
    private Button btn_commit;
    private EditText et_password,et_password_agin;

    @Override
    public int getLayoutID() {
        return R.layout.activity_set_password;
    }

    @Override
    public void initData() {
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText(getResources().getText(R.string.title_set_password));
        iv_back = findViewById(R.id.iv_back);
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setOnClickListener(this);
        tv_getcode = findViewById(R.id.tv_getcode);
        tv_getcode.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        tv_getcode.setOnClickListener(this);
        btn_commit = findViewById(R.id.btn_register);
        btn_commit.setOnClickListener(this);
        et_password = findViewById(R.id.et_password);
        et_password_agin = findViewById(R.id.et_password_agin);
    }

    @Override
    public void initPresenter() {
        mPresenter = new SetPasswordPresenter();
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

    @Override
    public void setStatusBar() {
        super.setStatusBar();
        StatusBarUtil.setLightMode(this);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.white));
        StatusBarUtil.setTranslucent(this, 0);
    }
}
