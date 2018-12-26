package com.thinker.vdongthinker.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.base.BaseActivity;

/**
 * Created by zjw on 2018/12/25.
 */
public class YiJianActivity extends BaseActivity implements View.OnClickListener {

    private ImageView iv_back;
    private TextView tv_title;
    private Button btn_account,btn_pay,btn_other;
    private EditText et_content;
    private TextView tv_num;
    private Button btn_commit;
    @Override
    public int getLayoutID() {
        return R.layout.activity_yijian;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        iv_back = findViewById(R.id.iv_back);
        tv_title = findViewById(R.id.tv_title);
        btn_account = findViewById(R.id.btn_account);
        btn_pay = findViewById(R.id.btn_pay);
        btn_other = findViewById(R.id.btn_other);
        et_content = findViewById(R.id.et_content);
        tv_num = findViewById(R.id.tv_num);
        btn_commit = findViewById(R.id.btn_commit);
        iv_back.setOnClickListener(this);
        tv_title.setText("意见反馈");
        btn_account.setOnClickListener(this);
        btn_pay.setOnClickListener(this);
        btn_other.setOnClickListener(this);
        btn_commit.setOnClickListener(this);

        /*字数限制监听*/
        et_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                int num = 200 ;
                int num1 = num - charSequence.length() ;
                String str_num = String.valueOf(num1);
                tv_num.setText(str_num);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_account:

                break;
            case R.id.btn_pay:

                break;
            case R.id.btn_other:

                break;
            case R.id.btn_commit:

                break;
        }
    }
}
