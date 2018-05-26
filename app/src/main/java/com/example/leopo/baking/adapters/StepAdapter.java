package com.example.leopo.baking.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.leopo.baking.ClickCallBack;
import com.example.leopo.baking.R;
import com.example.leopo.baking.data.Step;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder> {

    private Context mContext;
    private ArrayList<Step> mSteps;
    private ClickCallBack mClickCallBack;

    public StepAdapter(Context context, ArrayList<Step> steps) {
        mContext = context;
        mSteps = steps;
    }

    public interface  StepAdapterOnClickHandler {
        void onClick(int id);
    }

    @Override
    public StepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.step_list_item, parent, false);
        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepViewHolder holder, int position) {
        final Step step = mSteps.get(position);
        holder.step.setText(step.getId() + ". " + step.getShortDescription());
        holder.step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickCallBack.onClick(mContext, step.getId(), step.getDescription(), step.getVideoURL(), step.getThumbnailURL());
            }
        });
    }

    public void setOnClick(ClickCallBack clickCallBack) {
        mClickCallBack = clickCallBack;
    }

    @Override
    public int getItemCount() {
        if (null == mSteps) return 0;
        return mSteps.size();
    }

    public class StepViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_step)
        TextView step;

        public StepViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
