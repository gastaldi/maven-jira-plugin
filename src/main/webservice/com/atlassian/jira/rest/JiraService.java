package com.atlassian.jira.rest;

import java.util.List;




public interface JiraService {

    public abstract RemoteVersion[] getVersions(String jiraProjectKey);

    public abstract Project getProject(String jiraProjectKey);

    public abstract RemoteVersion addVersion(String jiraProjectKey, RemoteVersion newVersion);

    public abstract RemoteVersion releaseVersion(String jiraProjectKey, RemoteVersion remoteReleasedVersion);

    public abstract List<Issue> getIssuesFromJqlSearch(String jql, Integer maxIssues);

    public abstract RemoteVersion archiveVersion(String jiraProjectKey, String name, boolean archive);


}