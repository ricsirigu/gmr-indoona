package com.gmr.indoona.api;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;


import java.io.IOException;
import java.util.Properties;
import javax.servlet.ServletException;
import java.util.*;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.indoona.openplatform.sdk.provider.ProviderLocator;
import com.indoona.openplatform.sdk.provider.exception.*;
import com.indoona.openplatform.sdk.model.Contact;
import com.indoona.openplatform.sdk.model.AccessToken;
import com.indoona.openplatform.sdk.model.message.*;
import com.indoona.openplatform.sdk.model.UserAccessToken;
import com.indoona.openplatform.sdk.model.message.*;

import  com.gmr.indoona.model.User;
import com.gmr.indoona.config.Config;

public class Redirect extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

        // getting authorization code
        String code = request.getParameter("code");

        // getting user info and storing
        User usr = User.createUser(code);

        //add channel to user and sending message
        usr.addChannel();

        //send welcome message to User
        usr.sendMessage(Config.roomFirstMessage);   

        response.sendRedirect("/management.jsp");

    }

    

}

