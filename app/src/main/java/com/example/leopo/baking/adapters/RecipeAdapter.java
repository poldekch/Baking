package com.example.leopo.baking.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.leopo.baking.R;
import com.example.leopo.baking.RecipeActivity;
import com.example.leopo.baking.data.Recipe;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeAdapterViewHolder> {

    private ArrayList<Recipe> mRecipes;
    private Context mContext;
    private SharedPreferences sharedPreferences;

    public interface RecipeAdapterOnClickHandler {
        void onClick(int clickedRecipeId);
    }

    public Recipe getRecipe(int i) {
        return mRecipes.get(i);
    }

    public RecipeAdapter(Context context, ArrayList<Recipe> recipes) {
        mContext = context;
        mRecipes = recipes;
        sharedPreferences = context.getSharedPreferences("BakingData", Context.MODE_PRIVATE);
    }

    public class RecipeAdapterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name) TextView mName;
        @BindView(R.id.tv_servings) TextView mServings;
        @BindView(R.id.fl_frame) FrameLayout mFrame;

        public RecipeAdapterViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public RecipeAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutIdForListItem = R.layout.recipe_list_item;
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View view = inflater.inflate(layoutIdForListItem, parent, false);
        return new RecipeAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeAdapterViewHolder holder, int position) {
        final Recipe recipe = mRecipes.get(position);
        String name = recipe.getName();
        holder.mName.setText(name);
        String servings = "Servings: " + recipe.getServings();
        holder.mServings.setText(servings);
        holder.mFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, RecipeActivity.class);
                intent.putExtra("Recipe", recipe);
                Gson gson = new Gson();
                String recipeString = gson.toJson(recipe);
                sharedPreferences.edit().putString("Recipe", recipeString).apply();
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (null == mRecipes) return 0;
        return mRecipes.size();
    }
}
