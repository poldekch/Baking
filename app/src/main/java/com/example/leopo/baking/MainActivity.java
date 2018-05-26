package com.example.leopo.baking;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.leopo.baking.adapters.RecipeAdapter;
import com.example.leopo.baking.adapters.RecipeAdapter.RecipeAdapterOnClickHandler;
import com.example.leopo.baking.data.Recipe;
import com.example.leopo.baking.utilities.NetworkUtils;
import com.example.leopo.baking.utilities.RecipesJsonUtils;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String RECIPES_STATE_KEY = "recipes";
    private Parcelable mRecipesState;

    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;

    private static boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.main_fragment_tablet) != null) {
            mTwoPane = true;
        } else {
            mTwoPane = false;
        }
    }

    public static boolean hasTwoPane() {
        return mTwoPane;
    }

    // TODO maybe move to fragment
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//
//        mRecipesState = mLayoutManager.onSaveInstanceState();
//        outState.putParcelable(RECIPES_STATE_KEY, mRecipesState);
//    }

    // TODO maybe move to fragment
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//
//        if (savedInstanceState != null) {
//            mRecipesState = savedInstanceState.getParcelable(RECIPES_STATE_KEY);
//        }
//    }

    // TODO disabled for now - maybe need to move to fragment
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        if (mRecipesState != null) {
//            mLayoutManager.onRestoreInstanceState(mRecipesState);
//        }
//    }

    // TODO maybe need to move to fragment
//    @Override
//    public void onClick(int clickedRecipeId) {
//        Intent intent = new Intent(MainActivity.this, RecipeActivity.class);
//
//        Recipe recipe = mRecipeAdapter.getRecipe(clickedRecipeId);
//        intent.putExtra("Recipe", recipe);
//        startActivity(intent);
//    }

}
