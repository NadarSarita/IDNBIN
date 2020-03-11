package com.app.idnbin.MainScreen.Chat.Model;

import androidx.annotation.NonNull;

public class Stockschat {

    String message;
    String Email;
    String key;

    public Stockschat(String message, String email) {
        this.message = message;
        Email = email;

    }

    public Stockschat() {
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
