package com.thinker.vdongthinker.ui.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.adapter.MessageZanAdapter;
import com.thinker.vdongthinker.base.BasePresenterActivity;
import com.thinker.vdongthinker.bean.MessageZanBean;
import com.thinker.vdongthinker.presenter.MessageAssessPresenter;
import com.thinker.vdongthinker.presenter.MessageZanPresenter;
import com.thinker.vdongthinker.view.MessageAssessView;
import com.thinker.vdongthinker.view.MessageZanView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjw on 2018/12/28.
 */
public class MessageAssessActivity extends BasePresenterActivity<MessageAssessPresenter> implements MessageAssessView,MessageZanAdapter.OnMessageZanClickListener{
    private TextView tv_title;
    private ImageView iv_back;
    private RecyclerView rv_content;
    private MessageZanAdapter adapter;
    private List<MessageZanBean> list_zan;
    @Override
    public void initPresenter() {
        mPresenter = new MessageAssessPresenter();
        mPresenter.init(this,this,this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_message_recycler;
    }

    @Override
    public void initData() {
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText("评论");
        iv_back = findViewById(R.id.iv_back);
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rv_content = findViewById(R.id.rv_content);
        setList();
    }

    private void setList() {
        list_zan = new ArrayList<>();
        for (int i = 0; i<10 ;i ++){
            if(i == 4 || i == 6){
                list_zan.add(new MessageZanBean(1));
            }
            list_zan.add(new MessageZanBean(0));
        }
        adapter = new MessageZanAdapter(this,rv_content);
        adapter.setItems(list_zan);
        adapter.setOnMessageZanClickListener(this);
        rv_content.setAdapter(adapter);
    }

    @Override
    public void onCloseClick(int position) {

    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
