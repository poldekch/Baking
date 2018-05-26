package com.example.leopo.baking.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.example.leopo.baking.data.Step;

import java.util.ArrayList;

public class RecipeDetailsFragment extends Fragment{

    public RecipeDetailsFragment() {}


    // TODO review
    public static RecipeDetailsFragment newInstance(ArrayList<Step> steps, int position) {

        RecipeDetailsFragment recipeDetailsFragment = new RecipeDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("Steps", steps);
        bundle.putInt("Position", position);

        recipeDetailsFragment.setArguments(bundle);

        return recipeDetailsFragment;
    }


}
