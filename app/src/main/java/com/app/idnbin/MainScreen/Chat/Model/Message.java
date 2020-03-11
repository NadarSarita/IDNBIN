package com.app.idnbin.MainScreen.Chat.Model;

import androidx.annotation.NonNull;

public class Message {
    String message;
    String Email;
    String key;
    String ImageURL;
    String groupName;
    String userId;

    public Message(String message, String Email,String Imageurl) {
        this.message = message;
        this.Email = Email;
        this.ImageURL = Imageurl;
    }


    public Message(String groupName, String userId,String message, String Imageurl) {
        this.groupName=groupName;
        this.userId=userId;
        this.message = message;
        this.ImageURL = Imageurl;
    }

    public Message() {
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @NonNull
    @Override
    public String toString() {
        return "Forex{" +
                "messgae ='" + message + '\'' +
                ", Email ='" + Email + '\'' +
                ",key ='" + key + '\'' +
                '}';
    }
}
