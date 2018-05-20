package com.example.leopo.baking;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.leopo.baking.data.Recipe;
import com.example.leopo.baking.ui.RecipeIngredientsFragment;
import com.example.leopo.baking.ui.RecipeStepsFragment;

public class RecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);


        // two pane
//        if (findViewById(R.id.))



        if (savedInstanceState == null) {

            RecipeStepsFragment recipeSteps = new RecipeStepsFragment();

            RecipeIngredientsFragment recipeIngredient = new RecipeIngredientsFragment();

            Intent intent = getIntent();

            Recipe recipe = intent.getParcelableExtra("Recipe");
            recipeSteps.setStepsData(recipe.getSteps());
            recipeIngredient.setIngredientsData(recipe.getIngredients());

            // TODO clean
            Bundle bundle = new Bundle();
            bundle.putParcelable("RECIPE", recipe);
            recipeSteps.setArguments(bundle);

            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction()
                    .replace(R.id.fl_ingredients, recipeIngredient)
                    .replace(R.id.fl_steps, recipeSteps)
                    .commit();
        }
    }
}
