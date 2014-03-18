package com.atlassian.jira.rest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RemoteVersion {

    private String id;

    private String project;

    private int projectId;

    private String self;

    private String description;

    private String name;
    
    private boolean archived;

    private String startDate = generateNormalFormattedDate();

    private String userStartDate;

    private String releaseDate;

    private String userReleaseDate;

    private boolean released;

    private boolean overdue;

    public RemoteVersion() { }

    public RemoteVersion(RemoteVersion otherRemoteVersion) { 
        setDescription(otherRemoteVersion.getDescription());
        setName(otherRemoteVersion.getName());
        setStartDate(otherRemoteVersion.getStartDate());
        setProjectId(otherRemoteVersion.getProjectId());
    }

    public RemoteVersion(java.lang.String id, java.lang.String name,
            boolean archived, boolean released) {
        this.id = id;
        this.name = name;
        this.archived = archived;
        this.releaseDate = generateNormalFormattedDate();
        this.released = released;
    }
	/**
	 * Gets the archived value for this RemoteVersion.
	 * 
	 * @return archived
	 */
	public boolean isArchived() {
		return archived;
	}

	/**
	 * Sets the archived value for this RemoteVersion.
	 * 
	 * @param archived
	 */
	public void setArchived(boolean archived) {
		this.archived = archived;
	}

	/**
	 * Gets the releaseDate value for this RemoteVersion.
	 * 
	 * @return releaseDate
	 */
	public String getReleaseDate() {
		return releaseDate;
	}

	/**
	 * Sets the releaseDate value for this RemoteVersion.
	 * 
	 * @param releaseDate
	 */
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	/**
	 * Gets the released value for this RemoteVersion.
	 * 
	 * @return released
	 */
	public boolean isReleased() {
		return released;
	}

	/**
	 * Sets the released value for this RemoteVersion.
	 * 
	 * @param released
	 */
	public void setReleased(boolean released) {
		this.released = released;
	}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

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

    public String getUserStartDate() {
        return userStartDate;
    }

    public void setUserStartDate(String userStartDate) {
        this.userStartDate = userStartDate;
    }

    public String getUserReleaseDate() {
        return userReleaseDate;
    }

    public void setUserReleaseDate(String userReleaseDate) {
        this.userReleaseDate = userReleaseDate;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public static String generateNormalFormattedDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }
    
    public static String generateUserFormattedDate() {
        return new SimpleDateFormat("dd/MM/yy").format(new Date());
    }

    public boolean isOverdue() {
        return overdue;
    }

    public void setOverdue(boolean overdue) {
        this.overdue = overdue;
    }

    @Override
    public String toString() {
        return "RemoteVersion [id=" + id + ", project=" + project + ", projectId=" + projectId + ", self=" + self
                + ", description=" + description + ", name=" + name + ", archived=" + archived + ", startDate="
                + startDate + ", userStartDate=" + userStartDate + ", releaseDate=" + releaseDate
                + ", userReleaseDate=" + userReleaseDate + ", released=" + released + ", overdue=" + overdue + "]";
    }

}
