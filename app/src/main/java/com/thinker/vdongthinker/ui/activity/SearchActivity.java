package com.thinker.vdongthinker.ui.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.base.BasePresenterActivity;
import com.thinker.vdongthinker.customControl.AutoTagView;
import com.thinker.vdongthinker.customControl.HotTagView;
import com.thinker.vdongthinker.presenter.SearchPresenter;
import com.thinker.vdongthinker.tool.StatusBarUtil;
import com.thinker.vdongthinker.view.SearchView;

public class SearchActivity extends BasePresenterActivity<SearchPresenter> implements SearchView ,View.OnClickListener {
    private ImageView iv_back,iv_search;
    private TextView tv_cancle,tv_search_no,tv_hot,tv_topic;
    private EditText et_search;
    private AutoTagView atv_topic_hot,atv_topic;
    private int PAGE_TYPE = 0;//0发布，1首页，2机构
    @Override
    public void initPresenter() {
        mPresenter = new SearchPresenter();
        mPresenter.init(this,this,this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_search;
    }

    @Override
    public void initData() {
        PAGE_TYPE = getIntent().getIntExtra("PAGE_TYPE",0);

        iv_back = findViewById(R.id.iv_back);
        iv_search = findViewById(R.id.iv_search);
        tv_cancle = findViewById(R.id.tv_cancle);
        et_search = findViewById(R.id.et_search);
        atv_topic_hot = findViewById(R.id.atv_topic_hot);
        atv_topic = findViewById(R.id.atv_topic);
        tv_search_no = findViewById(R.id.tv_search_no);
        atv_topic = findViewById(R.id.atv_topic);
        tv_hot = findViewById(R.id.tv_hot);
        tv_topic = findViewById(R.id.tv_topic);
        tv_search_no.setVisibility(View.GONE);
        iv_back.setOnClickListener(this);
        iv_search.setOnClickListener(this);
        tv_cancle.setOnClickListener(this);
        if (PAGE_TYPE == 1){
            setDateIndex();
        }else if (PAGE_TYPE == 2){
            setDateAgency();
        }else{
            setDateRealse();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    public void setStatusBar() {
        super.setStatusBar();
        //设置字体颜色为黑色
        StatusBarUtil.setImmersiveStatusBar(this,true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        //设置状态栏的颜色
//        StatusBarUtil.setStatusBarColor(this, );
    }

    /**
     * 发布搜索跳转
     */
    private void setDateRealse(){

    }

    /**
     * 首页搜索跳转
     */
    private void setDateIndex() {
        tv_hot.setText("推荐类别");
        String[] tags = new String[]{"哈喽啊", "小金毛", "哈哈", "啦啦啦", "萨瓦迪卡", "欢迎大家", "走吧"};
        for (int i = 0; i < tags.length; i++) {
            HotTagView tagView = new HotTagView(this);
            tagView.setTagText(tags[i]);
            tagView.setTextColor(getResources().getColor(R.color.text_gray));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(10, 10, 10, 10);
            tagView.setLayoutParams(params);
//            tv.setBackgroundResource(R.drawable.text_background);
            atv_topic_hot.addView(tagView);
        }

    }

    /**
     * 机构搜索跳转
     */
    private void setDateAgency(){

    }
}
