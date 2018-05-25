package com.example.leopo.baking.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.leopo.baking.R;
import com.example.leopo.baking.adapters.IngredientAdapter;
import com.example.leopo.baking.data.Ingredient;
import com.example.leopo.baking.data.Recipe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecipeIngredientsFragment extends Fragment {

    // TODO save instance
    public static String INGREDIENTS = "ingredients";

    @BindView(R.id.rv_ingredients)RecyclerView recyclerView;

    private ArrayList<Ingredient> mIngredients;
    private Unbinder unbinder;

    // TODO mCallback

    // TODO jest w tamtym projekcie
    public static RecipeStepsFragment nI(ArrayList<Ingredient> ingredients) {
        RecipeStepsFragment fragment = new RecipeStepsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(INGREDIENTS, ingredients);
        fragment.setArguments(bundle);
        return fragment;
    }

    public RecipeIngredientsFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mRecipe = getArguments().getParcelable(RECIPE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_recipe_overview, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        IngredientAdapter mAdapter = new IngredientAdapter(getContext(), mIngredients);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);

        return rootView;
    }

    public void setIngredientsData(ArrayList<Ingredient> ingredients) {
        mIngredients = ingredients;
    }
}
