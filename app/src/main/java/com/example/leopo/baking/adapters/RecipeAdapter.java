package com.example.leopo.baking.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.leopo.baking.R;
import com.example.leopo.baking.data.Recipe;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeAdapterViewHolder> {

    private ArrayList<Recipe> mRecipes;
    private Context mContext;

    private final RecipeAdapterOnClickHandler mClickHandler;

    public interface RecipeAdapterOnClickHandler {
        void onClick(int clickedRecipeId);
    }

    public Recipe getRecipe(int i) {
        return mRecipes.get(i);
    }

    public RecipeAdapter(RecipeAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    public class RecipeAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // TODO change binding
        public final TextView mName;
        public final TextView mServings;


        public RecipeAdapterViewHolder(View view) {
            super(view);

            // TODO change binding to butterknife
            // TODO only one listener for whole box
            mName = view.findViewById(R.id.tv_name);
            view.setOnClickListener(this);
            mServings = view.findViewById(R.id.tv_servings);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mClickHandler.onClick(clickedPosition);
        }
    }

    @Override
    public RecipeAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        int layoutIdForListItem = R.layout.recipe_list_item;
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View view = inflater.inflate(layoutIdForListItem, parent, false);
        return new RecipeAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeAdapterViewHolder holder, int position) {
        Recipe recipe = mRecipes.get(position);
        String name = recipe.getName();
        holder.mName.setText(name);
        String servings = "Servings: " + recipe.getServings();
        holder.mServings.setText(servings);
    }

    @Override
    public int getItemCount() {
        if (null == mRecipes) return 0;
        return mRecipes.size();
    }

    public void setRecipeData(ArrayList<Recipe> recipeData) {
        mRecipes = recipeData;
        notifyDataSetChanged();
    }
}
