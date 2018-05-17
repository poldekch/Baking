package com.example.leopo.baking.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.leopo.baking.R;

public class RecipeOverviewFragment extends Fragment {

    public RecipeOverviewFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            // TODO retrieve state values
        }

        View rootView = inflater.inflate(R.layout.fragment_recipe_overview, container, false);

        // TODO set listeners

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle currentState) {
        // TODO save state
    }
}
