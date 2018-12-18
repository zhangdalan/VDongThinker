package com.thinker.vdongthinker.base;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

public class BaseFragmentPresenter<V extends IView> implements IFragmentPresenter {
    public FragmentActivity mActivity ;
    public Context mContext ;
    public V mFgView ;
    public void init(FragmentActivity mActivity, Context mContext, V fgView) {
        this.mActivity = mActivity ;
        this.mContext = mContext ;
        this.mFgView = fgView ;

        mFgView.initData();
    }
}
