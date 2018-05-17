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

public class MainActivity extends AppCompatActivity implements RecipeAdapterOnClickHandler {

    private RecyclerView mRecyclerView;
    private RecipeAdapter mRecipeAdapter;
    private GridLayoutManager mLayoutManager;

    public static final String RECIPES_STATE_KEY = "recipes";
    private Parcelable mRecipesState;

    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.rv_recipes);
        mErrorMessageDisplay = findViewById(R.id.tv_error_message_diaplay);
        mLayoutManager = new GridLayoutManager(this, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        mRecipeAdapter = new RecipeAdapter(this);
        mRecyclerView.setAdapter(mRecipeAdapter);

        mLoadingIndicator = findViewById(R.id.pb_loading_indicator);

        loadRecipeData();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        mRecipesState = mLayoutManager.onSaveInstanceState();
        outState.putParcelable(RECIPES_STATE_KEY, mRecipesState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
            mRecipesState = savedInstanceState.getParcelable(RECIPES_STATE_KEY);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mRecipesState != null) {
            mLayoutManager.onRestoreInstanceState(mRecipesState);
        }
    }

    @Override
    public void onClick(int clickedRecipeId) {
        Intent intent = new Intent(MainActivity.this, RecipeActivity.class);

        Recipe recipe = mRecipeAdapter.getRecipe(clickedRecipeId);
        intent.putExtra("Recipe", recipe);
        startActivity(intent);
    }

    public class FetchRecipesTask extends AsyncTask<String, Void, ArrayList<Recipe>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<Recipe> doInBackground(String... strings) {
            URL recipesUrl = NetworkUtils.buildUrl();

            try {
                String recipesResponse = NetworkUtils.getApiResponse(recipesUrl);
                return RecipesJsonUtils.getRecipesFromJson(MainActivity.this, recipesResponse);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Recipe> recipes) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (recipes != null) {
                showRecipeDataView();
                mRecipeAdapter.setRecipeData(recipes);
            } else {
                showErrorMessage();
            }
        }
    }

    private void loadRecipeData() {
        showRecipeDataView();
        new FetchRecipesTask().execute();
    }

    private void showRecipeDataView() {
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    private int numberOfColumns() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        // You can change this divider to adjust the size of the poster
        int widthDivider = 400;
        int width = displayMetrics.widthPixels;
        int nColumns = width / widthDivider;
        if (nColumns < 2) return 2; //to keep the grid aspect
        return nColumns;
    }
}
