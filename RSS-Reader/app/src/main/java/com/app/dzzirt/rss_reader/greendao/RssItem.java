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

    @Id(autoincrement = true)
    private Long id;

    @NotNull
    private String imageUrl = "";

    @NotNull
    private String title = "";

    @NotNull
    private String description = "";

    @NotNull
    private String pubDate = "";

    @NotNull
    private String autor = "";

    @NotNull
    private String guid = "";

    @NotNull
    private String link = "";

    @Generated(hash = 1962356016)
    public RssItem(Long id, @NotNull String imageUrl, @NotNull String title,
            @NotNull String description, @NotNull String pubDate,
            @NotNull String autor, @NotNull String guid, @NotNull String link) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.description = description;
        this.pubDate = pubDate;
        this.autor = autor;
        this.guid = guid;
        this.link = link;
    }

    @Generated(hash = 2069234278)
    public RssItem() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    
}
