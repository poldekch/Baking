package com.example.leopo.baking.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.leopo.baking.R;
import com.example.leopo.baking.data.Ingredient;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder>{


    private ArrayList<Ingredient> mIngredients;
    private Context mContext;

    public IngredientAdapter(Context context, ArrayList<Ingredient> ingredients) {
        mContext = context;
        mIngredients = ingredients;
    }


    @Override
    public IngredientAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredient_list_item, parent, false);
        return new IngredientAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IngredientAdapter.ViewHolder holder, int position) {
        holder.ingredient.setText(mIngredients.get(position).getIngredient());
    }

    @Override
    public int getItemCount() {
        if (null == mIngredients) return 0;
        return mIngredients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_ingredient)TextView ingredient;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
