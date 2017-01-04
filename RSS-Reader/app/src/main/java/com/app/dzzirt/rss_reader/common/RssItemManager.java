package com.app.dzzirt.rss_reader.common;

import com.app.dzzirt.rss_reader.utils.DateUtils;
import com.app.dzzirt.rss_reader.greendao.RssItem;
import com.app.dzzirt.rss_reader.greendao.RssItemDao;

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

    public List<RssItem> getAll() {
        return m_itemDao.loadAll();
    }

    public void insertAllWithUpdate(List<RssItem> RssItems) {
        for (RssItem updatingItem : RssItems) {
            RssItem updatedItem = m_itemDao.queryBuilder()
                    .where(RssItemDao.Properties.Title.eq(updatingItem.getTitle()))
                    .unique();
            if (updatedItem != null) {
                try {
                    if (isDateGreaterThan(updatingItem.getPubDate(), updatedItem.getPubDate())
                            || !isEquals(updatingItem.getGuid(), updatedItem.getGuid())
                            || !isEquals(updatingItem.getDescribtion(), updatedItem.getDescribtion())) {
                        updatingItem.setId(updatedItem.getId());
                        m_itemDao.update(updatingItem);
                    }
                } catch (ParseException ignored) {}
            } else if (isItemValid(updatingItem)) {
                m_itemDao.insert(updatingItem);
            }
        }
    }

    private boolean isEquals(String lhs, String rhs) {
        return (lhs != null && rhs != null) && lhs.equals(rhs);
    }

    private boolean isItemValid(RssItem item) {
        if (!item.getDescribtion().isEmpty() || !item.getTitle().isEmpty()) {
            if (!item.getPubDate().isEmpty()) {
                try {
                    DateUtils.parseRssDate(item.getPubDate());
                } catch (ParseException e) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean isDateGreaterThan(String lhsDateString, String rhsDateString) throws ParseException {
        if (lhsDateString != null && rhsDateString != null) {
                Date lhsDate = DateUtils.parseRssDate(lhsDateString);
                Date rhsDate = DateUtils.parseRssDate(rhsDateString);
                if (lhsDate.after(rhsDate)) {
                    return true;
                }
        }
        return false;
    }
}
