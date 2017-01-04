package com.app.dzzirt.rss_reader.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Dzzirt on 04.01.2017.
 */

public class NetUtils {
    public static InputStream openUrlStream(String link) {
        try {
            URL url = new URL(link);
            return url.openStream();
        } catch (IOException ignored) {
        }
        return null;
    }
}
