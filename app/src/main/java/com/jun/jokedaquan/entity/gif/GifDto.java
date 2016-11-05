package com.jun.jokedaquan.entity.gif;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * GifDto
 * Created by Tse on 2016/11/5.
 */

public class GifDto {

    public int allPages;
    public int ret_code;
    public int currentPage;
    public int allNum;
    public int maxResult;

    public List<ContentlistBean> contentlist;

    public static class ContentlistBean implements Parcelable {
        public String id;
        public String title;
        public String img;
        public int type;
        public String ct;
        public boolean isPlay;

        protected ContentlistBean(Parcel in) {
            id = in.readString();
            title = in.readString();
            img = in.readString();
            type = in.readInt();
            ct = in.readString();
            isPlay = in.readByte() != 0;
        }

        public static final Creator<ContentlistBean> CREATOR = new Creator<ContentlistBean>() {
            @Override
            public ContentlistBean createFromParcel(Parcel in) {
                return new ContentlistBean(in);
            }

            @Override
            public ContentlistBean[] newArray(int size) {
                return new ContentlistBean[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(id);
            parcel.writeString(title);
            parcel.writeString(img);
            parcel.writeInt(type);
            parcel.writeString(ct);
            parcel.writeByte((byte) (isPlay ? 1 : 0));
        }
    }
}
