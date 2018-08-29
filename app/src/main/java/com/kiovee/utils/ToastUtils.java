package com.kiovee.utils;

import android.annotation.SuppressLint;
import android.view.Gravity;
import android.widget.Toast;

import com.kiovee.XMPApplication;

public class ToastUtils {

    private static Toast sToast;

    @SuppressLint("ShowToast")
    public static void show(String message){
        if(sToast == null){
            sToast = Toast.makeText(XMPApplication.getInstance().getApplicationContext(),"", Toast.LENGTH_SHORT);
        }
        sToast.setGravity(Gravity.CENTER,0,0);
        sToast.setText(message + "");
        sToast.show();
    }

}
