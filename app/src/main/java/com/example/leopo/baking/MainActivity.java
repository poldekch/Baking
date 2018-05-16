package com.example.leopo.baking;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.leopo.baking.utilities.NetworkUtils;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
