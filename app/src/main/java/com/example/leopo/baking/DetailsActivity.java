package com.example.leopo.baking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.leopo.baking.data.Step;
import com.example.leopo.baking.ui.RecipeDetailsFragment;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    private ArrayList<Step> mSteps;
    private int stepPos;
    // TODO rename
    private boolean fragmentCreated;

    private RecipeDetailsFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        if (savedInstanceState != null) {
            fragmentCreated = savedInstanceState.getBoolean("fragment_created");
        }
        if (!fragmentCreated) {
            Bundle bundle = getIntent().getExtras();
            RecipeDetailsFragment recipeDetailsFragment = new RecipeDetailsFragment();
            recipeDetailsFragment.setArguments(bundle);
            fragmentCreated = true;
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.placeholder_fragment_recipe_details2, recipeDetailsFragment)
                    .commit();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // TODO not sure if we need it - fragment created
        outState.putBoolean("fragment_created", fragmentCreated);
    }
}
