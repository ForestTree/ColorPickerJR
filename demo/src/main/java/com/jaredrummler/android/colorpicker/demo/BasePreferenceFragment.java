package com.jaredrummler.android.colorpicker.demo;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;

import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceGroupAdapter;
import androidx.preference.PreferenceScreen;
import androidx.preference.PreferenceViewHolder;
import androidx.recyclerview.widget.RecyclerView;

// Fix for PreferenceFragmentCompat spacing.
//
// https://stackoverflow.com/questions/18509369
// https://issuetracker.google.com/issues/111907042
// https://issuetracker.google.com/issues/116170936
//
// I never wanted to support the preference fragment compat library. It sucks!
// Give the developers what they want.. yeah.. your welcome. :P

public abstract class BasePreferenceFragment extends PreferenceFragmentCompat {

    @SuppressLint("RestrictedApi")
    @Override
    protected RecyclerView.Adapter onCreateAdapter(PreferenceScreen preferenceScreen) {
        return new PreferenceGroupAdapter(preferenceScreen) {
            @SuppressLint("RestrictedApi")
            @Override
            public void onBindViewHolder(PreferenceViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                Preference preference = getItem(position);
                if (preference instanceof PreferenceCategory) {
                    setZeroPaddingToLayoutChildren(holder.itemView);
                } else {
                    View iconFrame = holder.itemView.findViewById(R.id.icon_frame);
                    if (iconFrame != null) {
                        iconFrame.setVisibility(preference.getIcon() == null ? View.GONE : View.VISIBLE);
                    }
                }
            }
        };
    }

    private void setZeroPaddingToLayoutChildren(View view) {
        if (!(view instanceof ViewGroup)) {
            return;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            setZeroPaddingToLayoutChildren(viewGroup.getChildAt(i));
            viewGroup.setPaddingRelative(0, viewGroup.getPaddingTop(), viewGroup.getPaddingEnd(),
                    viewGroup.getPaddingBottom());
        }
    }
}