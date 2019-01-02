package com.thinker.vdongthinker.ui.fragment;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.adapter.AgencyRecyclerAdapter;
import com.thinker.vdongthinker.adapter.BaseAdapterRecycler;
import com.thinker.vdongthinker.adapter.IndexRecyclerAdapter;
import com.thinker.vdongthinker.base.BasePresenterFragment;
import com.thinker.vdongthinker.base.Constants;
import com.thinker.vdongthinker.bean.AgencyMallRecyclerBean;
import com.thinker.vdongthinker.bean.IndexMallRecyclerBean;
import com.thinker.vdongthinker.customControl.AutoTextView;
import com.thinker.vdongthinker.customControl.MyScrollView;
import com.thinker.vdongthinker.customControl.ObservableScrollView;
import com.thinker.vdongthinker.presenter.IndexPresenter;
import com.thinker.vdongthinker.tool.GlideImageLoader;
import com.thinker.vdongthinker.tool.Util;
import com.thinker.vdongthinker.ui.activity.AgencyDetailActivity;
import com.thinker.vdongthinker.ui.activity.AgencyTypeActivity;
import com.thinker.vdongthinker.ui.activity.CourseDetailActivity;
import com.thinker.vdongthinker.ui.activity.CourseTypeActivity;
import com.thinker.vdongthinker.ui.activity.PersonalInfoActivity;
import com.thinker.vdongthinker.ui.activity.SearchActivity;
import com.thinker.vdongthinker.view.IndexView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zt on 2018/12/10.
 */

public class FragmentIndex extends BasePresenterFragment<IndexPresenter> implements IndexView,OnBannerListener ,ObservableScrollView.ScrollViewListener,View.OnClickListener{

    private ObservableScrollView sv_index;
    private LinearLayout layout_search,ll_content;
    private View view_background;
    private RecyclerView rv_course,rv_agency,rv_hot_mall;
    private IndexRecyclerAdapter adapter_course,adapter_hot_mall;
    private AgencyRecyclerAdapter adapter_agency;
    private List<IndexMallRecyclerBean> list_course,list_hot_mall;
    private List<AgencyMallRecyclerBean> list_agency;
    private Banner banner_index,banner_course,banner_agency,banner_hot_mall;
    private List<String> imgs;
    private AutoTextView tv_news;
    private List<String> list_news;
    private TextView tv_course_more,tv_agency_more,tv_mall_more;
    private View fake_statusbar_view;
    private ImageView iv_touxiang;

    @Override
    public void initData() {
        fake_statusbar_view = contentView.findViewById(R.id.fake_statusbar_view);
        sv_index = contentView.findViewById(R.id.sv_index);
        sv_index.setScrollViewListener(this);
        layout_search = contentView.findViewById(R.id.layout_search);
        layout_search.setOnClickListener(this);
        ll_content = contentView.findViewById(R.id.ll_content);
        view_background = contentView.findViewById(R.id.view_background);
        view_background.setAlpha(0.0f);
        rv_course = contentView.findViewById(R.id.rv_course);
        rv_agency = contentView.findViewById(R.id.rv_agency);
        rv_hot_mall = contentView.findViewById(R.id.rv_hot_mall);
        banner_index = contentView.findViewById(R.id.banner_index);
        banner_course = contentView.findViewById(R.id.banner_course);
        banner_agency = contentView.findViewById(R.id.banner_agency);
        banner_hot_mall = contentView.findViewById(R.id.banner_hot_mall);
        tv_news = contentView.findViewById(R.id.tv_news);
        tv_course_more = contentView.findViewById(R.id.tv_course_more);
        tv_agency_more = contentView.findViewById(R.id.tv_agency_more);
        tv_mall_more = contentView.findViewById(R.id.tv_mall_more);
        iv_touxiang = contentView.findViewById(R.id.iv_touxiang);
        tv_course_more.setOnClickListener(this);
        tv_agency_more.setOnClickListener(this);
        tv_mall_more.setOnClickListener(this);
        iv_touxiang.setOnClickListener(this);
        setRecycler();

        setBannerDate();
        setNewsDate();

        sv_index.scrollTo(0,ll_content.getTop());
    }

    private void setRecycler() {
        list_course = new ArrayList<>();
        list_course.add(new IndexMallRecyclerBean("商品标题","1000","1111"));
        list_course.add(new IndexMallRecyclerBean("商品标题","1000","1111"));
        list_course.add(new IndexMallRecyclerBean("商品标题","1000","1111"));
        list_course.add(new IndexMallRecyclerBean("商品标题","1000","1111"));
        list_agency = new ArrayList<>();
        list_agency.add(new AgencyMallRecyclerBean("商品标题","管城区","1111"));
        list_agency.add(new AgencyMallRecyclerBean("商品标题","管城区","1111"));
        list_agency.add(new AgencyMallRecyclerBean("商品标题","管城区","1111"));
        list_agency.add(new AgencyMallRecyclerBean("商品标题","管城区","1111"));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getContext(),2);
        rv_course.setLayoutManager(gridLayoutManager);
        rv_agency.setLayoutManager(gridLayoutManager1);
        adapter_course = new IndexRecyclerAdapter(getContext(),rv_course);
        adapter_course.setItems(list_course);
        rv_course.setAdapter(adapter_course);
        adapter_agency = new AgencyRecyclerAdapter(getContext(),rv_agency);
        adapter_agency.setItems(list_agency);
        rv_agency.setAdapter(adapter_agency);
//        list_hot_mall = new ArrayList<>();
//        list_hot_mall.add(new IndexMallRecyclerBean("商品标题","1000","1111"));
//        list_hot_mall.add(new IndexMallRecyclerBean("商品标题","1000","1111"));
//        list_hot_mall.add(new IndexMallRecyclerBean("商品标题","1000","1111"));
//        list_hot_mall.add(new IndexMallRecyclerBean("商品标题","1000","1111"));
//        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getContext(),2);
//        rv_hot_mall.setLayoutManager(gridLayoutManager2);
//        adapter_hot_mall = new IndexRecyclerAdapter(getContext(),rv_hot_mall);
//        adapter_hot_mall.setItems(list_course);
//        rv_hot_mall.setAdapter(adapter_hot_mall);

        adapter_course.setOnRecyclerViewItemClickListener(new BaseAdapterRecycler.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //跳转商品详情
                Intent intent = new Intent(getActivity(),CourseDetailActivity.class);
                intent.putExtra("","");
                startActivity(intent);
            }
        });
        adapter_agency.setOnRecyclerViewItemClickListener(new BaseAdapterRecycler.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//跳转机构详情
                Intent intent = new Intent(getActivity(),AgencyDetailActivity.class);
                intent.putExtra("","");
                startActivity(intent);
            }
        });
//        adapter_hot_mall.setOnRecyclerViewItemClickListener(new BaseAdapterRecycler.OnRecyclerViewItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                //跳转商品详情
//                Intent intent = new Intent(getActivity(),CourseDetailActivity.class);
//                intent.putExtra("","");
//                startActivity(intent);
//            }
//        });
    }

    @Override
    public void initPresenter() {
        mPresenter = new IndexPresenter();
        mPresenter.init(getActivity() ,getContext() ,this);
    }

    @Override
    public int getLauoutID() {
        return R.layout.fragment_index;
    }

    @Override
    public void OnBannerClick(int position) {

    }
    private void setBannerDate(){
        imgs = new ArrayList<>();
        imgs.add("");
        imgs.add("");
        imgs.add("");
        /*banner*/
        banner_index.setIndicatorGravity(BannerConfig.CENTER);
        banner_course.setIndicatorGravity(BannerConfig.CENTER);
        banner_agency.setIndicatorGravity(BannerConfig.CENTER);
//        banner_hot_mall.setIndicatorGravity(BannerConfig.CENTER);
        banner_index.setOnBannerListener(this);
        banner_course.setOnBannerListener(this);
        banner_agency.setOnBannerListener(this);
//        banner_hot_mall.setOnBannerListener(this);
        banner_index.setImages(imgs).setImageLoader(new GlideImageLoader(false)).start() ;
        banner_course.setImages(imgs).setImageLoader(new GlideImageLoader(true)).start() ;
        banner_agency.setImages(imgs).setImageLoader(new GlideImageLoader(true)).start() ;
//        banner_hot_mall.setImages(imgs).setImageLoader(new GlideImageLoader(true)).start() ;
    }

    private void setNewsDate() {
        list_news = new ArrayList<>();
        for (int i = 0;i<4;i++){
            list_news.add("头条"+(i+1));
        }
        handler.removeMessages(1);
        tv_news.setText(list_news.get(0));
        handler.sendEmptyMessageDelayed(1, 3000);
    }

    private int noticePosition = 0;
    /**
     * 广告轮播定时
     */
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
              tv_news.next();
              tv_news.setText(list_news.get(noticePosition++ % list_news.size()));
              handler.sendEmptyMessageDelayed(1, 3000);

        }
    };

    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        int screenHeight = Util.getWindowHeigh(getActivity());
        int top = screenHeight / 4;
        if (y <= 20) {
            Constants.alpha = 0.0f;
        } else if (y > 20 && y <= top) {
            Constants.alpha = (float) y / top;
        } else {
            Constants.alpha = 1.0f;
        }
        view_background.setAlpha(Constants.alpha);

        fake_statusbar_view.setAlpha(Constants.alpha);
    }

    @Override
    public void setStatusBar() {
        super.setStatusBar();
//        StatusBarUtil.setTransparent(mPresenter.mActivity);
//        StatusBarUtil.setTranslucent(mPresenter.mActivity,0);
        StatusBarUtil.setTranslucentForImageViewInFragment(mPresenter.mActivity,0, null);
        fake_statusbar_view.setAlpha(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_course_more:
                startActivity(new Intent(mPresenter.mActivity, CourseTypeActivity.class));
                break;
            case R.id.tv_agency_more:
                startActivity(new Intent(mPresenter.mActivity, AgencyTypeActivity.class));

                break;
            case R.id.tv_mall_more:
//                startActivity(new Intent(mPresenter.mActivity, CourseTypeActivity.class));

                break;
            case R.id.layout_search:
                Intent intent = new Intent(mPresenter.mContext,SearchActivity.class);
                intent.putExtra("PAGE_TYPE",1);
                startActivity(intent);
                break;
            case R.id.iv_touxiang:
                startActivity(new Intent(mPresenter.mActivity, PersonalInfoActivity.class));
                break;
        }
    }
}
