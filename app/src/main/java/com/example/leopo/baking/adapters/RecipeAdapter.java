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
        public final TextView mName;

        public RecipeAdapterViewHolder(View view) {
            super(view);

            mName = view.findViewById(R.id.tv_name);
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
        String name = mRecipes.get(position).getName();
        holder.mName.setText(name);
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
