package com.app.idnbin.LoginRegister;

import java.io.Serializable;

public class UserDetails implements Serializable{

    private String id;
    private String email;
    private String gmail;
    private String phone;
    private String time;
    private String imageURL;

    public UserDetails(String ID, String Email, String Gmail, String Phone, String Time, String imageURL)
    {
        this.id = ID;
        this.email = Email;
        this.gmail = Gmail;
        this.phone = Phone;
        this.time = Time;
        this.imageURL = imageURL;
    }

    public UserDetails() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

}
