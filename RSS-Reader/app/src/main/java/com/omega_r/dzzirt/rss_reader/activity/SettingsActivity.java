package com.omega_r.dzzirt.rss_reader.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.app.dzzirt.rss_reader.R;

import com.omega_r.dzzirt.rss_reader.viewmodel.SettingsViewModel;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Dzzirt on 08.01.2017.
 */
public class SettingsActivity extends BaseActivity {

    public static final String SETTINGS_PREFS = "settings";
    public static final String URL_KEY = "rss_url";

    private SharedPreferences mPreferences;

    @BindView(R.id.edittext_address)
    EditText mRssUrl;

    private SettingsViewModel mSettingsViewModel;

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.settings);
    }

    public static Intent createIntent(Context context) {
        return new Intent(context, SettingsActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_settings);

        mSettingsViewModel = ViewModelProviders.of(this).get(SettingsViewModel.class);

        init();
    }

    public void init() {
        String currentUrl = mSettingsViewModel.getUrlObservable().getValue();
        mRssUrl.setText(currentUrl);
    }

    @OnClick(R.id.button_save)
    public void onSaveButtonClick() {
        String newUrl = mRssUrl.getText().toString();
        mSettingsViewModel.onSave(newUrl);
    }

}
