package com.app.dzzirt.rss_reader.presenter;

import android.support.v7.widget.RecyclerView;

import com.app.dzzirt.rss_reader.view.RssFeedView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

/**
 * Created by Dzzirt on 02.01.2017.
 */

@InjectViewState
public class RssFeedPresenter extends MvpPresenter<RssFeedView> {
    public void onInjectFeedList(RecyclerView feedList) {
        // init feed list
    }
}
