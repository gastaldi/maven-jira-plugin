package com.atlassian.jira.rest;

import javax.xml.bind.annotation.XmlElement;

public class Issue {

    @XmlElement
    private String expand;
    
    @XmlElement
    private String id;
    
    @XmlElement
    private String self;
    
    @XmlElement
    private String key;

    @XmlElement
    private Fields fields;

    public String getExpand() {
        return expand;
    }

    public void setExpand(String expand) {
        this.expand = expand;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "Issue [expand=" + expand + ", id=" + id + ", self=" + self + ", key=" + key + ", fields=" + fields
                + "]";
    }

    public Object getSummary() {
        return fields.getSummary();
    }

    
}