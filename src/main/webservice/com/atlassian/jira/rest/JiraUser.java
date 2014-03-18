package com.atlassian.jira.rest;

import java.util.Map;

public class JiraUser {
    private String self;
    private String name;

    private String emailAddress;
    private Map<String, String> avatarUrls;

    private String displayName;
    private boolean active;
    public String getSelf() {
        return self;
    }
    public void setSelf(String self) {
        this.self = self;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    @Override
    public String toString() {
        return "JiraUser [self=" + self + ", name=" + name + ", emailAddress=" + emailAddress + ", avatarUrls="
                + avatarUrls + ", displayName=" + displayName + ", active=" + active + "]";
    }

}
