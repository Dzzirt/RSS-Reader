package com.app.dzzirt.rss_reader.view;

import android.support.v7.widget.RecyclerView;

import com.app.dzzirt.rss_reader.common.RSSItem;
import com.arellomobile.mvp.MvpView;

import java.util.List;

/**
 * Created by Dzzirt on 02.01.2017.
 */

public interface RssFeedView extends MvpView {
    void showRssItemInfo(RSSItem item);
    void initFeedList(RecyclerView.Adapter adapter);
    void updateFeedData(List<RSSItem> items);
    void resetRefreshing();
}
