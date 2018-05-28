package com.example.leopo.baking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.leopo.baking.ui.RecipeDetailsFragment;

public class DetailsActivity extends AppCompatActivity {

    private boolean mFragmentCreated;

    private RecipeDetailsFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        if (savedInstanceState != null) {
            mFragmentCreated = savedInstanceState.getBoolean("fragment_created");
        }
        if (!mFragmentCreated) {
            Bundle bundle = getIntent().getExtras();
            RecipeDetailsFragment recipeDetailsFragment = new RecipeDetailsFragment();
            recipeDetailsFragment.setArguments(bundle);
            mFragmentCreated = true;
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.placeholder_fragment_recipe_phone, recipeDetailsFragment)
                    .commit();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("fragment_created", mFragmentCreated);
    }
}
