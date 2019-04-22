package com.example.applaudo.tourguideapp.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity(tableName = "places_table")
public class Place implements Parcelable {

    @ColumnInfo(name = "img_src")
    private int mImgSrc;
    @NonNull
    @PrimaryKey
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

    public int getImgSrc() {
        return mImgSrc;
    }

    public void setImgSrc(int mImgSrc) {
        this.mImgSrc = mImgSrc;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getWebsite() {
        return mWebsite;
    }

    public void setWebsite(String mWebsite) {
        this.mWebsite = mWebsite;
    }

    public String getTel() {
        return mTel;
    }

    public void setTel(String mTel) {
        this.mTel = mTel;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public String getLatitude() {
        return mLatitude;
    }

    public void setLatitude(String mLatitude) {
        this.mLatitude = mLatitude;
    }

    public String getLongitude() {
        return mLongitude;
    }

    public void setLongitude(String mLongitude) {
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
