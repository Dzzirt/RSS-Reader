package com.app.dzzirt.rss_reader.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by Dzzirt on 01.01.2017.
 */

@Entity
public class RssItem {
    @Id
    private long id;

    @NotNull
    private String imageUrl = "";

    @NotNull
    private String title = "";

    @NotNull
    private String describtion = "";

    @NotNull
    private String pubDate = "";

    @NotNull
    private String autor = "";

    @NotNull
    private String guid = "";

    @Generated(hash = 261252799)
    public RssItem(long id, @NotNull String imageUrl, @NotNull String title,
            @NotNull String describtion, @NotNull String pubDate,
            @NotNull String autor, @NotNull String guid) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.describtion = describtion;
        this.pubDate = pubDate;
        this.autor = autor;
        this.guid = guid;
    }

    @Generated(hash = 2069234278)
    public RssItem() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribtion() {
        return this.describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    public String getPubDate() {
        return this.pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getAutor() {
        return this.autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGuid() {
        return this.guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
    

}
