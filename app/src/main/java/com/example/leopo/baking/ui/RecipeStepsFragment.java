package com.example.leopo.baking.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.leopo.baking.R;
import com.example.leopo.baking.adapters.StepAdapter;
import com.example.leopo.baking.data.Recipe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecipeStepsFragment extends Fragment {

    // TODO save instance
    public static String RECIPE = "recipe";

    @BindView(R.id.rv_steps)RecyclerView recyclerView;
//    @BindView(R.id.rv_ingredients)RecyclerView recyclerView;

    private Recipe mRecipe;
    private Unbinder unbinder;

    // TODO mCallback

    // TODO jest w tamtym projekcie
    public static RecipeStepsFragment nI(Recipe recipe) {
        RecipeStepsFragment fragment = new RecipeStepsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(RECIPE, recipe);
        fragment.setArguments(bundle);
        return fragment;
    }

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

        StepAdapter mAdapter = new StepAdapter(getContext(), mRecipe.getSteps());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);

        return rootView;
    }

    public void setRecipeData(Recipe recipe) {
        mRecipe = recipe;
    }
}
