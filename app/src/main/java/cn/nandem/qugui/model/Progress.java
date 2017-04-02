package cn.nandem.qugui.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Nandem on 2017-04-02
 */
public class Progress implements Parcelable{
    private String title;
    private String content;
    private int imgRes;

    public Progress(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Progress(String title, int imgRes) {
        this.title = title;
        this.imgRes = imgRes;
    }

    protected Progress(Parcel in) {
        title = in.readString();
        content = in.readString();
        imgRes = in.readInt();
    }

    public static final Creator<Progress> CREATOR = new Creator<Progress>() {
        @Override
        public Progress createFromParcel(Parcel in) {
            return new Progress(in);
        }

        @Override
        public Progress[] newArray(int size) {
            return new Progress[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(content);
        dest.writeInt(imgRes);
    }
}
