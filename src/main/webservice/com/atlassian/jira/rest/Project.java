package com.atlassian.jira.rest;

import java.util.List;
import java.util.Map;

public class Project {

    private String expand;

    private String self;

    private int id;

    private String key;

    private String description;
    
    private Lead lead;
    
    private List<Component> components;

    private List<IssueType> issueTypes;
    
    private String url;
    
    private String email;
    
    private String assigneeType;
    
    private List<String> versions;
    
    private String name;
    
    private Map roles;
    
    private Map avatarUrls;

    public String getExpand() {
        return expand;
    }

    public void setExpand(String expand) {
        this.expand = expand;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Lead getLead() {
        return lead;
    }

    public void setLead(Lead lead) {
        this.lead = lead;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    public List<IssueType> getIssueTypes() {
        return issueTypes;
    }

    public void setIssueTypes(List<IssueType> issueTypes) {
        this.issueTypes = issueTypes;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAssigneeType() {
        return assigneeType;
    }

    public void setAssigneeType(String assigneeType) {
        this.assigneeType = assigneeType;
    }

    public List<String> getVersions() {
        return versions;
    }

    public void setVersions(List<String> versions) {
        this.versions = versions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map getRoles() {
        return roles;
    }

    public void setRoles(Map roles) {
        this.roles = roles;
    }

    public Map getAvatarUrls() {
        return avatarUrls;
    }

    public void setAvatarUrls(Map avatarUrls) {
        this.avatarUrls = avatarUrls;
    }

    @Override
    public String toString() {
        return "Project [expand=" + expand + ", self=" + self + ", id=" + id + ", key=" + key + ", description="
                + description + ", lead=" + lead + ", components=" + components + ", issueTypes=" + issueTypes
                + ", url=" + url + ", email=" + email + ", assigneeType=" + assigneeType + ", versions=" + versions
                + ", name=" + name + ", roles=" + roles + ", avatarUrls=" + avatarUrls + "]";
    }

    
}
