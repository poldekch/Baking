package com.example.leopo.baking;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.leopo.baking.data.Ingredient;
import com.example.leopo.baking.data.Recipe;
import com.example.leopo.baking.data.Step;
import com.example.leopo.baking.ui.DetailsFragment;
import com.example.leopo.baking.ui.OverviewFragment;
import com.example.leopo.baking.ui.RecipeIngredientsFragment;
import com.example.leopo.baking.ui.RecipeStepsFragment;

import java.util.ArrayList;

public class RecipeActivity extends AppCompatActivity {

    private boolean mTwoPanels;
    public ArrayList<Ingredient> mIngredients;
    public ArrayList<Step> mSteps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        if (findViewById(R.id.video_container) != null) {
            mTwoPanels = true;
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.video_container, new DetailsFragment())
                    .commit();
        }


        if (savedInstanceState != null) {
            // todo check rotation
        } else {
            RecipeStepsFragment recipeSteps = new RecipeStepsFragment();

            RecipeIngredientsFragment recipeIngredient = new RecipeIngredientsFragment();

            Intent intent = getIntent();
//
            Recipe recipe = intent.getParcelableExtra("Recipe");


//            recipeSteps.setStepsData(recipe.getSteps());
//            recipeIngredient.setIngredientsData(recipe.getIngredients());

            // TODO clean
            // TODO add some things to bundle
            Bundle bundle = new Bundle();
            bundle.putParcelable("RECIPE", recipe);
            bundle.putBoolean("TWO_PAN", mTwoPanels);


            recipeSteps.setArguments(bundle);

            OverviewFragment overviewFragment = new OverviewFragment();
            overviewFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_recipe_overview, overviewFragment)
                    .commit();


            DetailsFragment detailsFragment = new DetailsFragment();
            detailsFragment.setArguments(bundle);
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.details_fragment_container, detailsFragment)
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
