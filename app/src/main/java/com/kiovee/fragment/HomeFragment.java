package com.kiovee.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.kiovee.R;
import com.kiovee.base.BaseViewPagerFragment;
import com.kiovee.utils.Logger;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.RenderContainer;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;

import java.util.HashMap;

public class HomeFragment extends BaseViewPagerFragment implements IWXRenderListener {

    private String mHomeUrl = "";
    private FrameLayout mWeexHomeContainer;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        mWeexHomeContainer = mRootView.findViewById(R.id.weex_Home_Container);

        RenderContainer renderContainer = new RenderContainer(getContext());
        mWeexHomeContainer.addView(renderContainer);


        mWXSDKInstance.setRenderContainer(renderContainer);
        mWXSDKInstance.registerRenderListener(this);
        mWXSDKInstance.setTrackComponent(true);
        HashMap<String, Object> options = new HashMap<>();
        options.put(WXSDKInstance.BUNDLE_URL, mHomeUrl);

        mWXSDKInstance.renderByUrl("HomeFragment", mHomeUrl, options, null, WXRenderStrategy.APPEND_ASYNC);

    }

    @Override
    protected void initPreData() {
    }

    @Override
    protected void initPosData() {
    }

    @Override
    protected void initListener() {
    }

    @Override
    public void onViewCreated(WXSDKInstance instance, View view) {
        if(view.getParent() != null){
            ((ViewGroup)view.getParent()).removeAllViews();
        }
        if (mWeexHomeContainer != null){
            mWeexHomeContainer.removeAllViews();
            mWeexHomeContainer.addView(view);
        }
    }

    @Override
    public void onRenderSuccess(WXSDKInstance instance, int width, int height) {

    }

    @Override
    public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {

    }

    @Override
    public void onException(WXSDKInstance instance, String errCode, String msg) {
        Logger.e("HomeFragment ==== 异常码：" + errCode + "\r\n" + "异常信息：" + msg);

    }
}
