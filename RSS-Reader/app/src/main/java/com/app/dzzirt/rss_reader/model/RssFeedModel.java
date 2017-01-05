package com.app.dzzirt.rss_reader.model;

import android.os.AsyncTask;

import com.app.dzzirt.rss_reader.activity.RssReaderApp;
import com.app.dzzirt.rss_reader.common.RssItemManager;
import com.app.dzzirt.rss_reader.event.OnUpdateFeedDataEvent;
import com.app.dzzirt.rss_reader.rssparser.RssParser;
import com.app.dzzirt.rss_reader.utils.NetUtils;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Dzzirt on 04.01.2017.
 */
public class RssFeedModel {

    private final int DOWNLOAD_TIMEOUT = 2000;


    public void updateRssFeedData() {
        new AsyncTask<Void, Void, Void>()
        {
            private boolean isSuccessful = false;

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    RssItemManager rssItemManager = RssReaderApp.getRssItemManager();
                    File tempXml = RssReaderApp.getFileManager().getInternalStorageFile("tempXml");
                    tempXml.delete();
                    tempXml.createNewFile();
                    boolean isDownloaded = NetUtils.download(
                            new FileOutputStream(tempXml),
                            "http://www.20minutos.es/rss/",
                            DOWNLOAD_TIMEOUT);
                    if (isDownloaded) {
                        rssItemManager.insertAllWithUpdate(RssParser.parse(new FileInputStream(tempXml)));
                        isSuccessful = true;
                    }
                } catch (IOException ignored) {}
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                EventBus.getDefault().post(new OnUpdateFeedDataEvent(isSuccessful));
            }
        }.execute();
    }
}
