package com.app.dzzirt.rss_reader.rssparser;

import android.util.Xml;

import com.app.dzzirt.rss_reader.greendao.RssItem;
import com.app.dzzirt.rss_reader.utils.DateUtils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Dzzirt on 30.12.2016.
 */

public class RssParser {

    private static final String TITLE = "title";
    private static final String PUBDATE = "pubDate";
    private static final String ENCLOSURE = "enclosure";
    private static final String DESCRIPTION = "description";
    private static final String AUTHOR = "author";
    private static final String LINK = "link";
    private static final String ITEM = "item";
    private static final String GUID = "guid";
    private static final int MAX_DEPTH = 4;

    private static boolean m_isInItemTag = false;
    //Parser includes rss structure validation, but not values validation
    public static List<RssItem> parse(InputStream inputStream) throws IOException
            , XmlPullParserException
            , ParseException {
        ArrayList<RssItem> list = new ArrayList<>();
        InputStreamReader reader = null;
        try {
            XmlPullParser parser = Xml.newPullParser();
            reader = new InputStreamReader(inputStream);
            parser.setInput(reader);
            RssItem tempItem = null;
            while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
                parser.next();
                if (parser.getName() != null) {
                    String tag = parser.getName();
                    if (tag.equals(ITEM) && isValidItemTag(parser)) {
                        if (parser.getEventType() == XmlPullParser.START_TAG) {
                            m_isInItemTag = true;
                            tempItem = new RssItem(); // creating temp item when we just enter <item>
                        } else {
                            list.add(tempItem); // adding temp item to list when we on </item> tag
                            m_isInItemTag = false;
                        }
                    } else if (m_isInItemTag //parsed tag should be in <item></item>
                            && parser.getEventType() != XmlPullParser.END_TAG //skip closing tags
                            && parser.getDepth() <= MAX_DEPTH) { // skip deep nested tags (Idiot Protection)
                        switch (tag) {
                            case TITLE:
                                tempItem.setTitle(readText(parser));
                                break;
                            case DESCRIPTION:
                                tempItem.setDescription(readText(parser));
                                break;
                            case AUTHOR:
                                tempItem.setAutor(readText(parser));
                                break;
                            case ENCLOSURE:
                                String url = readImageUrl(parser);
                                if (!url.isEmpty()) { // We get img from last <enclosure>
                                    tempItem.setImageUrl(url);
                                }
                                break;
                            case PUBDATE:
                                String pubdate = readText(parser);
                                tempItem.setPubDate(DateUtils.parseRssDate(pubdate));
                                break;
                            case LINK:
                                tempItem.setLink(readText(parser));
                                break;
                            case GUID:
                                tempItem.setGuid(readText(parser));
                                break;
                        }
                    }
                }
            }
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return list;
    }
    //Protection from nested <item></item> (Idiot Protection!)
    private static boolean isValidItemTag(XmlPullParser parser) throws XmlPullParserException {
        if (parser.getEventType() == XmlPullParser.START_TAG) {
            return !m_isInItemTag;
        } else if (parser.getEventType() == XmlPullParser.END_TAG) {
            return m_isInItemTag;
        }
        return false;
    }

    //Parsing img url
    private static String readImageUrl(XmlPullParser parser) {
        String result = "";
        int attributeCount = parser.getAttributeCount();
        if (attributeCount > 1 && attributeCount <= 3) {
            boolean isCorrectType = parser.getAttributeValue(attributeCount - 1).contains("image/");
            result = isCorrectType ? parser.getAttributeValue(0) : "";
        }
        return result;
    }

    private static String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

}
