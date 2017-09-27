package com.omega_r.dzzirt.rss_reader.model;

import android.arch.lifecycle.LiveData;

import com.omega_r.dzzirt.rss_reader.database.FeedItem;

import java.util.List;

/**
 * Created by nikita on 21.09.17.
 */

public interface DatabaseProvider {

    LiveData<List<FeedItem>> getAllFeedItems(boolean reverse);

    LiveData<FeedItem> getFeedItem(long id);

    void updateFeedItems(List<FeedItem> items);

    void deleteAll();

    interface Callback<T> {
        void onDataFetched(T data);

        void onError();
    }
}
