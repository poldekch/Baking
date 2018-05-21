package com.example.leopo.baking;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.leopo.baking.data.Step;
import com.example.leopo.baking.ui.DetailsFragment;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    private ArrayList<Step> mSteps;
    private int stepPos;

    private DetailsFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        FragmentManager fragmentManager = getSupportFragmentManager();
        mFragment = (DetailsFragment) fragmentManager.findFragmentByTag("DetailsFragment");

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
            mFragment = DetailsFragment.newInstance(mSteps, stepPos);
            fragmentManager.beginTransaction()
                    .replace(R.id.details_fragment_container, mFragment, "DetailsFragment")
                    .commit();
        }
    }
}
