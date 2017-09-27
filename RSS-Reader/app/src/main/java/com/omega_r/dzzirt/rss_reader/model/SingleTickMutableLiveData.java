package com.omega_r.dzzirt.rss_reader.model;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

/**
 * Created by nikita on 25.09.17.
 */

public class SingleTickMutableLiveData<T> extends MutableLiveData<T> {

    @Override
    public void observe(LifecycleOwner owner, Observer<T> observer) {
        super.observe(owner, t -> {
            observer.onChanged(t);
            removeObservers(owner);
        });
    }

    @Override
    public void observeForever(Observer<T> observer) {
        super.observeForever(new Observer<T>() {
            @Override
            public void onChanged(@Nullable T t) {
                observer.onChanged(t);
                removeObserver(this);
            }
        });
    }

}
