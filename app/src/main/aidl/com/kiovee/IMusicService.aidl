package com.kiovee;

import com.kiovee.helpers.MusicPlaybackTrack;

interface IMusicService {
    void openFile(String path);
    void open(in List<MusicPlaybackTrack> list, int position);
    void stop();
    void pause();
    void play();
    void prev(boolean forcePrevious);
    void next();
    void enqueue(in List<MusicPlaybackTrack> list, int action);
    void setQueuePosition(int index);
    void setShuffleMode(int shufflemode);
    void setRepeatMode(int repeatmode);
    void moveQueueItem(int from, int to);
    void refresh();
    void playlistChanged();
    boolean isPlaying();
    List<MusicPlaybackTrack> getPlayList();
    long [] getQueue();
    long getQueueItemAtPosition(int position);
    int getQueueSize();
    int getQueuePosition();
    int getQueueHistoryPosition(int position);
    int getQueueHistorySize();
    int[] getQueueHistoryList();
    long duration();
    long position();
    long seek(long pos);
    void seekRelative(long deltaInMs);
    long getAudioId();
    MusicPlaybackTrack getCurrentTrack();
    MusicPlaybackTrack getTrack(int index);
    long getNextAudioId();
    long getPreviousAudioId();
    long getArtistId();
    long getAlbumId();
    String getArtistName();
    String getTrackName();
    String getAlbumName();
    String getPath();
    int getShuffleMode();
    int removeTracks(int first, int last);
    int removeTrack(long id);
    boolean removeTrackAtPosition(long id, int position);
    int getRepeatMode();
    int getMediaMountedCount();
    int getAudioSessionId();
}

