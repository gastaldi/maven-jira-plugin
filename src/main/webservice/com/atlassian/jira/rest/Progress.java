package com.atlassian.jira.rest;

public class Progress {

    private int progress;
    
    private int total;

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Progress [progress=" + progress + ", total=" + total + "]";
    }
    
}
