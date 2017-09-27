package com.omega_r.dzzirt.rss_reader.model;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.omega_r.dzzirt.rss_reader.database.FeedItem;
import com.omega_r.dzzirt.rss_reader.rssparser.RSSParser;
import com.omega_r.dzzirt.rss_reader.utils.FileUtils;
import com.omega_r.dzzirt.rss_reader.utils.HtmlUtils;
import com.omega_r.dzzirt.rss_reader.utils.NetUtils;

import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by Dzzirt on 04.01.2017.
 */
public class FeedDownloaderImpl implements FeedDownloader {

    private final int DOWNLOAD_TIMEOUT = 2000;

    public enum Error {
        DOWNLOAD_ERROR,
        RSS_PARSE_ERROR
    }

    public LiveData<DownloadResult<List<FeedItem>>> download(String rssUrl) {
        SingleTickMutableLiveData<DownloadResult<List<FeedItem>>> mResultLiveData = new SingleTickMutableLiveData<>();

        startDownload(rssUrl, mResultLiveData);

        return mResultLiveData;
    }

    private void startDownload(final String rssUrl, final SingleTickMutableLiveData<DownloadResult<List<FeedItem>>> mResultLiveData) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                DownloadResult<List<FeedItem>> downloadResult = new DownloadResult<>();

                try {
                    File tempXml = FileUtils.getTempFile();

                    NetUtils.download(
                            new FileOutputStream(tempXml),
                            rssUrl,
                            DOWNLOAD_TIMEOUT);

                    List<FeedItem> feedItems = RSSParser.parse(new FileInputStream(tempXml));

                    deleteHtmlTags(feedItems);

                    downloadResult.setData(feedItems);
                } catch (IOException e) {
                    downloadResult.setError(Error.DOWNLOAD_ERROR);
                } catch (ParseException | XmlPullParserException e) {
                    downloadResult.setError(Error.RSS_PARSE_ERROR);
                }

                mResultLiveData.postValue(downloadResult);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }
        }.execute();
    }

    private void deleteHtmlTags(List<FeedItem> feedItems) {
        for (FeedItem feedItem : feedItems) {
            feedItem.setDescription(HtmlUtils.extractText(feedItem.getDescription()));
            feedItem.setTitle(HtmlUtils.extractText(feedItem.getTitle()));
            feedItem.setAuthor(HtmlUtils.extractText(feedItem.getAuthor()));
        }
    }


}
