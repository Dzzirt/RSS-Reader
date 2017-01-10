package com.app.dzzirt.rss_reader.common;

import com.app.dzzirt.rss_reader.utils.DateUtils;
import com.app.dzzirt.rss_reader.greendao.RssItem;
import com.app.dzzirt.rss_reader.greendao.RssItemDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by Dzzirt on 03.01.2017.
 */

public class RssItemManager {

    private RssItemDao m_itemDao;

    public RssItemManager(RssItemDao dao) {
        m_itemDao = dao;
    }

    public List<RssItem> getAll(boolean reverse) {
        QueryBuilder<RssItem> rssItemQueryBuilder = m_itemDao.queryBuilder();
        if (reverse) {
            rssItemQueryBuilder = rssItemQueryBuilder.orderDesc(RssItemDao.Properties.PubDate);
        }
        return rssItemQueryBuilder.build().list();
    }

    public void deleteAll() {
        m_itemDao.deleteAll();
    }

    public RssItem getRssItemById(Long id) {
        return m_itemDao.queryBuilder().where(RssItemDao.Properties.Id.eq(id)).unique();
    }

    public void insertAllWithUpdate(List<RssItem> RssItems) {
        for (RssItem updatingItem : RssItems) {
            RssItem updatedItem = m_itemDao.queryBuilder() //get existing item with id if any
                    .where(RssItemDao.Properties.Title.eq(updatingItem.getTitle()))
                    .unique();
            if (updatedItem != null) {
                // updating by pub date only
                if (updatingItem.getPubDate().after(updatedItem.getPubDate())) {
                    updatingItem.setId(updatedItem.getId());
                    m_itemDao.update(updatingItem);
                }
            } else if (isItemValid(updatingItem)) {
                m_itemDao.insert(updatingItem);
            }
        }
    }

    //rss item is valid if it have title and description
    private boolean isItemValid(RssItem item) {
        return !(item.getTitle().isEmpty() || item.getLink().isEmpty());
    }

}
