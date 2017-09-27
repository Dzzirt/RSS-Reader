package com.omega_r.dzzirt.rss_reader.model;

import android.arch.lifecycle.LiveData;

import com.omega_r.dzzirt.rss_reader.database.FeedItem;

import java.util.List;

/**
 * Created by nikita on 23.09.17.
 */

public interface FeedDownloader {

    LiveData<DownloadResult<List<FeedItem>>> download(String url);

}
