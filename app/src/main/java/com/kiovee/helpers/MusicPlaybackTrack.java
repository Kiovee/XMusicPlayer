package com.kiovee.helpers;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;


public class MusicPlaybackTrack implements Parcelable {

    //本地播放音乐字段
    public long id;
    public int sourcePosition;


    //网络播放音乐字段
    public String money;
    public String createTime;
    public String nickName;
    public long resType;
    public String isCollect;
    public String mobile;
    public String logo;
    public String title;
    public String descn;
    public String userId;
    public List<String> tag;
    public String path;
    public String lycUrl;
    public String lycContent;
    public boolean isLocal;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeInt(this.sourcePosition);
        dest.writeString(this.money);
        dest.writeString(this.createTime);
        dest.writeString(this.nickName);
        dest.writeLong(this.resType);
        dest.writeString(this.isCollect);
        dest.writeString(this.mobile);
        dest.writeString(this.logo);
        dest.writeString(this.title);
        dest.writeString(this.descn);
        dest.writeString(this.userId);
        dest.writeStringList(this.tag);
        dest.writeString(this.path);
    }

    public MusicPlaybackTrack() {
    }

    public MusicPlaybackTrack(long id,int sourcePosition) {
        this.id = id;
        this.sourcePosition = sourcePosition;
    }

    protected MusicPlaybackTrack(Parcel in) {
        this.id = in.readLong();
        this.sourcePosition = in.readInt();
        this.money = in.readString();
        this.createTime = in.readString();
        this.nickName = in.readString();
        this.resType = in.readLong();
        this.isCollect = in.readString();
        this.mobile = in.readString();
        this.logo = in.readString();
        this.title = in.readString();
        this.descn = in.readString();
        this.userId = in.readString();
        this.tag = in.createStringArrayList();
        this.path = in.readString();
    }

    public static final Creator<MusicPlaybackTrack> CREATOR = new Creator<MusicPlaybackTrack>() {
        @Override
        public MusicPlaybackTrack createFromParcel(Parcel source) {
            return new MusicPlaybackTrack(source);
        }

        @Override
        public MusicPlaybackTrack[] newArray(int size) {
            return new MusicPlaybackTrack[size];
        }
    };

    @Override
    public String toString() {
        return "MusicPlaybackTrack{" +
                "mId=" + id +
                ", mSourcePosition=" + sourcePosition +
                ", money='" + money + '\'' +
                ", createTime='" + createTime + '\'' +
                ", nickName='" + nickName + '\'' +
                ", resType='" + resType + '\'' +
                ", isCollect='" + isCollect + '\'' +
                ", mobile='" + mobile + '\'' +
                ", logo='" + logo + '\'' +
                ", title='" + title + '\'' +
                ", descn='" + descn + '\'' +
                ", userId='" + userId + '\'' +
                ", tag=" + tag +
                ", path='" + path + '\'' +
                '}';
    }
}
