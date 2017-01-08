package com.app.dzzirt.rss_reader.presenter;

import com.app.dzzirt.rss_reader.view.RssItemInfoView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

/**
 * Created by Dzzirt on 08.01.2017.
 */
@InjectViewState
public class RssItemInfoPresenter extends MvpPresenter<RssItemInfoView> {

    public void onRssItemClick(Long rssItemId) {
        getViewState().showRssItemInfo(rssItemId);
    }
}
