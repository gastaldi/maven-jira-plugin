package com.atlassian.jira.rest;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class RemoteIssue {
    
    @XmlElement
    private String expand;
    
    @XmlElement
    private int startAt;
    
    @XmlElement
    private int maxResults;
    
    @XmlElement
    private int total;
    
    @XmlElement
    private List<Issue> issues;

    public String getExpand() {
        return expand;
    }

    public void setExpand(String expand) {
        this.expand = expand;
    }

    public int getStartAt() {
        return startAt;
    }

    public void setStartAt(int startAt) {
        this.startAt = startAt;
    }

    public int getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    @Override
    public String toString() {
        return "RemoteIssue [expand=" + expand + ", startAt=" + startAt + ", maxResults=" + maxResults + ", total="
                + total + ", issues=" + issues + "]";
    }

}
