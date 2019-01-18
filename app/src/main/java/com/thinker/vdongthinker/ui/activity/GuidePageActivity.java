package com.thinker.vdongthinker.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.adapter.GuideAdapter;
import com.thinker.vdongthinker.base.BaseActivity;
import com.thinker.vdongthinker.tool.SharedPreferencesUtils;
import com.thinker.vdongthinker.tool.Util;

public class GuidePageActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private String TAG = this.getClass().getSimpleName();

    private ViewPager viewPager;
    private LinearLayout pointGroup;
    protected int lastPointPosition; // 指示点上一个位置
    private TextView confirm;
    private int resIds[] = new int[]{R.mipmap.img_mall_defult, R.mipmap.img_mall_defult, R.mipmap.img_mall_defult, R.mipmap.img_mall_defult};

    @Override
    public int getLayoutID() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_page);
        initView();
    }

    private void initView() {
        isFirst();

        viewPager =  findViewById(R.id.guide_pager);
        pointGroup = findViewById(R.id.point_guide);

        /** viewpage小圆点初始化 */
        for (int i = 0; i < resIds.length; i++) {
            ImageView point = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(13, 13);
            params.rightMargin = 15;
            params.bottomMargin = 40;
            point.setLayoutParams(params);
            point.setBackgroundResource(R.drawable.point_guide);
            if (i == 0) {// 初始化第一个点是亮的状态
                point.setEnabled(true);
            } else {
                point.setEnabled(false);
            }
            pointGroup.addView(point);
        }
        viewPager.setAdapter(new GuideAdapter(this, resIds));
        viewPager.setOnPageChangeListener(this);
        viewPager.setCurrentItem(0);//


        confirm = (TextView) findViewById(R.id.btn_confirm);
        confirm.setOnClickListener(this);

        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) confirm.getLayoutParams();
        int width = Util.getWindowWidth(this);
        int height = Util.getWindowHeigh(this);
        if (height / width >= 1.8 && height / width <= 2.0) {
            lp.bottomMargin = 225;
        }else if (height / width > 2.0 && height / width < 2.2) {
            lp.bottomMargin = 260;
        } else {
            lp.bottomMargin = 190;
        }

        confirm.setLayoutParams(lp);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_confirm:
                SharedPreferencesUtils.put(this, "isFirst", false);
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        // 设置指示点状态
        // 把当前位置图片对应的点设置为true
        pointGroup.getChildAt(position).setEnabled(true);
        // 上一个点设置为false
        pointGroup.getChildAt(lastPointPosition).setEnabled(false);
        // 更新上一个位置的值
        lastPointPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == 2) {
            if (viewPager.getCurrentItem() == 3)
                confirm.setVisibility(View.VISIBLE);
            else
                confirm.setVisibility(View.GONE);
        }
    }
    private void isFirst(){
        if (!(Boolean) SharedPreferencesUtils.get(this, "isFirst", true)) {// 是：跳转到引导页
            startActivity(new Intent(this,MainActivity.class));
        }
    }
}