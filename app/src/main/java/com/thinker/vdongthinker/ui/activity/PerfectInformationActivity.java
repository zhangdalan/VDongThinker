package com.thinker.vdongthinker.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.base.BasePresenterActivity;
import com.thinker.vdongthinker.presenter.PerfectInformationPresenter;
import com.thinker.vdongthinker.view.PerfectInformationView;

/**
 * Created by zjw on 2018/12/7.
 */

public class PerfectInformationActivity extends BasePresenterActivity<PerfectInformationPresenter> implements PerfectInformationView ,View.OnClickListener{

    private TextView tv_title,tv_function,tv_birth;
    private ImageView iv_touxiang,iv_add;
    private RelativeLayout rl_touxiang;
    private EditText et_name,et_tel,et_address;
    private RadioButton rb_man,rb_woman;
    private Button btn_commit;

    @Override
    public void initData() {
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText(getResources().getText(R.string.title_perfect_information));
        tv_function = findViewById(R.id.tv_function);
        tv_function.setText(getResources().getText(R.string.btn_next));
        tv_function.setVisibility(View.VISIBLE);
        tv_function.setOnClickListener(this);
        iv_touxiang = findViewById(R.id.iv_touxiang);
        iv_add = findViewById(R.id.iv_add);
        iv_touxiang.setOnClickListener(this);
        iv_add.setOnClickListener(this);
        rl_touxiang = findViewById(R.id.rl_touxiang);
        et_name = findViewById(R.id.et_name);
        et_tel = findViewById(R.id.et_tel);
        tv_birth = findViewById(R.id.tv_birth);
        et_address = findViewById(R.id.et_address);
        rb_man = findViewById(R.id.rb_man);
        rb_woman = findViewById(R.id.rb_woman);
        btn_commit = findViewById(R.id.btn_commit);
    }

    @Override
    public void initPresenter() {
        mPresenter = new PerfectInformationPresenter();
        mPresenter.init(this,this,this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_perfect_information;
    }

    @Override
    public void onClick(View v) {

    }
}
