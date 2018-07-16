package com.kiovee.activity;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.kiovee.MusicPlayer;
import com.kiovee.R;
import com.kiovee.base.BaseActivity;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

public class HomeActivity extends BaseActivity {
    public static final String TAG = "TAG";
    public final String path = "http://other.web.nf01.sycdn.kuwo.cn/resource/n3/77/78/2432129916.mp3";
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setEnabled(false);
                if(!button.isEnabled()){
                    RxPermissions rxPermissions = new RxPermissions(HomeActivity.this);
                    rxPermissions.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            .subscribe(new Consumer<Permission>() {
                                @Override
                                public void accept(Permission permission) throws Exception {
                                    button.setEnabled(true);
                                    if (permission.granted) {
                                        // 用户已经同意该权限
                                        Log.d(TAG, permission.name + " is granted.");
                                        Log.e("TAG","开始播放音乐111");
                                        MusicPlayer.openFile(path);
                                        MusicPlayer.playOrPause();

                                    } else if (permission.shouldShowRequestPermissionRationale) {
                                        // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                                        Log.d(TAG, permission.name + " is denied. More info should be provided.");
                                    } else {
                                        // 用户拒绝了该权限，并且选中『不再询问』
                                        Log.d(TAG, permission.name + " is denied.");
                                    }
                                }
                            });
                }

            }
        });

        /*final MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(this, Uri.parse(path));
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
