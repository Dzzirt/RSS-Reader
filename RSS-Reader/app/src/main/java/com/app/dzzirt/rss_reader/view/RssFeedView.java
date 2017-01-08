package com.app.dzzirt.rss_reader.view;

import android.support.v7.widget.RecyclerView;

import com.app.dzzirt.rss_reader.greendao.RssItem;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

/**
 * Created by Dzzirt on 02.01.2017.
 */

public interface RssFeedView extends MvpView {

    @StateStrategyType(value = SkipStrategy.class)
    void updateFeedData(List<RssItem> items);

    @StateStrategyType(value = SkipStrategy.class)
    void resetRefreshing();

    @StateStrategyType(value = SkipStrategy.class)
    void showErrorRefreshingMessage(int messageId);
}
