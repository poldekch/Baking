package com.example.leopo.baking.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Recipe implements Parcelable {

    private Integer mId;
    private String mName;
    private ArrayList<Ingredient> mIngredients;
//    private
    private Integer mServings;
    private String mImage;

    public Recipe () {
    };

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public ArrayList<Ingredient> getIngredients() {
        return mIngredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        mIngredients = ingredients;
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
        dest.writeString(mName);
        dest.writeSerializable(mIngredients);
        // TODO add missing fields
        dest.writeInt(mServings);
        dest.writeString(mImage);
    }

    Recipe(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        mIngredients = (ArrayList<Ingredient>) in.readSerializable();
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
