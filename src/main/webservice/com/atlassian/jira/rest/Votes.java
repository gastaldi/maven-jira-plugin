package com.atlassian.jira.rest;

public class Votes {
    private String self;
    
    private Long votes;
    private boolean hasVoted;
    public String getSelf() {
        return self;
    }
    public void setSelf(String self) {
        this.self = self;
    }
    public Long getVotes() {
        return votes;
    }
    public void setVotes(Long votes) {
        this.votes = votes;
    }
    public boolean isHasVoted() {
        return hasVoted;
    }
    public void setHasVoted(boolean hasVoted) {
        this.hasVoted = hasVoted;
    }
    @Override
    public String toString() {
        return "Votes [self=" + self + ", votes=" + votes + ", hasVoted=" + hasVoted + "]";
    }
    
}
