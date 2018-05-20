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
import com.example.leopo.baking.adapters.StepAdapter;
import com.example.leopo.baking.data.Recipe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecipeIngredientFragment extends Fragment {

    // TODO save instance
    public static String INGREDIENTS = "recipe";

    @BindView(R.id.rv_ingredients)RecyclerView recyclerView;

    private Recipe mRecipe;
    private Unbinder unbinder;

    // TODO mCallback

    // TODO jest w tamtym projekcie
    public static com.example.leopo.baking.ui.RecipeOverviewFragment nI(Recipe recipe) {
        com.example.leopo.baking.ui.RecipeOverviewFragment fragment = new com.example.leopo.baking.ui.RecipeOverviewFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(INGREDIENTS, recipe);
        fragment.setArguments(bundle);
        return fragment;
    }

    public RecipeIngredientFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mRecipe = getArguments().getParcelable(RECIPE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_recipe_ingredients, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        IngredientAdapter mAdapter = new IngredientAdapter(getContext(), mRecipe.getIngredients());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);

        return rootView;
    }

    public void setRecipeData(Recipe recipe) {
        mRecipe = recipe;
    }
}
