package com.omega_r.dzzirt.rss_reader.model;

import android.arch.lifecycle.LiveData;

import com.omega_r.dzzirt.rss_reader.database.FeedItem;

import java.util.List;

/**
 * Created by nikita on 25.09.17.
 */

public class DataRepository {

    private DatabaseProvider mDatabaseProvider;
    private FeedDownloader mFeedDownloader;
    private Preferences mPreferences;

    public DataRepository(DatabaseProvider databaseProvider, FeedDownloader feedDownloader, Preferences preferences) {
        mDatabaseProvider = databaseProvider;
        mFeedDownloader = feedDownloader;
        mPreferences = preferences;
    }

    public LiveData<DownloadResult<List<FeedItem>>> downloadFeedItems(String url) {
        return mFeedDownloader.download(url);
    }

    public LiveData<FeedItem> getFeedItemFromDatabase(long id) {
        return mDatabaseProvider.getFeedItem(id);
    }

    public LiveData<List<FeedItem>> getFeedItemsFromDatabase(boolean reverse) {
        return mDatabaseProvider.getAllFeedItems(reverse);
    }

    public void updateFeedItems(List<FeedItem> feedItems) {
        mDatabaseProvider.updateFeedItems(feedItems);
    }

    public String getFeedUrl() {
        return mPreferences.getFeedUrl();
    }

    public void setFeedUrl(String url) {
        mPreferences.setFeedUrl(url);
    }
}
