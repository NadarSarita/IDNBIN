package com.app.idnbin.MainScreen.Chat.Model;

import androidx.annotation.NonNull;

public class Englishchat {
    String message;
    String Email;
    String key;
    public Englishchat(String message, String Email) {
        this.message = message;
        this.Email = Email;
        this.key = key;
    }

    public Englishchat() {
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
