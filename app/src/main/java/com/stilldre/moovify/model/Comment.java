package com.stilldre.moovify.model;

public class Comment {
    private String userName, message;

    public Comment(){

    }

    public Comment(String userName, String message){
        this.userName = userName;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
