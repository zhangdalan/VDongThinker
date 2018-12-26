package com.thinker.vdongthinker.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.base.BaseActivity;

/**
 * Created by zjw on 2018/12/25.
 */
public class SetActivity extends BaseActivity implements View.OnClickListener {

    private ImageView iv_back;
    private TextView tv_title,tv_version;
    private LinearLayout ll_account_safe,ll_version,ll_about_us;
    private Button btn_out;
    @Override
    public int getLayoutID() {
        return R.layout.activity_set;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        iv_back = findViewById(R.id.iv_back);
        tv_title = findViewById(R.id.tv_title);
        ll_account_safe = findViewById(R.id.ll_account_safe);
        ll_version = findViewById(R.id.ll_version);
        ll_about_us = findViewById(R.id.ll_about_us);
        tv_version = findViewById(R.id.tv_version);
        btn_out = findViewById(R.id.btn_out);
        iv_back.setOnClickListener(this);
        tv_title.setText("设置");
        ll_account_safe.setOnClickListener(this);
        ll_version.setOnClickListener(this);
        ll_about_us.setOnClickListener(this);
        btn_out.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_account_safe:

                break;
            case R.id.ll_version:

                break;
            case R.id.ll_about_us:

                break;
            case R.id.btn_out:

                break;
        }
    }
}
