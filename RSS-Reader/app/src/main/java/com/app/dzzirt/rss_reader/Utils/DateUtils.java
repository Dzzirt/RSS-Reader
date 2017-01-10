package com.app.dzzirt.rss_reader.utils;

import android.content.Context;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dzzirt on 03.01.2017.
 */

public class DateUtils {
    public static final String RSS_DATE_PATTERN_WITH_NUMERIC_TIMEZONE
            = "EEE, dd MMM yyyy HH:mm:ss Z";
    public static final String RSS_DATE_PATTERN_WITH_TIMEZONE_ABBREVIATION
            = "EEE, dd MMM yyyy HH:mm:ss zzz";

    public static Date parseRssDate(String source) throws ParseException {
        SimpleDateFormat withNumTimezon = new SimpleDateFormat(RSS_DATE_PATTERN_WITH_NUMERIC_TIMEZONE);
        SimpleDateFormat withAbbrTimezone = new SimpleDateFormat(RSS_DATE_PATTERN_WITH_TIMEZONE_ABBREVIATION);
        Date output;
        try {
            output = withAbbrTimezone.parse(source);
        } catch (ParseException e) {
            output = withNumTimezon.parse(source);
        }
        return output;
    }

    public static String asRegionalDateString(Date date, Context context) {
        DateFormat dateFormat = android.text.format.DateFormat.getLongDateFormat(context);
        DateFormat timeFormat = android.text.format.DateFormat.getTimeFormat(context);
        return dateFormat.format(date) + " " + timeFormat.format(date);
    }


}
