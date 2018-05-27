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
import android.widget.TextView;

import com.example.leopo.baking.ClickCallBack;
import com.example.leopo.baking.DetailsActivity;
import com.example.leopo.baking.R;
import com.example.leopo.baking.adapters.StepAdapter;
import com.example.leopo.baking.data.Ingredient;
import com.example.leopo.baking.data.Step;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecipeOverviewFragment extends Fragment implements ClickCallBack {

    private ArrayList<Ingredient> mIngredients;
    private ArrayList<Step> mSteps;

    private Unbinder unbinder;

    private boolean mTwoPane;

    // TODO save instance

    @BindView(R.id.tv_ingredients)
    TextView ingredients;
    @BindView(R.id.rv_steps)
    RecyclerView recyclerView;

    public RecipeOverviewFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mIngredients = bundle.getParcelableArrayList("ingredients");
        mSteps = bundle.getParcelableArrayList("steps");
        mTwoPane = bundle.getBoolean("TWO_PANE");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_recipe_overview, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        String ingredientsString = new String();
        for (int i = 0; i <= mIngredients.size() - 1; i++) {
            Ingredient ingredient = mIngredients.get(i);
            ingredientsString = ingredientsString + "- " + ingredient.getQuantity() + " " + ingredient.getMeasure() + " of " + ingredient.getIngredient() + "\n";
        }
        ingredients.setText(ingredientsString);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        if (savedInstanceState != null) {
            // TODO populate list from savedinstance
        }

        StepAdapter stepAdapter = new StepAdapter(getActivity(), mSteps);
        stepAdapter.setOnClick(this);
        recyclerView.setAdapter(stepAdapter);

        return rootView;
    }

    // Implementing interface
    @Override // TODO it was there...
    public void onClick(Context context, Integer id, String description, String url, String thumbnailUrl) {

        Step step = mSteps.get(id);

        if (mTwoPane) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("TWO_PANE", mTwoPane);
            bundle.putParcelable("step", step);
            RecipeDetailsFragment recipeDetailsFragment = new RecipeDetailsFragment();
            recipeDetailsFragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.placeholder_fragment_recipe_details, recipeDetailsFragment).commit();
        } else {
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("step", step);
            context.startActivity(intent);
        }
    }
}
