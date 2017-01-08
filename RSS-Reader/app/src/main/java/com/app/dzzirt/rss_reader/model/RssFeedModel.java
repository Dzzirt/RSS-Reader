package com.app.dzzirt.rss_reader.model;

import android.os.AsyncTask;

import com.app.dzzirt.rss_reader.activity.RssReaderApp;
import com.app.dzzirt.rss_reader.common.RefreshingResult;
import com.app.dzzirt.rss_reader.common.RssItemManager;
import com.app.dzzirt.rss_reader.event.OnUpdateFeedDataEvent;
import com.app.dzzirt.rss_reader.greendao.RssItem;
import com.app.dzzirt.rss_reader.rssparser.RssParser;
import com.app.dzzirt.rss_reader.utils.HtmlUtils;
import com.app.dzzirt.rss_reader.utils.NetUtils;

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

    public void updateRssFeedData(String rssUrl) {
        new AsyncTask<Void, Void, Void>() {

            private RefreshingResult result;

            @Override
            protected Void doInBackground(Void... voids) { //simple algorithm: download -> parse -> fill database
                try {
                    RssItemManager rssItemManager = RssReaderApp.getRssItemManager();
                    File tempXml = getEmptyTempFile();
                    NetUtils.download(
                            new FileOutputStream(tempXml),
                            rssUrl,
                            DOWNLOAD_TIMEOUT);
                    List<RssItem> rssItems = RssParser.parse(new FileInputStream(tempXml));
                    deleteHtmlTags(rssItems); //delete html tags from rss item if any
                    rssItemManager.insertAllWithUpdate(rssItems);
                    result = RefreshingResult.OK;
                } catch (IOException | XmlPullParserException e) {
                    result = RefreshingResult.INVALID_RSS_ERROR;
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                EventBus.getDefault().post(new OnUpdateFeedDataEvent(result));
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
