package com.example.leopo.baking;

import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.leopo.baking.utilities.NetworkUtils;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private GridLayoutManager mLayoutManager;

    public static final String RECIPES_STATE_KEY = "recipes";
    private Parcelable mRecipesState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public class FetchRecipesTask extends AsyncTask<String, Void, ArrayList<Recipe>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // TODO
            // mLoadingIndicator.setVisibility(View.VISIBLE)
        }

        @Override
        protected ArrayList<Recipe> doInBackground(String... strings) {
            URL recipesUrl = NetworkUtils.buildUrl();

            try {
                String recipesResponse = NetworkUtils.getApiResponse(recipesUrl);
                // TODO return correct object
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Recipe> recipes) {
            // TODO
            // mLoadingIndicator.setVisibility(View.INVISIBLE);

            if (recipes != null) {
                // TODO implement
            } else {
                // TODO
//                showErrorMessage();
            }
        }
    }
}
