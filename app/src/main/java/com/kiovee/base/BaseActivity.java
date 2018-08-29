package com.kiovee.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    protected static final String TAG = "TAG";

    protected Activity mActivity;
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());

        mActivity = this;
        mContext = BaseActivity.this;

        //初始化前置数据
        initPreData();
        //初始化控件
        initView();
        //初始化后置数据
        initPosData();
        //初始化监听器
        initListener();

    }


    protected abstract int setLayoutId();

    protected abstract void initPreData();

    protected abstract void initView();

    protected abstract void initPosData();

    protected abstract void initListener();

}
