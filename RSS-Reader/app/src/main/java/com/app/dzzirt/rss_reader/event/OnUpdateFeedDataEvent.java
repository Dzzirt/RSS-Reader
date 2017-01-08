package com.app.dzzirt.rss_reader.event;

import com.app.dzzirt.rss_reader.common.RefreshingResult;

/**
 * Created by Dzzirt on 05.01.2017.
 */

public class OnUpdateFeedDataEvent {
    public RefreshingResult result;

    public OnUpdateFeedDataEvent(RefreshingResult result) {
        this.result = result;
    }
}
