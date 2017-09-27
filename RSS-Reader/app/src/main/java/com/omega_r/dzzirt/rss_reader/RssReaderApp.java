package com.omega_r.dzzirt.rss_reader;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.omega_r.dzzirt.rss_reader.model.DataRepository;
import com.omega_r.dzzirt.rss_reader.model.DatabaseProviderImpl;
import com.omega_r.dzzirt.rss_reader.database.RssDatabase;
import com.omega_r.dzzirt.rss_reader.model.FeedDownloader;
import com.omega_r.dzzirt.rss_reader.model.FeedDownloaderImpl;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.omega_r.dzzirt.rss_reader.model.PreferencesImpl;

/**
 * Created by Dzzirt on 01.01.2017.
 */

public class RssReaderApp extends Application {

    private final String DATABASE_NAME = "rss-db";

    private DataRepository mDataRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        RssDatabase database = Room.databaseBuilder(this, RssDatabase.class, DATABASE_NAME).build();
        DatabaseProviderImpl databaseProvider = new DatabaseProviderImpl(database.getRssItemDao());
        FeedDownloaderImpl feedDownloader = new FeedDownloaderImpl();
        PreferencesImpl preferences = new PreferencesImpl(getApplicationContext());
        mDataRepository = new DataRepository(databaseProvider, feedDownloader, preferences);
    }

    public DataRepository getDataRepository() {
        return mDataRepository;
    }
}
