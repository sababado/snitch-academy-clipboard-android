package com.sababado.snitchacademy.ins;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.sababado.snitchacademy.ins.model.DrillRating;
import com.sababado.snitchacademy.ins.model.Participant;
import com.sababado.snitchacademy.ins.model.Rating;
import com.sababado.snitchacademy.ins.view.DrillRatingsView;

/**
 * A fragment representing a single Participant detail screen.
 * This fragment is either contained in a {@link ParticipantListActivity}
 * in two-pane mode (on tablets) or a {@link ParticipantDetailActivity}
 * on handsets.
 */
public class ParticipantDetailFragment extends Fragment {

    private static final String TAG = ParticipantDetailFragment.class.getSimpleName();
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_NAME = "participant_name";
    public static final String KEY_FIRST_RUN = "run_1";
    public static final String KEY_SECOND_RUN = "run_2";
    public static final String KEY_THIRD_RUN = "run_3";

    /**
     * The content this fragment is presenting.
     */
    private Participant participant;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ParticipantDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_NAME)) {
            // Load the content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            final String name = getArguments().getString(ARG_NAME);
            participant = DataStore.loadParticipant(getActivity(), name);
            Log.v(TAG, "check: load data: " + participant);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_participant_detail, container, false);

        // Show the content as text in a TextView.
        if (participant != null) {
            ((TextView) rootView.findViewById(R.id.participant_name)).setText(participant.name);
        }

        init(rootView);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        init(getView());
    }

    private void init(final View rootView) {
        initDrillRatingView((DrillRatingsView) rootView.findViewById(R.id.pokerface), participant.pokerface);
        initDrillRatingView((DrillRatingsView) rootView.findViewById(R.id.arena), participant.arena);
        initDrillRatingView((DrillRatingsView) rootView.findViewById(R.id.endurance_arena), participant.enduranceArena);
        initDrillRatingView((DrillRatingsView) rootView.findViewById(R.id.no_contact), participant.noContact);
        initDrillRatingView((DrillRatingsView) rootView.findViewById(R.id.blockade), participant.blockade);
        initDrillRatingView((DrillRatingsView) rootView.findViewById(R.id.chase_plus), participant.chasePlus);
    }

    private void initDrillRatingView(final DrillRatingsView drillRatingsView, final DrillRating rating) {
        if (rating != null) {
            initRating(drillRatingsView, R.id.first_run, KEY_FIRST_RUN, rating);
            initRating(drillRatingsView, R.id.second_run, KEY_SECOND_RUN, rating);
            initRating(drillRatingsView, R.id.third_run, KEY_THIRD_RUN, rating);
        }
        drillRatingsView.setOnRatingChangeListener(onRatingChangeListener);
    }

    private void initRating(final DrillRatingsView drillRatingsView, final int ratingBarId, final String ratingKey, final DrillRating rating) {
        final Rating r = rating.getRating(ratingKey);
        if (r != null) {
            ((RatingBar) drillRatingsView.findViewById(ratingBarId)).setRating(r.getRating());
        }
    }

    final private DrillRatingsView.OnRatingChangeListener onRatingChangeListener = new DrillRatingsView.OnRatingChangeListener() {
        @Override
        public void onRatingChanged(final String drillName, final RatingBar ratingBar, final float rating, final boolean fromUser) {
            if (fromUser) {
                final int ratingBarId = ratingBar.getId();
                if (getString(R.string.pokerface).equals(drillName)) {
                    if (participant.pokerface == null) {
                        participant.pokerface = new DrillRating(drillName);
                    }
                    putRating(participant.pokerface, ratingBarId, rating);
                } else if (getString(R.string.arena).equals(drillName)) {
                    if (participant.arena == null) {
                        participant.arena = new DrillRating(drillName);
                    }
                    putRating(participant.arena, ratingBarId, rating);
                } else if (getString(R.string.endurance_arena).equals(drillName)) {
                    if (participant.enduranceArena == null) {
                        participant.enduranceArena = new DrillRating(drillName);
                    }
                    putRating(participant.enduranceArena, ratingBarId, rating);
                } else if (getString(R.string.no_contact).equals(drillName)) {
                    if (participant.noContact == null) {
                        participant.noContact = new DrillRating(drillName);
                    }
                    putRating(participant.noContact, ratingBarId, rating);
                } else if (getString(R.string.blockade).equals(drillName)) {
                    if (participant.blockade == null) {
                        participant.blockade = new DrillRating(drillName);
                    }
                    putRating(participant.blockade, ratingBarId, rating);
                } else if (getString(R.string.chase_plus).equals(drillName)) {
                    if (participant.chasePlus == null) {
                        participant.chasePlus = new DrillRating(drillName);
                    }
                    putRating(participant.chasePlus, ratingBarId, rating);
                }

                DataStore.saveParticipant(getActivity(), participant);
                Log.v(TAG, "check: rating change after: " + participant);
            }
        }

        private void putRating(DrillRating drillRating, final int ratingBarId, final float rating) {
            switch (ratingBarId) {
                case R.id.first_run:
                    drillRating.putRating(KEY_FIRST_RUN, (int) (rating + 0.5f));
                    break;
                case R.id.second_run:
                    drillRating.putRating(KEY_SECOND_RUN, (int) (rating + 0.5f));
                    break;
                case R.id.third_run:
                    drillRating.putRating(KEY_THIRD_RUN, (int) (rating + 0.5f));
                    break;
            }
        }
    };
}
