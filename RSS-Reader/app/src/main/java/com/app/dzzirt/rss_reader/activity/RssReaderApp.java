package com.app.dzzirt.rss_reader.activity;

import android.app.Application;

import com.app.dzzirt.rss_reader.common.FileManager;
import com.app.dzzirt.rss_reader.common.RssItemManager;
import com.app.dzzirt.rss_reader.greendao.DaoMaster;
import com.app.dzzirt.rss_reader.greendao.DaoSession;
import com.app.dzzirt.rss_reader.greendao.RssItem;
import com.app.dzzirt.rss_reader.greendao.UpgradingDatabaseOpenHelper;
import com.app.dzzirt.rss_reader.model.RssFeedModel;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Dzzirt on 01.01.2017.
 */

public class RssReaderApp extends Application {

    private final String DATABASE_NAME = "rssreader-db";
    private static DaoSession m_globalSession;
    private static RssItemManager m_rssItemManager;
    private static FileManager m_fileManager;
    private static RssFeedModel m_rssFeedModel;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        DaoMaster.OpenHelper helper = new UpgradingDatabaseOpenHelper(this, DATABASE_NAME);
        m_globalSession = new DaoMaster(helper.getWritableDb()).newSession();
        m_rssItemManager = new RssItemManager(m_globalSession.getRssItemDao());
        m_fileManager = new FileManager(this);
        m_rssFeedModel = new RssFeedModel();
    }


    public static DaoSession getGlobalSession() {
        return m_globalSession;
    }

    public static RssItemManager getRssItemManager() {
        return m_rssItemManager;
    }

    public static FileManager getFileManager() {
        return m_fileManager;
    }

    public static RssFeedModel getRssFeedModel() {
        return m_rssFeedModel;
    }
}
