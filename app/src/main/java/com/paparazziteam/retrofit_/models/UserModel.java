package com.paparazziteam.retrofit_.models;

import com.google.gson.annotations.SerializedName;

public class UserModel {

    private int userId;
    private int id;
    private String title;

    @SerializedName("completed") // when you wanna change the variable of name
    private boolean status;
}
