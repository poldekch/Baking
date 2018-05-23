package com.example.leopo.baking.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.leopo.baking.R;
import com.example.leopo.baking.data.Step;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder> {

    private ArrayList<Step> mSteps;
    private Context mContext;

//    private StepClickListener mListener;
//    private int mSelected;

    private final StepAdapterOnClickHandler mClickHandler;

    public interface  StepAdapterOnClickHandler {
        void onClick(int id);
    }


//    public StepAdapter(Context context, ArrayList<Step> steps) {
//        mContext = context;
//        mSteps = steps;
//    }

    public StepAdapter(StepAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    @Override
    public StepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.step_list_item, parent, false);
        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepViewHolder holder, int position) {
        Step step = mSteps.get(position);
        holder.step.setText(step.getId() + ". " + step.getShortDescription());
    }

    @Override
    public int getItemCount() {
        if (null == mSteps) return 0;
        return mSteps.size();
    }

    public class StepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_step)TextView step;

        public StepViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View view) {

            int clickedPosition = getAdapterPosition();

            mClickHandler.onClick(clickedPosition);

//            mListener.onStepClicked(view, getAdapterPosition());
//            mSelected = getAdapterPosition();

//            notifyDataSetChanged();
        }
    }

    public interface StepClickListener {
        // TODO do we need view here?
        void onStepClicked(View view, int position);
    }

//    public void setOnStepClickListener(StepClickListener listener) {
//        mListener = listener;
//    }
}
