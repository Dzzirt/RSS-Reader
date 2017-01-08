package com.app.dzzirt.rss_reader.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Dzzirt on 08.01.2017.
 */

public interface SettingsView extends MvpView {
    @StateStrategyType(value = SkipStrategy.class)
    void saveRssUrlChanges();
}
