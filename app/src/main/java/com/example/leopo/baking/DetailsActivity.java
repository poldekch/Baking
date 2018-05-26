package com.example.leopo.baking;

import android.support.v4.app.FragmentManager;
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
            // TODO rename key
            fragmentCreated = savedInstanceState.getBoolean("KEY");
        }
        if (!fragmentCreated) {
            Bundle bundle = getIntent().getExtras();
            RecipeDetailsFragment recipeDetailsFragment = new RecipeDetailsFragment();
            recipeDetailsFragment.setArguments(bundle);
            fragmentCreated = true;
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_recipe_details, recipeDetailsFragment)
                    .commit();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // TODO not sure if we need it - fragment created
        outState.putBoolean("KEY", fragmentCreated);
    }
}
