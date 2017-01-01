package com.app.dzzirt.rss_reader.rssparser;

import com.app.dzzirt.rss_reader.RSSItem;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
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

public class RSSParser {

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


    public static final String sample =
            "<rss xmlns:yandex=\"http://news.yandex.ru\" xmlns:media=\"http://search.yahoo.com/mrss/\" version=\"2.0\">\n" +
            "<channel>\n" +
            "<title>Новости - Новости дня</title>\n" +
            "<link>http://www.topnews24.ru/</link>\n" +
            "<language>ru</language>\n" +
            "<description>Новости - Новости дня</description>\n" +
            "<yandex:logo>http://www.topnews24.ru/yandexlogo.gif</yandex:logo>\n" +
            "<yandex:logo type=\"square\">http://www.topnews24.ru/topnews24_square_logo.png</yandex:logo>\n" +
            "<image>\n" +
            "<url>http://www.topnews24.ru/yandexlogo.gif</url>\n" +
            "<title>Новости - Новости дня</title>\n" +
            "<link>http://www.topnews24.ru/</link>\n" +
            "</image>\n" +
            "<generator>Topnews24.ru</generator>\n" +
            "<item>\n" +
            "<title>\n" +
            "В крае зафиксирован первый случай отравления «Боярышником»\n" +
            "</title>\n" +
            "<link>\n" +
            "http://www.topnews24.ru/news/krasnoyarsk/86233-v-krae-zafiksirovan-pervyy-sluchay-otravleniya-boyaryshnikom.html\n" +
            "</link>\n" +
            "<description>\n" +
            "Как сообщают городские СМИ, в больницу доставили жителя Канска. Сам пострадавший пояснил, что он употреблял лосьон целую неделю.\n" +
            "</description>\n" +
            "<category>Новости Красноярска</category>\n" +
            "<pubDate>Fri, 30 Dec 2016 08:51:45 +0300</pubDate>\n" +
            "<yandex:full-text>\n" +
            "В Красноярском крае зафиксирован первый случай отравления «Боярышником»: как сообщают городские СМИ, в больницу доставили жителя Канска. Сам пострадавший пояснил, что он употреблял лосьон целую неделю. Сейчас состояние мужчины после отравления метиловым спиртом оценивается как стабильно тяжелое, уточняет агентство Сибновости. Напомним, в этом месяце массовое отравление косметическим средством «Боярышник» произошло в Иркутске. По последним данным, пострадали 123 человека, 76 из них скончались.\n" +
            "</yandex:full-text>\n" +
            "</item>";

    private static Map<String, String> parseAttributes(String[] rawAttrs) {
        HashMap<String, String> attributesMap = new HashMap<>();
        for (int i = 1; i < rawAttrs.length; i++) {
            String[] pair = rawAttrs[i].split("=");
            attributesMap.put(pair[0], pair[1]);
        }
        return attributesMap;
    }

    public static List<RSSItem> parse(URL url) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        new ByteArrayInputStream(sample.getBytes(Charset.defaultCharset()))));
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            String[] split = line.split("<");
            for (String part : split) {
                String[] splittedPart = part.split(">");

            }
            System.out.println();
        }
        return new LinkedList<>();
    }
}
