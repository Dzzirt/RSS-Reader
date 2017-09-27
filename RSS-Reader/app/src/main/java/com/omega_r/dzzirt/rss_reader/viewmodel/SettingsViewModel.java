package com.omega_r.dzzirt.rss_reader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.omega_r.dzzirt.rss_reader.RssReaderApp;
import com.omega_r.dzzirt.rss_reader.model.DataRepository;

/**
 * Created by nikita on 23.09.17.
 */

public class SettingsViewModel extends AndroidViewModel {

    private DataRepository mDataRepository;

    private MutableLiveData<String> mUrlObservable = new MutableLiveData<>();

    public SettingsViewModel(Application application) {
        super(application);
        mDataRepository = ((RssReaderApp) application).getDataRepository();

        init();
    }

    private void init() {
        mUrlObservable.setValue(mDataRepository.getFeedUrl());
    }

    public void onSave(String url) {
        if (!url.equals(mDataRepository.getFeedUrl())) {
            mDataRepository.setFeedUrl(url);
        }
    }

    public LiveData<String> getUrlObservable() {
        return mUrlObservable;
    }
}
