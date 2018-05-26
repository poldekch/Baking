package com.example.leopo.baking.ui;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.leopo.baking.R;
import com.example.leopo.baking.data.Step;
import com.google.android.exoplayer2.SimpleExoPlayer;

import java.util.ArrayList;

public class RecipeDetailsFragment extends Fragment{

    SimpleExoPlayer simpleExoPlayer;

    // TODO make sure we have good names,
    // TODO add m notation
    private String description, url, thumbnailUrl;

    public RecipeDetailsFragment() {}

    @Override
    public void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            // TODO populate all data
            description = bundle.getString("description");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_details, container, false);

        return view;
    }
}
