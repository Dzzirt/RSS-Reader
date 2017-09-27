package com.omega_r.dzzirt.rss_reader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.app.dzzirt.rss_reader.R;
import com.omega_r.dzzirt.rss_reader.RssReaderApp;
import com.omega_r.dzzirt.rss_reader.model.DataRepository;
import com.omega_r.dzzirt.rss_reader.database.FeedItem;
import com.omega_r.dzzirt.rss_reader.model.DownloadResult;
import com.omega_r.dzzirt.rss_reader.model.FeedDownloaderImpl;

import java.util.List;

/**
 * Created by nikita on 22.09.17.
 */

public class FeedViewModel extends AndroidViewModel {

    private DataRepository mDataRepository;

    private LiveData<List<FeedItem>> mFeedObservable;
    private MutableLiveData<String> mFeedErrorObservable = new MutableLiveData<>();
    private MutableLiveData<FeedItem> mSelectedItemObservable = new MutableLiveData<>();

    public FeedViewModel(Application application) {
        super(application);
        mDataRepository = ((RssReaderApp) application).getDataRepository();

        init();
    }

    private void init() {
        mFeedObservable = mDataRepository.getFeedItemsFromDatabase(true);
    }

    public void onRefresh() {
        String feedUrl = mDataRepository.getFeedUrl();
        LiveData<DownloadResult<List<FeedItem>>> resultLiveData = mDataRepository.downloadFeedItems(feedUrl);
        resultLiveData.observeForever(downloadResult -> {
            if (downloadResult.getError() != null) {
                onFeedDownloadError(downloadResult.getError());
            } else {
                onFeedDownloadSuccess(downloadResult.getData());
            }
        });
    }

    public LiveData<List<FeedItem>> getFeedObservable() {
        return mFeedObservable;
    }

    public LiveData<String> getFeedErrorObservable() {
        return mFeedErrorObservable;
    }

    public LiveData<FeedItem> getSelectedItemObservable() {
        return mSelectedItemObservable;
    }


    private void onFeedDownloadSuccess(List<FeedItem> items) {
        mDataRepository.updateFeedItems(items);
    }


    private void onFeedDownloadError(FeedDownloaderImpl.Error error) {
        switch (error) {
            case DOWNLOAD_ERROR:
                mFeedErrorObservable.postValue(getApplication().getString(R.string.internet_connection_error));
                break;
            case RSS_PARSE_ERROR:
                mFeedErrorObservable.postValue(getApplication().getString(R.string.invalid_rss_error));
                break;
        }
    }

    public void onFeedItemClick(FeedItem item) {
        mSelectedItemObservable.setValue(item);
    }
}
