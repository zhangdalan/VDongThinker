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
import com.thinker.vdongthinker.presenter.SetPasswordPresenter;
import com.thinker.vdongthinker.view.SetPasswordView;

/**
 * Created by zt on 2018/12/6.
 */

public class UpdateTelActivity extends BasePresenterActivity<SetPasswordPresenter> implements SetPasswordView,View.OnClickListener {
    private TextView tv_title,tv_getcode,tv_tel;
    private ImageView iv_back;
    private Button btn_commit;
    private EditText et_tel,et_code;

    @Override
    public int getLayoutID() {
        return R.layout.activity_update_tel;
    }

    @Override
    public void initData() {
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText(getResources().getText(R.string.title_update_tel));
        iv_back = findViewById(R.id.iv_back);
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setOnClickListener(this);
        tv_getcode = findViewById(R.id.tv_getcode);
        tv_getcode.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        tv_getcode.setOnClickListener(this);
        btn_commit = findViewById(R.id.btn_register);
        btn_commit.setOnClickListener(this);
        tv_tel = findViewById(R.id.tv_tel);
        et_tel = findViewById(R.id.et_tel);
        et_code = findViewById(R.id.et_code);
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
            case R.id.btn_commit:

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
