package com.example.leopo.baking.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.example.leopo.baking.data.Step;

import java.util.ArrayList;

public class DetailsFragment extends Fragment{

    public DetailsFragment() {}


    // TODO review
    public static DetailsFragment newInstance(ArrayList<Step> steps, int position) {

        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("Steps", steps);
        bundle.putInt("Position", position);

        detailsFragment.setArguments(bundle);

        return detailsFragment;
    }


}
