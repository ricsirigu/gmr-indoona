package com.gmr.indoona.model;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;



@Entity
public class User {
    @Id Long id;
    @Index String userId;
    String token;
    String jsonUserAccessToken;

    private User(){

    }


    public User(String userId, String token, String json){
        this.userId = userId;
        this.token = token;
        this.jsonUserAccessToken = json;
    }

    public String getUserId(){
        return userId;
    }

    public String getToken(){
        return token;
    }
       public String jsonUserAccessToken(){
        return jsonUserAccessToken;
    }

}