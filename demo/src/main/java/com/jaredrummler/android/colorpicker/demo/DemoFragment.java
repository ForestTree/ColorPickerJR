package com.jaredrummler.android.colorpicker.demo;

import android.os.Bundle;
import android.util.Log;

import com.jaredrummler.android.colorpicker.ColorPreferenceCompat;

public class DemoFragment extends BasePreferenceFragment {

    private static final String TAG = "DemoFragment";

    private static final String KEY_DEFAULT_COLOR = "default_color";

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.main, rootKey);

        // Example showing how we can get the new color when it is changed:
        ColorPreferenceCompat colorPreference = findPreference(KEY_DEFAULT_COLOR);
        colorPreference.setOnPreferenceChangeListener((preference, newValue) -> {
            if (KEY_DEFAULT_COLOR.equals(preference.getKey())) {
                String newDefaultColor = Integer.toHexString((int) newValue);
                Log.d(TAG, "New default color is: #" + newDefaultColor);
            }
            return true;
        });
    }
}
