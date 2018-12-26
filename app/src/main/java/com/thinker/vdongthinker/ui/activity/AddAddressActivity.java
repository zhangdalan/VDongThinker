package com.thinker.vdongthinker.ui.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.base.BasePresenterActivity;
import com.thinker.vdongthinker.presenter.AddAddressPresenter;
import com.thinker.vdongthinker.view.AddAddressView;

import java.util.Calendar;

public class AddAddressActivity extends BasePresenterActivity<AddAddressPresenter> implements AddAddressView ,View.OnClickListener {
    private TextView tv_title;
    private ImageView iv_back;
    private EditText et_name,et_phone,et_address;
    private TextView tv_city,tv_birth;
    private int PAGE_TYPE = 0;//0添加学生信息，1添加收货地址
    private LinearLayout ll_sex,ll_birth,ll_phone;
    private View view_line1,view_line2,view_line3;
    private RadioGroup rg_sex;
    private RadioButton rb_man,rb_woman;
    @Override
    public void initData() {
        PAGE_TYPE = getIntent().getIntExtra("PAGE_TYPE",0);
        tv_title = findViewById(R.id.tv_title);

        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        iv_back.setVisibility(View.VISIBLE);
        et_name = findViewById(R.id.et_name);
        et_phone = findViewById(R.id.et_phone);
        et_address = findViewById(R.id.et_address);
        tv_city = findViewById(R.id.tv_city);
        tv_city.setOnClickListener(this);

        ll_sex = findViewById(R.id.ll_sex);
        ll_birth = findViewById(R.id.ll_birth);
        ll_phone = findViewById(R.id.ll_phone);
        view_line1 = findViewById(R.id.view_line1);
        view_line2 = findViewById(R.id.view_line2);
        view_line3 = findViewById(R.id.view_line3);
        rg_sex = findViewById(R.id.rg_sex);
        rb_man = findViewById(R.id.rb_man);
        rb_woman = findViewById(R.id.rb_woman);
        tv_birth = findViewById(R.id.tv_birth);
        if (PAGE_TYPE == 0){
            tv_title.setText("完善学生信息");
            ll_sex.setVisibility(View.VISIBLE);
            ll_birth.setVisibility(View.VISIBLE);
            ll_phone.setVisibility(View.GONE);
            view_line1.setVisibility(View.VISIBLE);
            view_line2.setVisibility(View.VISIBLE);
            view_line3.setVisibility(View.GONE);
        }else{
            tv_title.setText("添加收货地址");
            ll_sex.setVisibility(View.GONE);
            ll_birth.setVisibility(View.GONE);
            ll_phone.setVisibility(View.VISIBLE);
            view_line1.setVisibility(View.GONE);
            view_line2.setVisibility(View.GONE);
            view_line3.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void initPresenter() {
        mPresenter = new AddAddressPresenter();
        mPresenter.init(this,this,this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_add_address;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_birth:
                showDatePickerDialog(this,0,tv_birth,Calendar.getInstance());
                break;
        }
    }
    /**
     * 日期选择
     * @param activity
     * @param themeResId
     * @param tv
     * @param calendar
     */
    public static void showDatePickerDialog(Activity activity, int themeResId, final TextView tv, Calendar calendar) {
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(activity , themeResId,new DatePickerDialog.OnDateSetListener() {
            // 绑定监听器(How the parent is notified that the date is set.)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 此处得到选择的时间，可以进行你想要的操作
                tv.setText( year + "-" + (monthOfYear+1)+ "-" + dayOfMonth );

            }
        }
                // 设置初始日期
                , calendar.get(Calendar.YEAR)
                ,calendar.get(Calendar.MONTH)
                ,calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}
