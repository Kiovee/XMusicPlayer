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

public class IssueFragment extends BaseViewPagerFragment implements IWXRenderListener {

    private static final String mIssueUrl = "";
    private FrameLayout mWeexIssueContainer;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_issue;
    }

    @Override
    protected void initView() {
        mWeexIssueContainer = mRootView.findViewById(R.id.weex_Issue_Container);

        RenderContainer renderContainer = new RenderContainer(getContext());
        mWeexIssueContainer.addView(renderContainer);


        mWXSDKInstance.setRenderContainer(renderContainer);
        mWXSDKInstance.registerRenderListener(this);
        mWXSDKInstance.setTrackComponent(true);
        HashMap<String, Object> options = new HashMap<>();
        options.put(WXSDKInstance.BUNDLE_URL, mIssueUrl);

        mWXSDKInstance.renderByUrl("HomeFragment", mIssueUrl, options, null, WXRenderStrategy.APPEND_ASYNC);
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
    protected void onFragmentVisibleChange(boolean isVisible) {
    }


    @Override
    public void onViewCreated(WXSDKInstance instance, View view) {
        if(view.getParent() != null){
            ((ViewGroup)view.getParent()).removeAllViews();
        }
        if (mWeexIssueContainer != null){
            mWeexIssueContainer.removeAllViews();
            mWeexIssueContainer.addView(view);
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
        Logger.e("IssueFragment ==== 异常码：" + errCode + "\r\n" + "异常信息：" + msg);
    }
}
