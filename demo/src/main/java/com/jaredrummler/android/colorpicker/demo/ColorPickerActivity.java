/*
 * Copyright (C) 2017 Jared Rummler
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jaredrummler.android.colorpicker.demo;

import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import com.jaredrummler.android.colorpicker.ColorPanelView;
import com.jaredrummler.android.colorpicker.ColorPickerView;
import com.jaredrummler.android.colorpicker.ColorPickerView.OnColorChangedListener;

public class ColorPickerActivity extends AppCompatActivity implements OnColorChangedListener, View.OnClickListener {

    private ColorPickerView colorPickerView;
    private ColorPanelView newColorPanelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.RGBA_8888);

        setContentView(R.layout.activity_color_picker);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int initialColor = prefs.getInt("color_3", 0xFF000000);

        colorPickerView = findViewById(R.id.cpv_color_picker_view);
        ColorPanelView colorPanelView = findViewById(R.id.cpv_color_panel_old);
        newColorPanelView = findViewById(R.id.cpv_color_panel_new);

        Button btnOK = findViewById(R.id.okButton);
        Button btnCancel = findViewById(R.id.cancelButton);

        ((LinearLayout) colorPanelView.getParent()).setPadding(colorPickerView.getPaddingLeft(), 0,
                colorPickerView.getPaddingRight(), 0);

        colorPickerView.setOnColorChangedListener(this);
        colorPickerView.setColor(initialColor, true);
        colorPanelView.setColor(initialColor);

        btnOK.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onColorChanged(int newColor) {
        newColorPanelView.setColor(colorPickerView.getColor());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.okButton) {
            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(this).edit();
            edit.putInt("color_3", colorPickerView.getColor());
            edit.apply();
            finish();
        } else if (v.getId() == R.id.cancelButton) {
            finish();
        }
    }
}
