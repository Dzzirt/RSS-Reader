package com.omega_r.dzzirt.rss_reader.model;

/**
 * Created by nikita on 25.09.17.
 */

public class DownloadResult<T> {

    private T mData;
    private FeedDownloaderImpl.Error mError;

    public DownloadResult() {
    }

    public DownloadResult(T data, FeedDownloaderImpl.Error error) {
        mData = data;
        mError = error;
    }

    public T getData() {
        return mData;
    }

    public void setData(T data) {
        mData = data;
    }

    public FeedDownloaderImpl.Error getError() {
        return mError;
    }

    public void setError(FeedDownloaderImpl.Error error) {
        mError = error;
    }
}
