package com.app.dzzirt.rss_reader.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.app.dzzirt.rss_reader.R;
import com.app.dzzirt.rss_reader.presenter.SettingsPresenter;
import com.app.dzzirt.rss_reader.view.SettingsView;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Dzzirt on 08.01.2017.
 */
@EActivity(R.layout.settings_activity)
public class SettingsActivity extends MvpAppCompatActivity implements SettingsView {

    public static final String SETTINGS_PREFS = "settings";
    public static final String URL_KEY = "rss_url";
    private SharedPreferences m_settings;

    @InjectPresenter
    SettingsPresenter m_settingsPresenter;

    @ViewById(R.id.settings_toolbar)
    Toolbar m_toolbar;

    @ViewById(R.id.rss_url)
    EditText m_rssUrl;

    @AfterViews
    public void init() {
        initToolbar();
        m_settings = getSharedPreferences(SETTINGS_PREFS, MODE_PRIVATE);
        m_rssUrl.setText(m_settings.getString(URL_KEY, ""));
    }

    private void initToolbar() {
        setSupportActionBar(m_toolbar);
        m_toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    @Click(R.id.save_button)
    public void onSaveButtonClick() {
        String currentUrl = m_rssUrl.getText().toString();
        String oldUrl = m_settings.getString(URL_KEY, "");
        if (!currentUrl.equals(oldUrl)) {
            m_settingsPresenter.onRssUrlChange();
        }
    }

    @Override
    public void saveRssUrlChanges() {
        String url = m_rssUrl.getText().toString();
        m_settings.edit().putString(URL_KEY, url).apply();
    }
}
