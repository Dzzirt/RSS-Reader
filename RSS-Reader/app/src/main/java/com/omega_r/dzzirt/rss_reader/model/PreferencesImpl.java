package com.omega_r.dzzirt.rss_reader.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;

/**
 * Created by nikita on 23.09.17.
 */

public class PreferencesImpl implements Preferences {

    public static final String PREFERENCES_NAME = "settings";
    public static final String URL_FEED = "rss_url";

    private SharedPreferences mPreferences;

    public PreferencesImpl(Context context) {
        mPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public String getFeedUrl() {
        return mPreferences.getString(URL_FEED, "");
    }

    public void setFeedUrl(String url) {
        mPreferences.edit().putString(URL_FEED, url).apply();
    }
}
