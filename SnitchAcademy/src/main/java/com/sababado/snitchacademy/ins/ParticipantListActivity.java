package com.sababado.snitchacademy.ins;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;


/**
 * An activity representing a list of Participants. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ParticipantDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link ParticipantListFragment} and the item details
 * (if present) is a {@link ParticipantDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link ParticipantListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class ParticipantListActivity extends FragmentActivity
        implements ParticipantListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_list);

        if (findViewById(R.id.participant_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((ParticipantListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.participant_list))
                    .setActivateOnItemClick(true);
        }

        // TODO: If exposing deep links into your app, handle intents here.
    }

    /**
     * Callback method from {@link ParticipantListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String name) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(ParticipantDetailFragment.ARG_NAME, name);
            ParticipantDetailFragment fragment = new ParticipantDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.participant_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, ParticipantDetailActivity.class);
            detailIntent.putExtra(ParticipantDetailFragment.ARG_NAME, name);
            startActivity(detailIntent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_add) {
            final AddParticipantDialogFragment addParticipantDialogFragment = new AddParticipantDialogFragment();
            addParticipantDialogFragment.show(getSupportFragmentManager(), AddParticipantDialogFragment.TAG);
        }
        return super.onOptionsItemSelected(item);
    }

    void addParticipant(final String name) {
        final ParticipantListFragment participantListFragment = (ParticipantListFragment) getSupportFragmentManager().findFragmentById(R.id.participant_list);
        if (participantListFragment != null && participantListFragment.isAdded()) {
            participantListFragment.addParticipant(name);
        }
    }
}
