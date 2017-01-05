package com.app.dzzirt.rss_reader.rssparser;

import com.app.dzzirt.rss_reader.greendao.RssItem;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * Created by Dzzirt on 30.12.2016.
 */

public class RssParser {

    private static final String TITLE = "title";
    private static final String PUBDATE = "pubdate";
    private static final String ENCLOSURE = "enclosure";
    private static final String DESCRIBTION = "describtion";
    private static final String AUTOR = "autor";

    private static boolean m_isIgnore;

    private enum State {
        TAG,
        BODY,
        OUT
    }

    public static List<RssItem> parse(InputStream inputStream) throws IOException {
        return new LinkedList<>();
    }
}
