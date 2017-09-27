package com.omega_r.dzzirt.rss_reader.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;


/**
 * Created by Dzzirt on 01.01.2017.
 */
@Entity(tableName = FeedItem.RSS_TABLE_NAME)
public class FeedItem {

    static final String RSS_TABLE_NAME = "rss_items";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long mId;

    @ColumnInfo(name = "image_url")
    private String mImageUrl = "";

    @ColumnInfo(name = "title")
    private String mTitle = "";

    @ColumnInfo(name = "description")
    private String mDescription = "";

    @ColumnInfo(name = "date")
    private Date mDate;

    @ColumnInfo(name = "author")
    private String mAuthor = "";

    @ColumnInfo(name = "guid")
    private String mGuid = "";

    @ColumnInfo(name = "link" )
    private String mUrl = "";

    public void setId(long id) {
        mId = id;
    }

    public long getId() {
        return mId;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.mImageUrl = imageUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        this.mDate = date;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        this.mAuthor = author;
    }

    public String getGuid() {
        return mGuid;
    }

    public void setGuid(String guid) {
        this.mGuid = guid;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    public boolean isValid() {
        return !(mTitle.isEmpty() && mDescription.isEmpty());
    }
}
