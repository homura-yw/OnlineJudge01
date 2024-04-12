package com.group01.onlinejudge01.pojo;

public class User {
    private int userId;
    private int password;
    private String nickname;
    private String imgUrl;
    private int numSubmission;
    private int numSolved;
    private String registDate;

    // 构造函数
    public User() {
    }

    // Getter和Setter方法
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getNumSubmission() {
        return numSubmission;
    }

    public void setNumSubmission(int numSubmission) {
        this.numSubmission = numSubmission;
    }

    public int getNumSolved() {
        return numSolved;
    }

    public void setNumSolved(int numSolved) {
        this.numSolved = numSolved;
    }

    public String getRegistDate() {
        return registDate;
    }

    public void setRegistDate(String registDate) {
        this.registDate = registDate;
    }
}
