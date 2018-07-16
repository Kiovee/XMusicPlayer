package com.kiovee.base;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.kiovee.IRemoteMusicService;
import com.kiovee.MusicPlayer;
import com.kiovee.MusicService;
import com.kiovee.R;
import com.kiovee.listeners.MusicStateListener;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class BaseActivity extends AppCompatActivity implements ServiceConnection,MusicStateListener{

    private final ArrayList<MusicStateListener> mMusicStateListener = new ArrayList<>();
    private IRemoteMusicService mService;
    private MusicPlayer.ServiceToken mServiceToken;
    private PlaybackStatus mPlaybackStatus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mServiceToken = MusicPlayer.bindToService(this, this);

        mPlaybackStatus = new PlaybackStatus(this);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);

    }

    @Override
    protected void onStart() {
        super.onStart();

        final IntentFilter filter = new IntentFilter();
        // Play and pause changes
        filter.addAction(MusicService.PLAYSTATE_CHANGED);
        // Track changes
        filter.addAction(MusicService.META_CHANGED);
        // Update a list, probably the playlist fragment's
        filter.addAction(MusicService.REFRESH);
        // If a playlist has changed, notify us
        filter.addAction(MusicService.PLAYLIST_CHANGED);
        // If there is an error playing a track
        filter.addAction(MusicService.TRACK_ERROR);

        registerReceiver(mPlaybackStatus, filter);
    }

    @Override
    protected void onResume() {
        //For Android 8.0+: service may get destroyed if in background too long
        if(mService == null){
            mServiceToken = MusicPlayer.bindToService(this, this);
        }
        onMetaChanged();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(mServiceToken != null){
            MusicPlayer.unbindFromService(mServiceToken);
            mServiceToken = null;
        }

        unregisterReceiver(mPlaybackStatus);

        mMusicStateListener.clear();
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        mService = IRemoteMusicService.Stub.asInterface(service);
        onMetaChanged();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        mService = null;
    }

    @Override
    public void restartLoader() {
        // Let the listener know to update a list
        for (final MusicStateListener listener : mMusicStateListener) {
            if (listener != null) {
                listener.restartLoader();
            }
        }
    }

    @Override
    public void onPlaylistChanged() {
        // Let the listener know to update a list
        for (final MusicStateListener listener : mMusicStateListener) {
            if (listener != null) {
                listener.onPlaylistChanged();
            }
        }
    }

    @Override
    public void onMetaChanged() {
        // Let the listener know to the meta chnaged
        for (final MusicStateListener listener : mMusicStateListener) {
            if (listener != null) {
                listener.onMetaChanged();
            }
        }
    }

    public void setMusicStateListenerListener(final MusicStateListener status) {
        if (status == this) {
            throw new UnsupportedOperationException("Override the method, don't add a listener");
        }

        if (status != null) {
            mMusicStateListener.add(status);
        }
    }

    public void removeMusicStateListenerListener(final MusicStateListener status) {
        if (status != null) {
            mMusicStateListener.remove(status);
        }
    }

    private static final class PlaybackStatus extends BroadcastReceiver{

        private final WeakReference<BaseActivity> mReference;

        public PlaybackStatus(final BaseActivity activity){
            mReference = new WeakReference<>(activity);
        }

        @Override
        public void onReceive(final Context context, final Intent intent) {
           final String action = intent.getAction();
            BaseActivity baseActivity = mReference.get();
            if(baseActivity != null){
                if (action.equals(MusicService.META_CHANGED)) {
                    baseActivity.onMetaChanged();
                } else if (action.equals(MusicService.PLAYSTATE_CHANGED)) {
                    //                    baseActivity.mPlayPauseProgressButton.getPlayPauseButton().updateState();
                } else if (action.equals(MusicService.REFRESH)) {
                    baseActivity.restartLoader();
                } else if (action.equals(MusicService.PLAYLIST_CHANGED)) {
                    baseActivity.onPlaylistChanged();
                } else if (action.equals(MusicService.TRACK_ERROR)) {
                    final String errorMsg = context.getString(R.string.error_playing_track,
                            intent.getStringExtra(MusicService.TrackErrorExtra.TRACK_NAME));
                    Toast.makeText(baseActivity, errorMsg, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
