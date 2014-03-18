package com.atlassian.jira.rest;

import javax.xml.bind.annotation.XmlElement;

public class Component {
    
    @XmlElement
    private String self;

    @XmlElement
    private String id;

    @XmlElement
    private String name;

    @XmlElement
    private String description;
    
    @XmlElement
    private Lead lead;
    
    @XmlElement(name="asssignee")
    private Assignee assignee;

    @XmlElement(name="realAsssignee")
    private Assignee realAssignee;


    @XmlElement(name="isAssigneeTypeValid")
    private boolean assigneeTypeValid;


    public String getSelf() {
        return self;
    }


    public void setSelf(String self) {
        this.self = self;
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


    public Assignee getAssignee() {
        return assignee;
    }


    public void setAssignee(Assignee assignee) {
        this.assignee = assignee;
    }


    public Assignee getRealAssignee() {
        return realAssignee;
    }


    public void setRealAssignee(Assignee realAssignee) {
        this.realAssignee = realAssignee;
    }


    public boolean isAssigneeTypeValid() {
        return assigneeTypeValid;
    }


    public void setAssigneeTypeValid(boolean assigneeTypeValid) {
        this.assigneeTypeValid = assigneeTypeValid;
    }


    @Override
    public String toString() {
        return "Component [self=" + self + ", id=" + id + ", description=" + description + ", lead=" + lead
                + ", assignee=" + assignee + ", realAssignee=" + realAssignee + ", assigneeTypeValid="
                + assigneeTypeValid + "]";
    }
    
}
