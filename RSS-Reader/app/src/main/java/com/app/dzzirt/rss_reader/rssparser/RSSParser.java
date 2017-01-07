package com.app.dzzirt.rss_reader.rssparser;

import android.util.Xml;

import com.app.dzzirt.rss_reader.greendao.RssItem;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
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

    private static String sample = "<?xml version=\"1.0\" encoding=\"utf-8\" ?> <rss version=\"2.0\" xml:base=\"http://rusvesna.su/\" xmlns:atom=\"http://www.w3.org/2005/Atom\" xmlns:yandex=\"http://news.yandex.ru\" xmlns:media=\"http://search.yahoo.com/mrss/\"> " +
            "<channel> " +
            "<title>kek весна</title>" +
            " <description>Русская весна</description>" +
            " <link>http://rusvesna.su/</link>" +
            " <atom:link rel=\"self\" href=\"http://rusvesna.su/rss.xml\" />\n" +
            " <language>ru-ru</language>" +
            " <pubDate>Fri, 06 Jan 2017 12:11:58 +0300</pubDate>" +
            " <lastBuildDate>Fri, 06 Jan 2017 12:28:09 +0300</lastBuildDate>" +
                " <item> " +
                    "<title>vcdgfdfg авыафа</title>" +
                    " <link>http://rusvesna.su/news/1483693918<kek></kek></link>" +
                    " <description>Минобороны РФ приступило к сокращению группировки Вооруженных сил в САР.</description>" +
                    " <category>Новости</category>" +
                    " <enclosure url=\"http://rusvesna.su/sites/default/files/styles/top_news/public/admiral_kuznecov_avianosec.jpg?itok=z0HAOFRv\" length=\"5793\" type=\"image/jpeg\" />" +
                    " <guid isPermaLink=\"false\">http://rusvesna.su/news/1483693918</guid>" +
                    " <pubDate>Fri, 06 Jan 2017 12:11:58 +0300</pubDate>" +
                    " <source url=\"http://rusvesna.su/rss.xml\">Русская весна</source>" +
                "</item>" +
            " <item> <title>Трамп подарит мексиканцам стену на деньги США</title>" +
            " <link>http://rusvesna.su/news/1483692808</link>" +
            " <description>Избранный президент США отказался от идеи отгородиться стеной от Мексики за ее счет, сообщают СМИ.</description>\n" +
            " <category>Новости</category>" +
            " <enclosure url=\"http://rusvesna.su/sites/default/files/styles/top_news/public/tramp_31.jpg?itok=CnWMKivD\" length=\"6049\" type=\"image/jpeg\" />\n" +
            " <guid isPermaLink=\"false\">http://rusvesna.su/news/1483692808</guid>" +
            " <pubDate>Fri, 06 Jan 2017 11:53:28 +0300</pubDate>" +
            " <source url=\"http://rusvesna.su/rss.xml\">Русская весна</source>" +
            "</item>";

    public static List<RssItem> parse(InputStream inputStream) {
        ArrayList<RssItem> list = new ArrayList<>();
        InputStreamReader reader = null;

        try {
            XmlPullParser parser = Xml.newPullParser();
            reader = new InputStreamReader(inputStream);
//            reader = new InputStreamReader(new ByteArrayInputStream(sample.getBytes(Charset.forName("UTF-8"))));
            parser.setInput(reader);
            RssItem tempItem = null;
            while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
                parser.next();
                if (parser.getName() != null) {
                    String tag = parser.getName();
                    if (tag.equals(ITEM) && isValidItemTag(parser)) {
                        if (parser.getEventType() == XmlPullParser.START_TAG) {
                            m_isInItemTag = true;
                            tempItem = new RssItem();
                        } else {
                            list.add(tempItem);
                            m_isInItemTag = false;
                        }
                    } else if (m_isInItemTag
                            && parser.getEventType() != XmlPullParser.END_TAG
                            && parser.getDepth() <= MAX_DEPTH) {
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
                                if (!url.isEmpty()) {
                                    tempItem.setImageUrl(url);
                                }
                                break;
                            case PUBDATE:
                                tempItem.setPubDate(readText(parser));
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
        } catch (XmlPullParserException | IOException e) {
            list.clear();
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

    private static boolean isValidItemTag(XmlPullParser parser) throws XmlPullParserException {
        if (parser.getEventType() == XmlPullParser.START_TAG) {
            return !m_isInItemTag;
        } else if (parser.getEventType() == XmlPullParser.END_TAG) {
            return m_isInItemTag;
        }
        return false;
    }

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
