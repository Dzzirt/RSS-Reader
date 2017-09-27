package com.omega_r.dzzirt.rss_reader.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

/**
 * Created by nikita on 21.09.17.
 */

@Database(entities = {FeedItem.class}, version = 1)
@TypeConverters({DateConverter.class})
public abstract class RssDatabase extends RoomDatabase {
    public abstract FeedItemDao getRssItemDao();
}
