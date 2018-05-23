package com.example.leopo.baking.ui;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.leopo.baking.R;
import com.example.leopo.baking.adapters.StepAdapter;
import com.example.leopo.baking.data.Step;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecipeStepsFragment extends Fragment {

    // TODO save instance
    public static String STEPS = "steps";

    @BindView(R.id.rv_steps)RecyclerView recyclerView;
//    @BindView(R.id.rv_ingredients)RecyclerView recyclerView;

    private ArrayList<Step> mSteps;
    private Unbinder unbinder;

    // TODO mCallback
    OnStepClickListener mCallback;

    public interface OnStepClickListener {
        void onStepSelected(int position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (OnStepClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnStepClickListener");
        }
    }

    // TODO jest w tamtym projekcie
//    public static RecipeStepsFragment nI(ArrayList<Step> steps) {
//        RecipeStepsFragment fragment = new RecipeStepsFragment();
//        Bundle bundle = new Bundle();
//        bundle.putParcelableArrayList(STEPS, steps);
//        fragment.setArguments(bundle);
//        return fragment;
//    }

//    public interface StepCallback{
//        void onStepSelected(ArrayList<Step> steps, int position);
//    }

    public RecipeStepsFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mRecipe = getArguments().getParcelable(RECIPE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_recipe_steps, container, false);
        unbinder = ButterKnife.bind(this, rootView);

//        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_steps);

        StepAdapter mAdapter = new StepAdapter(getContext(), mSteps);

//        mAdapter.setOnStepClickListener(new StepAdapter.StepClickListener() {
//            @Override
//            public void onStepClicked(View view, int position) {
//
//                StepCallback aaa = (StepCallback)getActivity();
//
//
////                aaa.onStepSelected(mSteps, position);
//            }
//        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);

        return rootView;
    }

    public void setStepsData(ArrayList<Step> steps) {
        mSteps = steps;
    }
}
