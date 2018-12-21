package com.thinker.vdongthinker.ui.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.base.BasePresenterActivity;
import com.thinker.vdongthinker.presenter.SearchPresenter;
import com.thinker.vdongthinker.view.SearchView;

public class SearchActivity extends BasePresenterActivity<SearchPresenter> implements SearchView ,View.OnClickListener {
    private ImageView iv_back,iv_search;
    private TextView tv_cancle,tv_search_no;
    private EditText et_search;
    private RecyclerView rv_topic_hot,rv_topic_join,rv_topic;
    private LinearLayout ll_search,ll_join;
    @Override
    public void initPresenter() {
        iv_back = findViewById(R.id.iv_back);
        iv_search = findViewById(R.id.iv_search);
        tv_cancle = findViewById(R.id.tv_cancle);
        et_search = findViewById(R.id.et_search);
        rv_topic_hot = findViewById(R.id.rv_topic_hot);
        rv_topic_join = findViewById(R.id.rv_topic_join);
        tv_search_no = findViewById(R.id.tv_search_no);
        rv_topic = findViewById(R.id.rv_topic);
        ll_search = findViewById(R.id.ll_search);
        ll_join = findViewById(R.id.ll_join);
        tv_search_no.setVisibility(View.GONE);
        iv_back.setOnClickListener(this);
        iv_search.setOnClickListener(this);
        tv_cancle.setOnClickListener(this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_search;
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
