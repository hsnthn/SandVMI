package com.cybercrypt.sandvmi.data.remote.model;

import com.google.gson.annotations.SerializedName;

public class User {

    public User(){}

    public User( String username, String password) {
        this.username = username;
        this.password = password;
    }

    @SerializedName("id") public String id = "";
    @SerializedName("username") public String username = "";
    @SerializedName("password") public String password = "";
    @SerializedName("email") public String email = "";



}
