package com.omega_r.dzzirt.rss_reader.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by nikita on 21.09.17.
 */

@Dao
public interface FeedItemDao {
    @Query("SELECT * FROM rss_items")
    LiveData<List<FeedItem>> getAll();

    @Query("SELECT * FROM rss_items ORDER BY date DESC")
    LiveData<List<FeedItem>> getAllReversed();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(FeedItem... items);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FeedItem... items);

    @Query("SELECT * FROM rss_items WHERE id = :id")
    LiveData<FeedItem> getItem(long id);

    @Query("SELECT * FROM rss_items WHERE title = :title")
    FeedItem getItemByTitle(String title);

    @Query("DELETE FROM rss_items")
    void deleteAll();
}
