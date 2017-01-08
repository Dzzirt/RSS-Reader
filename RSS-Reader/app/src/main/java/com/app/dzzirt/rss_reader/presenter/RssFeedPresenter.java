package com.app.dzzirt.rss_reader.presenter;

import com.app.dzzirt.rss_reader.R;
import com.app.dzzirt.rss_reader.activity.RssReaderApp;
import com.app.dzzirt.rss_reader.common.RssItemManager;
import com.app.dzzirt.rss_reader.common.RssItemAdapter;
import com.app.dzzirt.rss_reader.event.OnUpdateFeedDataEvent;
import com.app.dzzirt.rss_reader.greendao.RssItem;
import com.app.dzzirt.rss_reader.model.RssFeedModel;
import com.app.dzzirt.rss_reader.view.RssFeedView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.androidannotations.annotations.EBean;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * Created by Dzzirt on 02.01.2017.
 */

@InjectViewState
public class RssFeedPresenter extends MvpPresenter<RssFeedView> {

    public RssFeedPresenter() {
        super();
        EventBus.getDefault().register(this);
    }

    public void onRefresh(String rssUrl) {
        RssFeedModel rssFeedModel = RssReaderApp.getRssFeedModel();
        rssFeedModel.updateRssFeedData(rssUrl);
    }

    @Subscribe
    public void onUpdateRssFeedData(OnUpdateFeedDataEvent event) {
        switch (event.result) {
            case OK:
                getViewState().updateFeedData(getRssItemsList());
                break;
            case DOWNLOAD_ERROR:
                getViewState().showErrorRefreshingMessage(R.string.internet_connection_error);
                break;
            case INVALID_RSS_ERROR:
                getViewState().showErrorRefreshingMessage(R.string.invalid_rss_error);
                break;
        }
        getViewState().resetRefreshing();
    }


    private List<RssItem> getRssItemsList() {
        //get list from model
        RssItemManager RssItemManager = RssReaderApp.getRssItemManager();
        return RssItemManager.getAll();
    }
}
