package com.example.leopo.baking.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.leopo.baking.MainActivity;
import com.example.leopo.baking.R;
import com.example.leopo.baking.adapters.RecipeAdapter;
import com.example.leopo.baking.data.Recipe;
import com.example.leopo.baking.utilities.NetworkUtils;
import com.example.leopo.baking.utilities.RecipesJsonUtils;

import java.net.URL;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainFragment extends Fragment {

    @BindView(R.id.rv_recipes) RecyclerView mRecyclerView;
    @BindView(R.id.tv_error_message_display) TextView mErrorMessageDisplay;
    @BindView(R.id.pb_loading_indicator) ProgressBar mLoadingIndicator;

    private RecipeAdapter mRecipeAdapter;
    private GridLayoutManager mLayoutManager;

    private Unbinder mUnbinder;

    public MainFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        loadRecipeData();

        return view;
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
                return RecipesJsonUtils.getRecipesFromJson(recipesResponse);
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
                displayData(recipes);
            } else {
                showErrorMessage();
            }
        }

        private int numberOfColumns() {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            // You can change this divider to adjust the size of the poster
            int widthDivider = 600;
            int width = displayMetrics.widthPixels;
            int nColumns = width / widthDivider;
            if (nColumns < 2) return 2; //to keep the grid aspect
            return nColumns;
        }

        private void displayData(ArrayList<Recipe> recipes) {
            mRecipeAdapter = new RecipeAdapter(getActivity(), recipes);
            if (MainActivity.hasTwoPane()) {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), numberOfColumns());
                mRecyclerView.setLayoutManager(gridLayoutManager);
            } else {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                mRecyclerView.setLayoutManager(linearLayoutManager);
            }
            mRecyclerView.setAdapter(mRecipeAdapter);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
