package com.app.dzzirt.rss_reader.model;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.app.dzzirt.rss_reader.activity.RssReaderApp;
import com.app.dzzirt.rss_reader.common.RssItemManager;
import com.app.dzzirt.rss_reader.event.OnUpdateFeedDataEvent;
import com.app.dzzirt.rss_reader.greendao.RssItem;
import com.app.dzzirt.rss_reader.rssparser.RssParser;
import com.app.dzzirt.rss_reader.utils.DateUtils;
import com.app.dzzirt.rss_reader.utils.HtmlUtils;
import com.app.dzzirt.rss_reader.utils.NetUtils;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.greenrobot.eventbus.EventBus;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Dzzirt on 04.01.2017.
 */
public class RssFeedModel {

    private final int DOWNLOAD_TIMEOUT = 2000;


    public void updateRssFeedData() {
        new AsyncTask<Void, Void, Void>() {
            private boolean isSuccessful = false;

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    RssItemManager rssItemManager = RssReaderApp.getRssItemManager();
                    File tempXml = getEmptyTempFile();
//                    rssItemManager.deleteAll();
                    boolean isDownloaded = NetUtils.download(
                            new FileOutputStream(tempXml),
                            "http://receptculinar.ru/blog/rss",
                            DOWNLOAD_TIMEOUT);
                    if (isDownloaded) {
                        List<RssItem> rssItems = RssParser.parse(new FileInputStream(tempXml));
                        deleteHtmlTags(rssItems);
                        rssItemManager.insertAllWithUpdate(rssItems);
                        isSuccessful = true;
                    }
                } catch (IOException ignored) {
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                EventBus.getDefault().post(new OnUpdateFeedDataEvent(isSuccessful));
            }
        }.execute();
    }

    private void deleteHtmlTags(List<RssItem> rssItems) {
        for (RssItem rssItem : rssItems) {
            rssItem.setDescription(HtmlUtils.extractText(rssItem.getDescription()));
            rssItem.setTitle(HtmlUtils.extractText(rssItem.getTitle()));
            rssItem.setAutor(HtmlUtils.extractText(rssItem.getAutor()));
        }
    }

    private File getEmptyTempFile() throws IOException {
        File tempXml = RssReaderApp.getFileManager().getInternalStorageFile("tempXml");
        tempXml.delete();
        tempXml.createNewFile();
        return tempXml;
    }


}
