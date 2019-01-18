package com.thinker.vdongthinker.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

public abstract class BasePresenterActivity<T extends BasePresenter> extends BaseActivity {

    public T mPresenter ;
    /*初始化mPresenter*/
    public abstract void initPresenter();
    public Gson gson = new Gson();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
    }
}