package com.group01.onlinejudge01.pojo;

public class Problem {
    private int problemID;
    private String problemName;
    private String problemDescription;
    private int problemDifficulty;
    private int problemType;
    private int testCaseID;
    private String creationDate;
    private String creator;
    private int timeLimit;
    private int memoryLimit;
    private String additionalTestCases;

    // 构造函数
    public Problem() {
    }

    // Getter和Setter方法
    public int getProblemID() {
        return problemID;
    }

    public void setProblemID(int problemID) {
        this.problemID = problemID;
    }

    public String getProblemName() {
        return problemName;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public int getProblemDifficulty() {
        return problemDifficulty;
    }

    public void setProblemDifficulty(int problemDifficulty) {
        this.problemDifficulty = problemDifficulty;
    }

    public int getProblemType() {
        return problemType;
    }

    public void setProblemType(int problemType) {
        this.problemType = problemType;
    }

    public int getTestCaseID() {
        return testCaseID;
    }

    public void setTestCaseID(int testCaseID) {
        this.testCaseID = testCaseID;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(int memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public String getAdditionalTestCases() {
        return additionalTestCases;
    }

    public void setAdditionalTestCases(String additionalTestCases) {
        this.additionalTestCases = additionalTestCases;
    }
}
