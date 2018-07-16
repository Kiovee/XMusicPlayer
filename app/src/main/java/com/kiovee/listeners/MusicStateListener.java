package com.kiovee.listeners;

public interface MusicStateListener {
    /**
     * Called when {@link com.kiovee.MusicService#REFRESH} is invoked
     */
    void restartLoader();

    /**
     * Called when {@link com.kiovee.MusicService#PLAYLIST_CHANGED} is invoked
     */
    void onPlaylistChanged();

    /**
     * Called when {@link com.kiovee.MusicService#META_CHANGED} is invoked
     */
    void onMetaChanged();
}
