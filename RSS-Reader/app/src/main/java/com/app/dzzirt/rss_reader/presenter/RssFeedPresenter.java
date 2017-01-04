package com.app.dzzirt.rss_reader.presenter;

import com.app.dzzirt.rss_reader.activity.RssReaderApp;
import com.app.dzzirt.rss_reader.common.RssItemManager;
import com.app.dzzirt.rss_reader.common.RssItemAdapter;
import com.app.dzzirt.rss_reader.greendao.RssItem;
import com.app.dzzirt.rss_reader.view.RssFeedView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.androidannotations.annotations.EBean;

import java.util.List;

/**
 * Created by Dzzirt on 02.01.2017.
 */

@InjectViewState
public class RssFeedPresenter extends MvpPresenter<RssFeedView> {
    public void onInjectFeedList() {
        RssItemAdapter rssItemAdapter = new RssItemAdapter(getRssItemsList());
        rssItemAdapter.setOnItemClickListener(item -> getViewState().showRssItemInfo(item));
        getViewState().initFeedList(rssItemAdapter);
    }

    public void onRefresh() {
        getViewState().updateFeedData(getRssItemsList());
        getViewState().resetRefreshing();
    }


    private List<RssItem> getRssItemsList() {
        //get list from model
        RssItemManager RssItemManager = RssReaderApp.getRssItemManager();
        return RssItemManager.getAll();
    }
}
