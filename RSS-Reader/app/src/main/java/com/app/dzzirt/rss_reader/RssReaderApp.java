package com.app.dzzirt.rss_reader;

import android.app.Application;

import com.app.dzzirt.rss_reader.greendao.DaoMaster;
import com.app.dzzirt.rss_reader.greendao.DaoSession;
import com.app.dzzirt.rss_reader.greendao.UpgradingDatabaseOpenHelper;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Dzzirt on 01.01.2017.
 */

public class RssReaderApp extends Application {

    private final String DATABASE_NAME = "rssreader-db";
    private DaoSession m_globalSession;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.OpenHelper helper = new UpgradingDatabaseOpenHelper(this, DATABASE_NAME);
        m_globalSession = new DaoMaster(helper.getWritableDb()).newSession();
    }

    public DaoSession getGlobalSession() {
        return m_globalSession;
    }
}
