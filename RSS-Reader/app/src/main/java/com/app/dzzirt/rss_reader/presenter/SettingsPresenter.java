package com.app.dzzirt.rss_reader.presenter;

import com.app.dzzirt.rss_reader.activity.RssReaderApp;
import com.app.dzzirt.rss_reader.view.SettingsView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

/**
 * Created by Dzzirt on 08.01.2017.
 */
@InjectViewState
public class SettingsPresenter extends MvpPresenter<SettingsView> {

    public void onRssUrlChange() {
        RssReaderApp.getGlobalSession().getRssItemDao().deleteAll();
        getViewState().saveRssUrlChanges();
    }
}
