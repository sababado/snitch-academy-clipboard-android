package com.sababado.snitchacademy.ins;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

/**
 * Created by rszabo on 10/25/13.
 */
public class AddParticipantDialogFragment extends DialogFragment {
    public static final String TAG = AddParticipantDialogFragment.class.getSimpleName();
    private TextView mNameField;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add_participant, container, false);
        view.findViewById(R.id.submit).setOnClickListener(onClickListener);
        mNameField = (TextView)view.findViewById(R.id.participant_name);
        mNameField.setOnEditorActionListener(onEditorActionListener);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().setTitle("Enter Your Name");
        getDialog().setCanceledOnTouchOutside(false);
    }

    final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            saveName();
        }
    };

    final TextView.OnEditorActionListener onEditorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//            if(actionId == EditorInfo.IME_ACTION_DONE) {
//                saveName();
//            }
            return false;
        }
    };
    private void saveName() {
        final String name = (mNameField).getText().toString();
        ((ParticipantListActivity)getActivity()).addParticipant(name);
        dismiss();
    }
}