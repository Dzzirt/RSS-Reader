package com.app.dzzirt.rss_reader.view;

import android.support.v7.widget.RecyclerView;

import com.app.dzzirt.rss_reader.common.RSSItem;
import com.arellomobile.mvp.MvpView;

/**
 * Created by Dzzirt on 02.01.2017.
 */

public interface RssFeedView extends MvpView {
    void showRssItemInfo(RSSItem item);
    void initFeedList(RecyclerView.Adapter adapter);
}
