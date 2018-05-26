package com.example.leopo.baking.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.leopo.baking.ClickCallBack;
import com.example.leopo.baking.DetailsActivity;
import com.example.leopo.baking.R;
import com.example.leopo.baking.data.Ingredient;
import com.example.leopo.baking.data.Step;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class OverviewFragment extends Fragment implements ClickCallBack {




    // TODO save instance
    public static String STEPS = "steps";

    @BindView(R.id.rv_steps)RecyclerView recyclerView;
//    @BindView(R.id.rv_ingredients)RecyclerView recyclerView;

    private ArrayList<Step> mSteps;
    private Unbinder unbinder;

//    public interface OnStepClickListener {
//        void onStepClicked(int pos);
//    }


    private boolean mTwoPane;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        // TODO tutaj
//        mIngredients = bundle.getParcelable("RECIPE");
//        mSteps
//        mRecipe = getArguments().getParcelable(RECIPE);

        mTwoPane = bundle.getBoolean("TWO_PAN");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_recipe_overview, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_steps);


        // TODO pass listener in constructor
//        StepAdapter mAdapter = new StepAdapter(getContext(), mSteps);

//        StepAdapter mAdapter = new StepAdapter();

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
//        recyclerView.setAdapter(mAdapter);

        return rootView;
    }





    // Implementing interface
    @Override // TODO it was there...
    public void onClick(Context context, Integer id, String description, String url, String thumbnailUrl) {
        if (mTwoPane) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("TWO_PAN", mTwoPane);
            bundle.putString("DESCRIPTION", description);
            // TODO add lines to replace fragment from DetailFragment 188:192
        } else {
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("DESCRIPTION", description);
            context.startActivity(intent);
        }
    }















//    @Override
//    public void onStepSelected(int position) {
//        Toast.makeText(getContext(), "Position " + position, Toast.LENGTH_LONG).show();
//    }

    // TODO mCallback
//    OnStepClickListener mCallback;

//    public interface OnStepClickListener {
//        void onStepSelected(int position);
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//
//        try {
//            mCallback = (OnStepClickListener) context;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(context.toString() + " must implement OnStepClickListener");
//        }
//    }

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

//    public RecipeStepsFragment() {}


    public void setStepsData(ArrayList<Step> steps) {
        mSteps = steps;
    }


//
//    // TODO save instance
//    public static String INGREDIENTS = "ingredients";
//
//    @BindView(R.id.rv_ingredients)RecyclerView recyclerView;
//
//    private ArrayList<Ingredient> mIngredients;
//    private Unbinder unbinder;

    // TODO mCallback

//    // TODO jest w tamtym projekcie
//    public static RecipeStepsFragment nI(ArrayList<Ingredient> ingredients) {
//        RecipeStepsFragment fragment = new RecipeStepsFragment();
//        Bundle bundle = new Bundle();
//        bundle.putParcelableArrayList(INGREDIENTS, ingredients);
//        fragment.setArguments(bundle);
//        return fragment;
//    }

//    public RecipeIngredientsFragment() {}

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        mRecipe = getArguments().getParcelable(RECIPE);
//    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//        View rootView = inflater.inflate(R.layout.fragment_recipe_overview, container, false);
//        unbinder = ButterKnife.bind(this, rootView);
//
//        IngredientAdapter mAdapter = new IngredientAdapter(getContext(), mIngredients);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
//        recyclerView.setAdapter(mAdapter);
//
//        return rootView;
//    }
//
//    public void setIngredientsData(ArrayList<Ingredient> ingredients) {
//        mIngredients = ingredients;
//    }


}
