package com.app.dzzirt.rss_reader.common;

/**
 * Created by Dzzirt on 30.12.2016.
 */

public class RSSItem {
    private String m_title;
    private String m_pubDate;
    private String m_autor;
    private String m_imageUrl;
    private String m_describtion;
    private String m_guid;

    public String getTitle() {
        return m_title;
    }

    public void setTitle(String title) {
        this.m_title = title;
    }

    public String getPubDate() {
        return m_pubDate;
    }

    public void setPubDate(String pubDate) {
        this.m_pubDate = pubDate;
    }

    public String getAutor() {
        return m_autor;
    }

    public void setAutor(String autor) {
        this.m_autor = autor;
    }

    public String getImageUrl() {
        return m_imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.m_imageUrl = imageUrl;
    }

    public String getDescribtion() {
        return m_describtion;
    }

    public void setDescribtion(String describtion) {
        this.m_describtion = describtion;
    }

    public String getGuid() {
        return m_guid;
    }

    public void setGuid(String guid) {
        this.m_guid = guid;
    }
}
