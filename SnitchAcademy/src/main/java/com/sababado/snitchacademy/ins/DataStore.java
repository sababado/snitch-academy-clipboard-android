package com.sababado.snitchacademy.ins;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.sababado.snitchacademy.ins.model.Participant;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * Created by rszabo on 10/25/13.
 */
public class DataStore {
    private static final String SP_PARTICIPANTS = "SHARED_PREFS_PARTICIPANTS";

    /**
     * Save participant data
     * @param context
     * @param participant
     */
    public static void saveParticipant(final Context context, final Participant participant) {
        final SharedPreferences sp = context.getSharedPreferences(SP_PARTICIPANTS, Context.MODE_PRIVATE);
        final String savedState = (new Gson()).toJson(participant);
        sp.edit().putString(participant.name, savedState).commit();
    }

    /**
     * Load a single participant
     * @param context
     * @param name
     * @return
     */
    public static Participant loadParticipant(final Context context, final String name) {
        final SharedPreferences sp = context.getSharedPreferences(SP_PARTICIPANTS, Context.MODE_PRIVATE);
        final String savedState = sp.getString(name, null);
        return savedState == null ? null : (new Gson()).fromJson(savedState, Participant.class);
    }

    /**
     * Load all participants
     * @param context
     * @return
     */
    public static ArrayList<Participant> loadParticipants(final Context context) {
        final SharedPreferences sp = context.getSharedPreferences(SP_PARTICIPANTS, Context.MODE_PRIVATE);
        final Map<String, String> allSavedState = (Map<String, String>) sp.getAll();
        final ArrayList<Participant> participants = new ArrayList<Participant>(allSavedState.size());

        final Set<String> keys = allSavedState.keySet();
        for (String key : keys) {
            final String savedState = sp.getString(key, null);
            if (savedState != null) {
                participants.add((new Gson()).fromJson(savedState, Participant.class));
            }
        }
        return participants;
    }

    /**
     * Load all participant names
     * @param context
     * @return
     */
    public static ArrayList<String> loadParticipantNames(final Context context) {
        final SharedPreferences sp = context.getSharedPreferences(SP_PARTICIPANTS, Context.MODE_PRIVATE);
        final Map<String, String> allSavedState = (Map<String, String>) sp.getAll();
        final ArrayList<String> participants = new ArrayList<String>(allSavedState.size());

        final Set<String> keys = allSavedState.keySet();
        for (String key : keys) {
            participants.add(key);
        }
        return participants;
    }
}
