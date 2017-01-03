package com.app.dzzirt.rss_reader.presenter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.dzzirt.rss_reader.R;
import com.app.dzzirt.rss_reader.common.OnItemClickListener;
import com.app.dzzirt.rss_reader.common.RSSItem;
import com.app.dzzirt.rss_reader.common.RssItemAdapter;
import com.app.dzzirt.rss_reader.view.RssFeedView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
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

    private List<RSSItem> getRssItemsList() {
        //get list from model
        ArrayList<RSSItem> rssItems = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            rssItems.add(new RSSItem() {
                {
                    setAutor("Nikita");
                    setDescribtion("Describtion");
                    setGuid("42");
                    setImageUri("http://www.popoptiq.com/wp-content/uploads/2012/11/Futurama.jpeg");
                    setPubDate("03.01.2017");
                    setTitle("Futurama");
                }
            });
        }
        return rssItems;
    }
}
