package com.kiovee.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends AbstractBaseFragment {

    protected View sRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sRootView = inflater.inflate(getLayoutId(),container,false);
        if(sRootView == null){
            return super.onCreateView(inflater,container,savedInstanceState);
        }else {
            initView(sRootView,savedInstanceState);
            initPreData();
            return sRootView;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPosData();
        initListener();
    }

    protected abstract int getLayoutId();

    protected abstract void initView(View rootView, Bundle savedInstanceState);

    protected abstract void initPreData();

    protected abstract void initPosData();

    protected abstract void initListener();

}
