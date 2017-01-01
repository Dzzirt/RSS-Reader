package com.app.dzzirt.rss_reader.rssparser;

import java.util.List;
import java.util.Map;

/**
 * Created by Dzzirt on 30.12.2016.
 */

public class XmlElement {
    private Boolean m_hasChilds = null;
    private String m_body;
    private Map<String, String> m_attributes;
    private String m_tag;
    private List<XmlElement> m_childs;
    private XmlElement m_parent;

    public XmlElement(XmlElement parent) {
        m_hasChilds = null;
        m_parent = parent;
    }

    public String getTag() {
        return m_tag;
    }

    public Map<String, String> getAttributes() {
        return m_attributes;
    }

    public XmlElement getParent() {
        return m_parent;
    }
    public String getBody() {
        return m_body;
    }

    public List<XmlElement> getChilds() {
        return m_childs;
    }

    public void setTag(String tag) {
        m_tag = tag;
    }

    public void setAttributes(Map<String, String> attributes) {
        m_attributes = attributes;
    }

    public void setBody(String m_body) {
        this.m_body = m_body;
    }

    public void setChilds(List<XmlElement> m_childs) {
        this.m_childs = m_childs;

    }
}
