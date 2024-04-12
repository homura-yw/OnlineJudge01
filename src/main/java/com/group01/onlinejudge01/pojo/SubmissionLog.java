package com.group01.onlinejudge01.pojo;

public class SubmissionLog {
    private int submissionID;
    private int userID;
    private int problemID;
    private String codeUrl;

    // 构造函数
    public SubmissionLog() {
    }

    // Getter和Setter方法
    public int getSubmissionID() {
        return submissionID;
    }

    public void setSubmissionID(int submissionID) {
        this.submissionID = submissionID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getProblemID() {
        return problemID;
    }

    public void setProblemID(int problemID) {
        this.problemID = problemID;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }
}
