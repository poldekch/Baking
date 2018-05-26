package com.example.leopo.baking.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.leopo.baking.R;
import com.example.leopo.baking.data.Ingredient;
import com.example.leopo.baking.data.Recipe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeAdapterViewHolder> {

    private ArrayList<Recipe> mRecipes;
    private Context mContext;

//    private final RecipeAdapterOnClickHandler mClickHandler;

    public interface RecipeAdapterOnClickHandler {
        void onClick(int clickedRecipeId);
    }

    public Recipe getRecipe(int i) {
        return mRecipes.get(i);
    }

//    public RecipeAdapter(RecipeAdapterOnClickHandler clickHandler) {
//        mClickHandler = clickHandler;
//    }

    public RecipeAdapter(Context context, ArrayList<Recipe> recipes) {
        mContext = context;
        mRecipes = recipes;
    }

    public class RecipeAdapterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name) TextView mName;
        @BindView(R.id.tv_servings) TextView mServings;

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
        Recipe recipe = mRecipes.get(position);
        String name = recipe.getName();
        holder.mName.setText(name);
        String servings = "Servings: " + recipe.getServings();
        holder.mServings.setText(servings);

        // TODO add onclicklistener
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





















//package com.example.leopo.baking.adapters;
//
//        import android.content.Context;
//        import android.support.v7.widget.RecyclerView;
//        import android.view.LayoutInflater;
//        import android.view.View;
//        import android.view.ViewGroup;
//        import android.widget.TextView;
//
//        import com.example.leopo.baking.R;
//        import com.example.leopo.baking.data.Ingredient;
//
//        import java.util.ArrayList;
//
//        import butterknife.BindView;
//        import butterknife.ButterKnife;
//
//public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder>{
//
//
//    private ArrayList<Ingredient> mIngredients;
//    private Context mContext;
//
//    public IngredientAdapter(Context context, ArrayList<Ingredient> ingredients) {
//        mContext = context;
//        mIngredients = ingredients;
//    }
//
//
//    @Override
//    public IngredientAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
////        View view = LayoutInflater.from(parent.getContext())
////                .inflate(R.layout.ingredient_list_item, parent, false);
////        return new IngredientAdapter.ViewHolder(view);
//        return null
//    }
//
//    @Override
//    public void onBindViewHolder(IngredientAdapter.ViewHolder holder, int position) {
//        Ingredient ingredient = mIngredients.get(position);
////        holder.ingredient.setText(ingredient.getIngredient());
////        holder.amount.setText(ingredient.getQuantity() + " " + ingredient.getMeasure());
//    }
//
//    @Override
//    public int getItemCount() {
//        if (null == mIngredients) return 0;
//        return mIngredients.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
////        @BindView(R.id.tv_ingredient)TextView ingredient;
////        @BindView(R.id.tv_amount)TextView amount;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//        }
//    }
//}
