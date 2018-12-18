package com.thinker.vdongthinker.base;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

public class BasePresenter<V extends IView> {
    public AppCompatActivity mActivity ;
    public Context mContext ;
    public V mView ;
    /*将加载的这个activity的初始化数据加载进来*/
    public void init(AppCompatActivity mActivity, Context mContext, V mView) {
        this.mActivity = mActivity ;
        this.mContext = mContext ;
        this.mView = mView ;

        mView.initData();
    }
}