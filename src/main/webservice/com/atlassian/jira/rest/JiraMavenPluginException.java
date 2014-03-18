package com.atlassian.jira.rest;

public class JiraMavenPluginException extends Exception {

    private static final long serialVersionUID = -1L;

    public JiraMavenPluginException() {
        super();
    }
    public JiraMavenPluginException(String message) {
        super(message);
    }
    public JiraMavenPluginException(String message, Throwable cause) {
        super(message, cause);
    }
}
