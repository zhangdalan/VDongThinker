package com.thinker.vdongthinker.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.adapter.BaseAdapterRecycler;
import com.thinker.vdongthinker.adapter.IndexRecyclerAdapter;
import com.thinker.vdongthinker.base.BasePresenterFragment;
import com.thinker.vdongthinker.base.Constants;
import com.thinker.vdongthinker.bean.IndexMallRecyclerBean;
import com.thinker.vdongthinker.customControl.ObservableScrollView;
import com.thinker.vdongthinker.presenter.MallPresenter;
import com.thinker.vdongthinker.tool.GlideImageLoader;
import com.thinker.vdongthinker.tool.Util;
import com.thinker.vdongthinker.ui.activity.CourseDetailActivity;
import com.thinker.vdongthinker.ui.activity.MallDetailActivity;
import com.thinker.vdongthinker.view.MallView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zt on 2018/12/14.
 */

public class FragmentMall extends BasePresenterFragment<MallPresenter> implements MallView,ObservableScrollView.ScrollViewListener ,OnBannerListener{
    private ImageView iv_touxiang;
    private LinearLayout ll_search,ll_mall_car;
    private Banner banner_mall,banner_hot_mall;
    private RecyclerView rv_hot_mall;
    private TextView tv_more;
    private ObservableScrollView sv_index;
    private View view_background;
    private ArrayList<String> imgs;

    private IndexRecyclerAdapter adapter_hot_mall;
    private List<IndexMallRecyclerBean> list_hot_mall;
    @Override
    public void initData() {
        sv_index = contentView.findViewById(R.id.sv_index);
        sv_index.setScrollViewListener(this);
        view_background = contentView.findViewById(R.id.view_background);
        view_background.setAlpha(0.0f);
        iv_touxiang = contentView.findViewById(R.id.iv_touxiang);
        ll_search = contentView.findViewById(R.id.ll_search);
        ll_mall_car = contentView.findViewById(R.id.ll_mall_car);
        banner_mall = contentView.findViewById(R.id.banner_mall);
        banner_hot_mall = contentView.findViewById(R.id.banner_hot_mall);
        rv_hot_mall = contentView.findViewById(R.id.rv_hot_mall);
        tv_more = contentView.findViewById(R.id.tv_more);

        setBannerDate();
        setRecycler();
    }

    private void setRecycler() {
        list_hot_mall = new ArrayList<>();
        list_hot_mall.add(new IndexMallRecyclerBean("商品标题","1000","1111"));
        list_hot_mall.add(new IndexMallRecyclerBean("商品标题","1000","1111"));
        list_hot_mall.add(new IndexMallRecyclerBean("商品标题","1000","1111"));
        list_hot_mall.add(new IndexMallRecyclerBean("商品标题","1000","1111"));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        rv_hot_mall.setLayoutManager(gridLayoutManager);
        adapter_hot_mall = new IndexRecyclerAdapter(getContext(),rv_hot_mall);
        adapter_hot_mall.setItems(list_hot_mall);
        rv_hot_mall.setAdapter(adapter_hot_mall);
        adapter_hot_mall.setOnRecyclerViewItemClickListener(new BaseAdapterRecycler.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //跳转商品详情
                Intent intent = new Intent(getActivity(),MallDetailActivity.class);
                intent.putExtra("","");
                startActivity(intent);
            }
        });
    }

    private void setBannerDate() {
        imgs = new ArrayList<>();
        imgs.add("");
        imgs.add("");
        imgs.add("");
        /*banner*/
        banner_hot_mall.setIndicatorGravity(BannerConfig.CENTER);
        banner_mall.setIndicatorGravity(BannerConfig.CENTER);
        banner_hot_mall.setOnBannerListener(this);
        banner_mall.setOnBannerListener(this);
        banner_hot_mall.setImages(imgs).setImageLoader(new GlideImageLoader(true)).start() ;
        banner_mall.setImages(imgs).setImageLoader(new GlideImageLoader(true)).start() ;
    }

    @Override
    public void initPresenter() {
        mPresenter = new MallPresenter();
        mPresenter.init(getActivity(),getContext(),this);
    }

    @Override
    public int getLauoutID() {
        return R.layout.fragment_mall;
    }

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
    }

    @Override
    public void OnBannerClick(int position) {

    }
}
