package com.example.leopo.baking.ui;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.leopo.baking.R;
import com.example.leopo.baking.adapters.RecipeStepsAdapter;
import com.example.leopo.baking.data.Recipe;

public class RecipeOverviewFragment extends Fragment {

    Recipe mRecipe;

    // TODO mCallback

    public RecipeOverviewFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            // TODO retrieve state values
        }

        View rootView = inflater.inflate(R.layout.fragment_recipe_overview, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.rv_recipes);

//        RecipeStepsAdapter mAdapter = new RecipeStepsAdapter(getContext(), );


        // TODO set listeners

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle currentState) {
        // TODO save state
    }

    public void setRecipeData(Recipe recipe) {
        mRecipe = recipe;
    }
}
