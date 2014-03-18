package com.atlassian.jira.rest;

public class Status {
    private String self;
    private String description;
    private String iconUrl;
    private String name;
    private String id;
    public String getSelf() {
        return self;
    }
    public void setSelf(String self) {
        this.self = self;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getIconUrl() {
        return iconUrl;
    }
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "Status [self=" + self + ", description=" + description + ", iconUrl=" + iconUrl + ", name=" + name
                + ", id=" + id + "]";
    }

}
