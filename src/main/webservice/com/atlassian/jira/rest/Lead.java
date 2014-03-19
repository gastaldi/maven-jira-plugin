package com.atlassian.jira.rest;

import java.util.Map;

public class Lead {

    private String self;

    private String key;

    private String name;

    private Map<String, String> avatarUrls;
    
    private String displayName;
    
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

    public Map<String, String> getAvatarUrls() {
        return avatarUrls;
    }

    public void setAvatarUrls(Map<String, String> avatarUrls) {
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
