package com.thinker.vdongthinker.customControl;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thinker.vdongthinker.R;

/**
 * Created by zjw on 2018/12/26.
 */
public class HotTagView extends LinearLayout {
    private TextView tv_tag;
    public HotTagView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.layout_hot_tag, this);
        tv_tag = findViewById(R.id.tv_tag);
    }

    public HotTagView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_hot_tag, this);
        tv_tag = findViewById(R.id.tv_tag);
    }
    public void setTagText(String tag) {
        tv_tag.setText(tag);
    }
    public void setTextColor(int color) {
        tv_tag.setTextColor(color);
    }
}
