package com.thinker.vdongthinker.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.adapter.CommunityAssessListViewAdapter;
import com.thinker.vdongthinker.adapter.CommunityPhotoGridViewAdapter;
import com.thinker.vdongthinker.adapter.CourseAssessListViewAdapter;
import com.thinker.vdongthinker.adapter.CoursePhotoGridViewAdapter;
import com.thinker.vdongthinker.base.BasePresenterActivity;
import com.thinker.vdongthinker.base.Constants;
import com.thinker.vdongthinker.bean.CommunityBean;
import com.thinker.vdongthinker.bean.CourseAssessBean;
import com.thinker.vdongthinker.customControl.MeasureGridView;
import com.thinker.vdongthinker.customControl.MeasureListView;
import com.thinker.vdongthinker.presenter.CommunityDetailPresenter;
import com.thinker.vdongthinker.tool.Util;
import com.thinker.vdongthinker.view.CommunityDetailView;
import com.xiao.nicevideoplayer.NiceVideoPlayer;
import com.xiao.nicevideoplayer.NiceVideoPlayerManager;
import com.xiao.nicevideoplayer.TxVideoPlayerController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zt on 2018/12/18.
 */

public class CommunityDetailActivity extends BasePresenterActivity<CommunityDetailPresenter> implements CommunityDetailView,CommunityAssessListViewAdapter.OnReplayClickListener,View.OnClickListener {
    private ImageView iv_back;
    private TextView tv_name,tv_type,tv_content,tv_assess_num,tv_assess_no;
    private EditText et_assess;
    private Button btn_assess_more;
    private MeasureGridView gv_photo;
    private MeasureListView lv_assess;
    private LinearLayout layout_send;
    private FrameLayout fl_send;
    private List<String> list_photo;
    private CommunityPhotoGridViewAdapter adapter_gv;
    private List<CourseAssessBean> list_assess;
    private CommunityAssessListViewAdapter adapter_assess;
    private InputMethodManager imm;
    private NiceVideoPlayer mPlayer;
    private CommunityBean bean;
    private int IS_VEDIO;

    @Override
    public void initData() {
        bean = (CommunityBean) getIntent().getSerializableExtra("bean");
        IS_VEDIO = bean.getIsVideo();

        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        tv_name = findViewById(R.id.tv_name);
        et_assess = findViewById(R.id.et_assess);
        et_assess.setOnClickListener(this);
        et_assess.setEnabled(false);
//        view_background = findViewById(R.id.view_background);
//        view_background.setBackgroundResource(R.color.trans);
//        view_background.setOnClickListener(this);
        tv_type = findViewById(R.id.tv_type);
        tv_content = findViewById(R.id.tv_content);
        tv_assess_num = findViewById(R.id.tv_assess_num);
        tv_assess_no = findViewById(R.id.tv_assess_no);
        btn_assess_more = findViewById(R.id.btn_assess_more);
        btn_assess_more.setOnClickListener(this);
        gv_photo = findViewById(R.id.gv_photo);
        lv_assess = findViewById(R.id.lv_assess);
        layout_send = findViewById(R.id.layout_send);
        fl_send = findViewById(R.id.fl_send);
        Util.buttonBeyondKeyboardLayout(fl_send,layout_send);
        mPlayer = findViewById(R.id.mPlayer);
        onReplay(0);
        if (IS_VEDIO == 1){
            initPlayer();
            gv_photo.setVisibility(View.GONE);
            mPlayer.setVisibility(View.VISIBLE);
        }else{
            gv_photo.setVisibility(View.VISIBLE);
            mPlayer.setVisibility(View.GONE);
            setList();
        }
        tv_name.setText(bean.getName());
        tv_type.setText(bean.getType());
        tv_content.setText(bean.getContent());
        if(bean.getAssess_num() == 0){
            lv_assess.setVisibility(View.GONE);
            btn_assess_more.setVisibility(View.GONE);
            tv_assess_no.setVisibility(View.VISIBLE);
        }else{
            lv_assess.setVisibility(View.VISIBLE);
            btn_assess_more.setVisibility(View.VISIBLE);
            tv_assess_no.setVisibility(View.GONE);
        }

    }

    private void initPlayer() {
        mPlayer.setVisibility(View.VISIBLE);
        gv_photo.setVisibility(View.GONE);
        mPlayer.setPlayerType(NiceVideoPlayer.TYPE_IJK);  //NiceVideoPlayer.IJKPlayer    or   NiceVideoPlayer.TYPE_NATIVE
        mPlayer.setUp(Constants.TEXT_VEDIO_URL,null);  //设置播放地址
        TxVideoPlayerController controller = new TxVideoPlayerController(this);
        controller.setTitle("测试小窗播放视频标题");  // controller为蒙版层，用于设置视频标题
//        controller.setLenght(98000);  //时间以mm为单位计算
//        Glide.with(this).load(Const.testUrl.VIDEOIMG1)
//                .placeholder(R.color.trans_70black)
//                .crossFade()
//                .into(controller.imageView()); //加载视频图片到蒙版上
        mPlayer.setController(controller);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 在onStop时释放掉播放器
        NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
    }
    @Override
    public void onBackPressed() {
        // 在全屏或者小窗口时按返回键要先退出全屏或小窗口，
        // 所以在Activity中onBackPress要交给NiceVideoPlayer先处理。
        if (NiceVideoPlayerManager.instance().onBackPressd()) return;
        super.onBackPressed();
    }
    private void setList() {

        mPlayer.setVisibility(View.VISIBLE);
        gv_photo.setVisibility(View.GONE);

        list_photo = bean.getImgs();
        List<String> list_show = new ArrayList<>();
        if(list_photo.size()>3){
            for (int i = 0;i<3;i++){
                list_show.add(list_photo.get(i));
            }
        }else{
            list_show = list_photo;
        }

        adapter_gv = new CommunityPhotoGridViewAdapter(this,list_photo.size());
        adapter_gv.setItems(list_show);
        gv_photo.setAdapter(adapter_gv);
        gv_photo.setOverScrollMode(View.OVER_SCROLL_NEVER);
        gv_photo.setVisibility(View.VISIBLE);
        gv_photo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CommunityDetailActivity.this,CommunityPhotoActivity.class);
                intent.putExtra("bean",bean);
                intent.putExtra("current",position+1);
                startActivity(intent);
            }
        });

        list_assess = new ArrayList<>();
        for (int i = 0;i<10;i++){
            list_assess.add(new CourseAssessBean("","小仙女啊","2019-01-01","声明：本文内容由互联网用户自发贡献自行上传，本网站不拥有所有权，未作人工编辑处理，也不承担相关法律责任。"));
        }
        adapter_assess = new CommunityAssessListViewAdapter(this);
        adapter_assess.setItems(list_assess);
        adapter_assess.setOnReplayClickListener(this);
        lv_assess.setAdapter(adapter_assess);
        lv_assess.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    @Override
    public void initPresenter() {
        mPresenter = new CommunityDetailPresenter();
        mPresenter.init(this,this,this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_community_detail;
    }



    @Override
    public void onReplay(int position) {
        //评论列表中点击回复，调出软键盘
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

//        view_background.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.et_assess:
                Toast.makeText(this,"该功能暂未开放...",Toast.LENGTH_SHORT);
                break;

        }
    }
}
