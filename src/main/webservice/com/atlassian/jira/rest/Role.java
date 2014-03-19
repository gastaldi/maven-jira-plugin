package com.atlassian.jira.rest;


public class Role {

    private String developers;

    public String getDevelopers() {
        return developers;
    }

    public void setDevelopers(String developers) {
        this.developers = developers;
    }

    @Override
    public String toString() {
        return "Role [developers=" + developers + "]";
    }
    
}
