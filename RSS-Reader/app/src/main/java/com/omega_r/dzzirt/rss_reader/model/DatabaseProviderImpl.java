package com.omega_r.dzzirt.rss_reader.model;

import android.arch.lifecycle.LiveData;


import com.omega_r.dzzirt.rss_reader.database.DatabaseAsyncTask;
import com.omega_r.dzzirt.rss_reader.database.FeedItem;
import com.omega_r.dzzirt.rss_reader.database.FeedItemDao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Dzzirt on 03.01.2017.
 */

public class DatabaseProviderImpl implements DatabaseProvider {

    private FeedItemDao mFeedItemDao;

    public DatabaseProviderImpl(FeedItemDao dao) {
        mFeedItemDao = dao;
    }

    @Override
    public LiveData<List<FeedItem>> getAllFeedItems(boolean reverse) {
        return reverse ? mFeedItemDao.getAllReversed() : mFeedItemDao.getAll();
        /*new DatabaseAsyncTask<LiveData<List<FeedItem>>>(callback) {
            @Override
            protected LiveData<List<FeedItem>> runTask() throws SQLException {
                return reverse ? mFeedItemDao.getAllReversed() : mFeedItemDao.getAll();
            }
        };*/
    }

    @Override
    public LiveData<FeedItem> getFeedItem(long id) {
        return mFeedItemDao.getItem(id);
        /*new DatabaseAsyncTask<LiveData<FeedItem>>(callback) {
            @Override
            protected LiveData<FeedItem> runTask() throws SQLException {
                return mFeedItemDao.getItem(id);
            }
        };*/
    }

    @Override
    public void updateFeedItems(List<FeedItem> items) {
        new DatabaseAsyncTask<Void>(null) {
            @Override
            protected Void runTask() throws SQLException {
                for (FeedItem updatingItem : items) {
                    FeedItem updatedItem = mFeedItemDao.getItemByTitle(updatingItem.getTitle());
                    if (updatedItem != null) {
                        updatingItem.setId(updatedItem.getId());
                        mFeedItemDao.update(updatingItem);
                    } else if (updatingItem.isValid()) {
                        mFeedItemDao.insert(updatingItem);
                    }
                }
                return null;
            }
        };
    }

    @Override
    public void deleteAll() {
        new DatabaseAsyncTask<Void>(null) {
            @Override
            protected Void runTask() throws SQLException {
                mFeedItemDao.deleteAll();
                return null;
            }
        };
    }
}
