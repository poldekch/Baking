package com.example.leopo.baking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.leopo.baking.data.Ingredient;
import com.example.leopo.baking.data.Recipe;
import com.example.leopo.baking.data.Step;
import com.example.leopo.baking.ui.RecipeDetailsFragment;
import com.example.leopo.baking.ui.RecipeOverviewFragment;

import java.util.ArrayList;

public class RecipeActivity extends AppCompatActivity {

    private boolean mTwoPane;
    public ArrayList<Ingredient> mIngredients;
    public ArrayList<Step> mSteps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        if (findViewById(R.id.video_container) != null) {
            mTwoPane = true;
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.video_container, new RecipeDetailsFragment())
                    .commit();
        }


        if (savedInstanceState != null) {
            // todo check rotation
        } else {
            RecipeOverviewFragment recipeOverviewFragment = new RecipeOverviewFragment();

            Intent intent = getIntent();
            Recipe recipe = intent.getParcelableExtra("Recipe");

            mIngredients = recipe.getIngredients();
            mSteps = recipe.getSteps();



//            recipeOverviewFragment.setStepsData(recipe.getSteps());
//            recipeIngredient.setIngredientsData(recipe.getIngredients());

            // TODO clean
            // TODO add some things to bundle
            Bundle bundle = new Bundle();
//            bundle.putParcelable("RECIPE", recipe);
            bundle.putParcelableArrayList("ingredients", mIngredients);
            bundle.putParcelableArrayList("steps", mSteps);

            bundle.putBoolean("TWO_PANE", mTwoPane);


            recipeOverviewFragment.setArguments(bundle);

            recipeOverviewFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_recipe_overview, recipeOverviewFragment)
                    .commit();

            RecipeDetailsFragment recipeDetailsFragment = new RecipeDetailsFragment();
            recipeDetailsFragment.setArguments(bundle);
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.details_fragment_container, recipeDetailsFragment)
//                    .commit();

        }
    }

    // TODO review
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.getBoolean(rotation, rotationdetails);
    }
}
