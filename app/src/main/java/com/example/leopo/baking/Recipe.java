package com.example.leopo.baking;

import android.os.Parcel;
import android.os.Parcelable;

public class Recipe implements Parcelable {

    private Integer mId;
//    private
    private Integer mServings;
    private String mImage;

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    // TODO implement fields

    public Integer getServings() {
        return mServings;
    }

    public void setServings(Integer servings) {
        mServings = servings;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        // TODO add missing fields
        dest.writeInt(mServings);
        dest.writeString(mImage);
    }

    Recipe(Parcel in) {
        mId = in.readInt();
        // TODO add missing fields
        mServings = in.readInt();
        mImage = in.readString();
    }

    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
}
