package com.example.leopo.baking;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.leopo.baking.data.Recipe;
import com.example.leopo.baking.ui.DetailsFragment;
import com.example.leopo.baking.ui.RecipeIngredientsFragment;
import com.example.leopo.baking.ui.RecipeStepsFragment;

public class RecipeActivity extends AppCompatActivity implements RecipeStepsFragment.OnStepClickListener {

    private boolean twoPanels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        if (savedInstanceState == null) {
            // todo check rotation
        }

//        if (findViewById(R.id.video_container) != null) {
//            twoPanels = true;
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.video_container, new DetailsFragment())
//                    .commit();
//        }

        // TODO move higher
        if (savedInstanceState == null) {
            RecipeStepsFragment recipeSteps = new RecipeStepsFragment();

            RecipeIngredientsFragment recipeIngredient = new RecipeIngredientsFragment();

            Intent intent = getIntent();

            Recipe recipe = intent.getParcelableExtra("Recipe");


            recipeSteps.setStepsData(recipe.getSteps());
            recipeIngredient.setIngredientsData(recipe.getIngredients());

            // TODO clean
            // TODO add some things to bundle
            Bundle bundle = new Bundle();
            bundle.putParcelable("RECIPE", recipe);
            recipeSteps.setArguments(bundle);


            DetailsFragment detailsFragment = new DetailsFragment();
            detailsFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.details_fragment_container, detailsFragment)
                    .commit();

        }
    }

<<<<<<< HEAD
    // TODO review
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.getBoolean(rotation, rotationdetails);
    }
=======
    // required to implement interface
    public void onStepClicked(int position) {
        Toast.makeText(this, "Clicked " + position, Toast.LENGTH_LONG).show();
    }

>>>>>>> 3b0962bd30f46cbb8c7f49d6f6a5aa77f5dc8b91
}
