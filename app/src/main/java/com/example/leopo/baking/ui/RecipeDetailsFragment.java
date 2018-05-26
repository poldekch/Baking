package com.example.leopo.baking.ui;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leopo.baking.R;
import com.example.leopo.baking.data.Step;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailsFragment extends Fragment{

    Step mStep;
    @BindView(R.id.ep_video)
    SimpleExoPlayerView exoPlayerView;
    @BindView(R.id.iv_missing_video)
    ImageView missingVideo;
    @BindView(R.id.tv_step_description) TextView stepDescription;
    SimpleExoPlayer exoPlayer;

    public RecipeDetailsFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            // todo key to constants
            mStep = bundle.getParcelable("step");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_details, container, false);
        ButterKnife.bind(this, view);

        if (savedInstanceState != null) {
            // todo - retrieve - VideoFragment 94:100
        }
        stepDescription.setText(mStep.getDescription());


        if (mStep.getVideoURL() != null) {
            if (mStep.getVideoURL().equals("")) {
                exoPlayerView.setVisibility(View.GONE);
                missingVideo.setVisibility(View.VISIBLE);
                if (!mStep.getThumbnailURL().equals("")) {
                    Picasso.with(getContext()).load(mStep.getThumbnailURL()).into(missingVideo);
                }

            }
        } else {
            exoPlayerView.setVisibility(View.GONE);
        }



        return view;
    }
}
