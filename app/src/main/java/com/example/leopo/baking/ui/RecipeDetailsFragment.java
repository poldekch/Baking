package com.example.leopo.baking.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaButtonReceiver;
import android.support.v4.media.session.MediaSessionCompat;
import android.os.Bundle;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leopo.baking.R;
import com.example.leopo.baking.data.Step;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecipeDetailsFragment extends Fragment implements ExoPlayer.EventListener {

    private static final String TAG = RecipeDetailsFragment.class.getSimpleName();

    Step mStep;
    @BindView(R.id.ep_video) SimpleExoPlayerView mExoPlayerView;
    @BindView(R.id.iv_missing_video) ImageView missingVideo;
    @BindView(R.id.tv_step_description) TextView stepDescription;
    private SimpleExoPlayer mExoPlayer;
    private long mPlayerPosition;
    private boolean mPlayerPlayWhenReady;
    private Uri mVideoUri;
    private PlaybackStateCompat.Builder mStateBuilder;
    private static MediaSessionCompat mMediaSession;

    private Unbinder mUnbinder;

    public RecipeDetailsFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mStep = bundle.getParcelable("step");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_details, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        if (savedInstanceState != null) {
            mExoPlayerView.setVisibility(savedInstanceState.getInt("player_visivility"));
            missingVideo.setVisibility(savedInstanceState.getInt("placeholder_visibility"));
            mPlayerPlayWhenReady = savedInstanceState.getBoolean("player_state");
        }

        if (mStep != null) {
            stepDescription.setText(mStep.getDescription());

            if (mStep.getVideoURL() != null) {
                if (TextUtils.isEmpty(mStep.getVideoURL())) {
                    mExoPlayerView.setVisibility(View.GONE);
                    missingVideo.setVisibility(View.VISIBLE);
                    if (!TextUtils.isEmpty(mStep.getThumbnailURL())) {
                        Picasso.with(getContext()).load(mStep.getThumbnailURL()).into(missingVideo);
                    }
                } else {
                    if (savedInstanceState != null) {
                        mPlayerPosition = savedInstanceState.getLong("player_position");
                    }
                    mVideoUri = Uri.parse(mStep.getVideoURL());
                    missingVideo.setVisibility(View.GONE);
                    initialiseMediaSession();
                    initialisePlayer(mVideoUri);
                }
            }
        } else {
            mExoPlayerView.setVisibility(View.GONE);
            missingVideo.setVisibility(View.GONE);
        }
        return view;
    }

    private void initialiseMediaSession() {
        mMediaSession = new MediaSessionCompat(getActivity(), TAG);
        mMediaSession.setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS | MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);
        mMediaSession.setMediaButtonReceiver(null);
        mStateBuilder = new PlaybackStateCompat.Builder()
                .setActions(
                        PlaybackStateCompat.ACTION_PLAY |
                        PlaybackStateCompat.ACTION_PAUSE |
                        PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS |
                        PlaybackStateCompat.ACTION_PLAY_PAUSE
                );
        mMediaSession.setPlaybackState(mStateBuilder.build());
        mMediaSession.setCallback(new RecipeDetailsCallbacks());
        mMediaSession.setActive(true);
    }

    private void initialisePlayer(Uri videoUri) {
        if (mExoPlayer == null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector, loadControl);
            mExoPlayerView.setPlayer(mExoPlayer);

            mExoPlayer.addListener(this);

            String userAgent = Util.getUserAgent(getActivity(), getActivity().getString(R.string.exo_player));
            MediaSource mediaSource = new ExtractorMediaSource(videoUri, new DefaultDataSourceFactory(getActivity(), userAgent), new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
        }
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {}

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {}

    @Override
    public void onLoadingChanged(boolean isLoading) {}

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        if (playbackState == ExoPlayer.STATE_READY && playWhenReady) {
            mStateBuilder.setState(PlaybackStateCompat.STATE_PLAYING, mExoPlayer.getCurrentPosition(), 1f);
        } else if (playbackState == ExoPlayer.STATE_READY) {
            mStateBuilder.setState(PlaybackStateCompat.STATE_PAUSED, mExoPlayer.getCurrentPosition(), 1f);
        }
        mMediaSession.setPlaybackState(mStateBuilder.build());
    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {}

    @Override
    public void onPositionDiscontinuity() {}

    private class RecipeDetailsCallbacks extends MediaSessionCompat.Callback {
        @Override
        public void onPlay() {
            mExoPlayer.setPlayWhenReady(true);
        }

        @Override
        public void onPause() {
            mExoPlayer.setPlayWhenReady(false);
        }

        @Override
        public void onSkipToPrevious() {
            mExoPlayer.seekTo(0);
        }
    }

    public static class MediaReceiver extends BroadcastReceiver {
        public MediaReceiver() {}

        @Override
        public void onReceive(Context context, Intent intent) {
            MediaButtonReceiver.handleIntent(mMediaSession, intent);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mExoPlayer != null) {
            mPlayerPosition = mExoPlayer.getCurrentPosition();
            mPlayerPlayWhenReady = mExoPlayer.getPlayWhenReady();
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mExoPlayer == null) {
            initialiseMediaSession();
            initialisePlayer(mVideoUri);
        }
        mExoPlayer.setPlayWhenReady(mPlayerPlayWhenReady);
        mExoPlayer.seekTo(mPlayerPosition);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("player_visibility", mExoPlayerView.getVisibility());
        outState.putInt("placeholder_visibility", missingVideo.getVisibility());
        outState.putLong("player_position", mPlayerPosition);
        outState.putBoolean("player_state", mPlayerPlayWhenReady);
    }
}
