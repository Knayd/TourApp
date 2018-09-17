package com.example.applaudo.tourguideapp;

public class Place {
    private int mImgSrc;
    private String mName;
    private String mDescription;
    private String mWebsite;
    private String mTel;

    Place(int mImgSrc, String mName, String mDescription, String mWebsite, String mTel) {
        this.mImgSrc = mImgSrc;
        this.mName = mName;
        this.mDescription = mDescription;
        this.mWebsite = mWebsite;
        this.mTel = mTel;
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
}
