package com.app.idnbin.util;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GlobalConstants {

    public static final DatabaseReference UsersInstance = FirebaseDatabase.getInstance().getReference("UserDetails");
    public static final DatabaseReference DeviceDetailsInstance = FirebaseDatabase.getInstance().getReference("DeviceDetails");
    public static final DatabaseReference LoginDetailsInstance = FirebaseDatabase.getInstance().getReference("LoginDetails");
    public static final DatabaseReference ChatsInstance=FirebaseDatabase.getInstance().getReference("Chats");

}
