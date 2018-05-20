package com.example.leopo.baking;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.leopo.baking.data.Recipe;
import com.example.leopo.baking.ui.RecipeIngredientFragment;
import com.example.leopo.baking.ui.RecipeOverviewFragment;

public class RecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        if (savedInstanceState == null) {

            RecipeOverviewFragment recipeOverview = new RecipeOverviewFragment();

            RecipeIngredientFragment recipeIngredient = new RecipeIngredientFragment();

            Intent intent = getIntent();

            Recipe recipe = intent.getParcelableExtra("Recipe");
            recipeOverview.setRecipeData(recipe);
            recipeIngredient.setRecipeData(recipe);

            // TODO clean
            Bundle bundle = new Bundle();
            bundle.putParcelable("RECIPE", recipe);
            recipeOverview.setArguments(bundle);

            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction()
                    .add(R.id.fl_overview, recipeIngredient)
                    .add(R.id.fl_overview, recipeOverview)
                    .commit();
        }
    }
}
