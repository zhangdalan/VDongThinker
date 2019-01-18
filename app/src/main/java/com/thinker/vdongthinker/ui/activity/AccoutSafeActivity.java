package com.thinker.vdongthinker.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.base.BaseActivity;

/**
 * Created by zjw on 2018/12/25.
 */
public class AccoutSafeActivity extends BaseActivity implements View.OnClickListener {

    private ImageView iv_back;
    private TextView tv_title;
    private LinearLayout ll_password,ll_tel;
    @Override
    public int getLayoutID() {
        return R.layout.activity_account_safe;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        iv_back = findViewById(R.id.iv_back);
        tv_title = findViewById(R.id.tv_title);
        ll_password = findViewById(R.id.ll_password);
        ll_tel = findViewById(R.id.ll_tel);
        iv_back.setOnClickListener(this);
        tv_title.setText("账户安全");
        ll_password.setOnClickListener(this);
        ll_tel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_tel:

                break;
            case R.id.ll_password:

                break;
        }
    }
}
