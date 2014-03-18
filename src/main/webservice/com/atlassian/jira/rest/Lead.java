package com.atlassian.jira.rest;

import java.util.Map;

import javax.xml.bind.annotation.XmlElement;

public class Lead {

    @XmlElement
    private String self;

    @XmlElement
    private String key;

    @XmlElement
    private String name;

    @XmlElement
    private Map avatarUrls;
    
    @XmlElement
    private String displayName;
    
    @XmlElement
    private String active;

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map getAvatarUrls() {
        return avatarUrls;
    }

    public void setAvatarUrls(Map avatarUrls) {
        this.avatarUrls = avatarUrls;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Lead [self=" + self + ", key=" + key + ", name=" + name + ", avatarUrls=" + avatarUrls
                + ", displayName=" + displayName + ", active=" + active + "]";
    }
}
