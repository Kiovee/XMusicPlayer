package com.kiovee;

import android.support.multidex.MultiDexApplication;

import com.kiovee.weex.ImageAdapter;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;

public class XMPApplication extends MultiDexApplication {

    private static XMPApplication sApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
        initWeexSdk();
    }


    private void initWeexSdk() {
        InitConfig config=new InitConfig.Builder().setImgAdapter(new ImageAdapter()).build();
        WXSDKEngine.initialize(this,config);

        try {
            WXSDKEngine.registerModule("MyyulePlayer", MyyulePlayer.class);
        } catch (WXException e) {
            e.printStackTrace();
        }
    }

    public static XMPApplication getInstance(){
        return sApplication;
    }
}
