package com.collab.cart.collabcartfinal.models;

import lombok.Data;

import java.util.List;

@Data
public class UserModel {

    public String userName;
    public String emailId;
    public String password;
    public String type;
    public String description;
    public String category;
    public int followers;
    public int averageViews;
    public List<String> insights;
    public List<Campaign> campaigns;
}
