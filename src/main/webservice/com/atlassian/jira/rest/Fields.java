package com.atlassian.jira.rest;

import java.util.Date;

public class Fields {
    
    private Progress progress;

    private RemoteVersion[] affectsVersions;
    
    private JiraUser assignee;
    
    private String[] attachmentNames;
    
    private Component[] components;
    
    private Date created;

    private CustomFieldValue customFieldValues;
    
    private String description;
    
    private Date duedate;
    
    private String environment;
    
    private RemoteVersion[] fixVersions;

    private String key;
    
    private Priority priority;
    
    private Project project;
    
    private JiraUser reporter;

    private String resolution;
    
    private Status status;
    
    private String summary;
    
    private IssueType issuetype;
    
    private Date updated;
    
    private Votes votes;

    public RemoteVersion[] getAffectsVersions() {
        return affectsVersions;
    }

    public void setAffectsVersions(RemoteVersion[] affectsVersions) {
        this.affectsVersions = affectsVersions;
    }

    public JiraUser getAssignee() {
        return assignee;
    }

    public void setAssignee(JiraUser assignee) {
        this.assignee = assignee;
    }

    public String[] getAttachmentNames() {
        return attachmentNames;
    }

    public void setAttachmentNames(String[] attachmentNames) {
        this.attachmentNames = attachmentNames;
    }

    public Component[] getComponents() {
        return components;
    }

    public void setComponents(Component[] components) {
        this.components = components;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public CustomFieldValue getCustomFieldValues() {
        return customFieldValues;
    }

    public void setCustomFieldValues(CustomFieldValue customFieldValues) {
        this.customFieldValues = customFieldValues;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public RemoteVersion[] getFixVersions() {
        return fixVersions;
    }

    public void setFixVersions(RemoteVersion[] fixVersions) {
        this.fixVersions = fixVersions;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public JiraUser getReporter() {
        return reporter;
    }

    public void setReporter(JiraUser reporter) {
        this.reporter = reporter;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public IssueType getIssuetype() {
        return issuetype;
    }

    public void setIssuetype(IssueType issuetype) {
        this.issuetype = issuetype;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Votes getVotes() {
        return votes;
    }

    public void setVotes(Votes votes) {
        this.votes = votes;
    }

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }


}
