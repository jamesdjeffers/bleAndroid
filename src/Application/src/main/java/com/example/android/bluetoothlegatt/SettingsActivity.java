package com.example.android.bluetoothlegatt;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/* Settings Activity for Bluetooth Com Port
 *  View:
 *      Display help
 *      Display D-Pad
 *  Custom:
 *      Buttons
 *  Connections:
 *      Auto-load
 *
*/
public class SettingsActivity extends AppCompatActivity {
    private static  final   String TAG_FRAGMENT = "Settings Fragment";

    public static final String PREF_VIEW_HELP = "view_help";
    public static final String PREF_VIEW_SEND = "view_send";
    public static final String PREF_VIEW_DPAD = "view_dpad";

    public static final String PREF_COM_NEWLINE_APPEND = "append_newline";
    public static final String PREF_COM_NEWLINE_REMOVE = "remove_newline";

    public static final String PREF_LOG_NAME = "log_name";
    public static final String PREF_LOG_TIME = "log_time";
    public static final String PREF_LOG_COMMA = "log_comma";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getActionBar().setTitle("Customize the View");
    }

    /* A preference value change listener that updates the preference's summary
     * to reflect its new value.
    */
    private static Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
        String stringValue = value.toString();

        if (preference instanceof ListPreference) {
             // For list preferences, 'entries' list.
            ListPreference listPreference = (ListPreference) preference;
            int index = listPreference.findIndexOfValue(stringValue);

            // Set the summary to reflect the new value.
            preference.setSummary( index >= 0 ? listPreference.getEntries()[index] : null);

        } else {
            // For all other preferences, set the summary to the value's
            // simple string representation.
            preference.setSummary(stringValue);
        }
        return true;
        }
    };

     /**
     * Binds a preference's summary to its value. More specifically, when the
     * preference's value is changed, its summary (line of text below the
     * preference title) is updated to reflect the value. The summary is also
     * immediately updated upon calling this method. The exact display format is
     * dependent on the type of preference.
     *
     * @see #sBindPreferenceSummaryToValueListener
     */
    private static void bindPreferenceSummaryToValue(Preference preference) {
        // Set the listener to watch for value changes.
        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);

        // Trigger the listener immediately with the preference's
        // current value.
        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }

    /* This fragment shows a one page settings
     */
    public static class SettingsFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_general);

            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
            // to their values. When their values change, their summaries are
            // updated to reflect the new value, per the Android Design
            // guidelines.

        }
    }
}
