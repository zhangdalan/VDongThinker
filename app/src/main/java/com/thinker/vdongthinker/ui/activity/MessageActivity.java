package com.thinker.vdongthinker.ui.activity;

import android.content.Intent;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.base.BasePresenterActivity;
import com.thinker.vdongthinker.presenter.MessagePresenter;
import com.thinker.vdongthinker.view.MessageView;

/**
 * Created by zjw on 2018/12/27.
 */
public class MessageActivity extends BasePresenterActivity<MessagePresenter> implements MessageView,View.OnClickListener {
    private ImageView iv_back;
    private TextView tv_title;
    private RelativeLayout rl_zan,rl_assess,rl_replay;
    private TextView tv_zan_num,tv_assess_num,tv_replay_num;
    @Override
    public void initPresenter() {
        mPresenter = new MessagePresenter();
        mPresenter.init(this,this,this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_message;
    }

    @Override
    public void initData() {
        iv_back = findViewById(R.id.iv_back);
        iv_back.setVisibility(View.VISIBLE);
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText("消息");
        rl_zan = findViewById(R.id.rl_zan);
        rl_assess = findViewById(R.id.rl_assess);
        rl_replay = findViewById(R.id.rl_replay);
        tv_zan_num = findViewById(R.id.tv_zan_num);
        tv_assess_num = findViewById(R.id.tv_assess_num);
        tv_replay_num = findViewById(R.id.tv_replay_num);
        iv_back.setOnClickListener(this);
        rl_zan.setOnClickListener(this);
        rl_assess.setOnClickListener(this);
        rl_replay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_zan:
                startActivity(new Intent(this,MessageZanActivity.class));
                break;
            case R.id.rl_assess:
                startActivity(new Intent(this,MessageAssessActivity.class));
                break;
            case R.id.rl_replay:
                startActivity(new Intent(this,MessageReplayActivity.class));
                break;

        }
    }
}
