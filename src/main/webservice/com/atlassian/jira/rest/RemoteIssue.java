package com.atlassian.jira.rest;

import java.util.List;

public class RemoteIssue {
    
    private String expand;
    
    private int startAt;
    
    private int maxResults;
    
    private int total;
    
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
