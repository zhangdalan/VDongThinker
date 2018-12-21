package com.thinker.vdongthinker.customControl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thinker.vdongthinker.R;

public class NumView extends LinearLayout implements View.OnClickListener {
    private Button btn_down,btn_up;
    private TextView tv_num;
    private int num = 1;
    public NumView(Context context) {
        super(context);
    }

    public NumView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_num, this);
        btn_down = findViewById(R.id.btn_down);
        btn_up = findViewById(R.id.btn_up);
        tv_num = findViewById(R.id.tv_num);
        btn_down.setOnClickListener(this);
        btn_up.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_down){//减
            if(num>1){
                num -= 1;
            }
        }else{//加
            num += 1;
        }
        tv_num.setText(num+"");
        onNumViewClickListener.onNumChangeClick(num);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        tv_num.setText(num+"");
    }

    private OnNumViewClickListener onNumViewClickListener;

    public void setOnNumViewClickListener(OnNumViewClickListener onNumViewClickListener) {
        this.onNumViewClickListener = onNumViewClickListener;
    }

    public interface OnNumViewClickListener{
        void onNumChangeClick(int num);
    }
}
