package com.app.dzzirt.rss_reader.event;

/**
 * Created by Dzzirt on 05.01.2017.
 */

public class OnUpdateFeedDataEvent {
    public boolean isSuccessful;

    public OnUpdateFeedDataEvent(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }
}
