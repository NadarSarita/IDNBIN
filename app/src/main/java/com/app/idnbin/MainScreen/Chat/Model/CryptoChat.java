package com.app.idnbin.MainScreen.Chat.Model;

import androidx.annotation.NonNull;

public class CryptoChat {
    String message;
    String Email;
    String key;

    public CryptoChat(String message, String email) {
        this.message = message;
        Email = email;
        this.key = key;
    }

    public CryptoChat() {
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
        return "Crypto{" +
                "messgae ='" + message + '\'' +
                ", Email ='" + Email + '\'' +
                ",key ='" + key + '\'' +
                '}';
    }
}
