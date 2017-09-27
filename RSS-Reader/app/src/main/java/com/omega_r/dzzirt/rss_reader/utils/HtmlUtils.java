package com.omega_r.dzzirt.rss_reader.utils;

import android.text.Html;
import android.text.Spanned;

import org.jsoup.Jsoup;

/**
 * Created by Dzzirt on 07.01.2017.
 */

public class HtmlUtils {
    public static String extractText(String html) {
        return Jsoup.parse(html).text();
    }
}
