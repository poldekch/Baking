package com.example.leopo.baking;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.leopo.baking.data.Step;
import com.example.leopo.baking.ui.RecipeDetailsFragment;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    private ArrayList<Step> mSteps;
    private int stepPos;

    private RecipeDetailsFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        FragmentManager fragmentManager = getSupportFragmentManager();
        mFragment = (RecipeDetailsFragment) fragmentManager.findFragmentByTag("RecipeDetailsFragment");

        Bundle bundle = getIntent().getExtras();
        // TODO key as constant
        mSteps = bundle.getParcelableArrayList("steps");
        stepPos = bundle.getInt("stepPos");

        if (savedInstanceState == null) {
            stepPos = bundle.getInt("stepPos");
        } else {
            // TODO key as const
            stepPos = savedInstanceState.getInt("stepPos");
        }

        if (mFragment == null) {
            mFragment = RecipeDetailsFragment.newInstance(mSteps, stepPos);
//            fragmentManager.beginTransaction()
//                    .replace(R.id.fragment_recipe_overview, mFragment, "RecipeDetailsFragment")
//                    .commit();
        }
    }
}
