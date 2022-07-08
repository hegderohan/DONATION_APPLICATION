package com.example.donation1;

import com.google.firebase.database.Exclude;

public class Upload {
    private String mName;
    private String mImageUrl;
    private String mKey;
    public Upload() {
        //empty constructor
    }

    public Upload(String name, String imageUrl) {
        if (name.trim().equals("")) {
            name = "No Description";
        }

        mName = name;
        mImageUrl = imageUrl;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    @Exclude
    public String getKey()
    {
        return mKey;
    }

    @Exclude
    public void setmKey(String key)
    {
        mKey=key;
    }

}
