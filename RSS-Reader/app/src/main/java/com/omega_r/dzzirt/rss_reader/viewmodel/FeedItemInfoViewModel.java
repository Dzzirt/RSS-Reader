package com.omega_r.dzzirt.rss_reader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;

import com.omega_r.dzzirt.rss_reader.RssReaderApp;
import com.omega_r.dzzirt.rss_reader.model.DataRepository;
import com.omega_r.dzzirt.rss_reader.database.FeedItem;

/**
 * Created by nikita on 23.09.17.
 */

public class FeedItemInfoViewModel extends AndroidViewModel {

    private DataRepository mDataRepository;

    private LiveData<FeedItem> mItemObservable;
    private MutableLiveData<Long> mItemIdObservable = new MutableLiveData<>();

    public FeedItemInfoViewModel(Application application) {
        super(application);
        mDataRepository = ((RssReaderApp) application).getDataRepository();
        mItemObservable = Transformations.switchMap(mItemIdObservable, id -> mDataRepository.getFeedItemFromDatabase(id));
    }

    public LiveData<FeedItem> getItemObservable() {
        return mItemObservable;
    }

    public void setItemId(long itemId) {
        mItemIdObservable.setValue(itemId);
    }
}
