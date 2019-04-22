package com.example.applaudo.tourguideapp.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "places_table")
public class Place implements Parcelable {

    @ColumnInfo(name = "img_src")
    private int mImgSrc;
    @ColumnInfo(name = "name")
    private String mName;
    @ColumnInfo(name = "description")
    private String mDescription;
    @ColumnInfo(name = "website")
    private String mWebsite;
    @ColumnInfo(name = "tel")
    private String mTel;
    @ColumnInfo(name = "location")
    private String mLocation;
    @ColumnInfo(name = "lat")
    private String mLatitude;
    @ColumnInfo(name = "lon")
    private String mLongitude;

    public Place(int mImgSrc, String mName, String mLocation, String mWebsite, String mTel, String mDescription, String mLatitude, String mLongitude) {
        this.mLocation = mLocation;
        this.mImgSrc = mImgSrc;
        this.mName = mName;
        this.mDescription = mDescription;
        this.mWebsite = mWebsite;
        this.mTel = mTel;
        this.mLatitude = mLatitude;
        this.mLongitude = mLongitude;
    }

    public int getmImgSrc() {
        return mImgSrc;
    }

    public void setmImgSrc(int mImgSrc) {
        this.mImgSrc = mImgSrc;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmWebsite() {
        return mWebsite;
    }

    public void setmWebsite(String mWebsite) {
        this.mWebsite = mWebsite;
    }

    public String getmTel() {
        return mTel;
    }

    public void setmTel(String mTel) {
        this.mTel = mTel;
    }

    public String getmLocation() {
        return mLocation;
    }

    public void setmLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public String getmLatitude() {
        return mLatitude;
    }

    public void setmLatitude(String mLatitude) {
        this.mLatitude = mLatitude;
    }

    public String getmLongitude() {
        return mLongitude;
    }

    public void setmLongitude(String mLongitude) {
        this.mLongitude = mLongitude;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mImgSrc);
        dest.writeString(this.mName);
        dest.writeString(this.mDescription);
        dest.writeString(this.mWebsite);
        dest.writeString(this.mTel);
        dest.writeString(this.mLocation);
        dest.writeString(this.mLatitude);
        dest.writeString(this.mLongitude);
    }

    protected Place(Parcel in) {
        this.mImgSrc = in.readInt();
        this.mName = in.readString();
        this.mDescription = in.readString();
        this.mWebsite = in.readString();
        this.mTel = in.readString();
        this.mLocation = in.readString();
        this.mLatitude = in.readString();
        this.mLongitude = in.readString();
    }

    public static final Parcelable.Creator<Place> CREATOR = new Parcelable.Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel source) {
            return new Place(source);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };
}
