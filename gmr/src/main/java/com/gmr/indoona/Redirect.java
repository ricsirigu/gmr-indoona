package com.gmr.indoona;

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


public class Redirect extends HttpServlet {
  @Override
protected void doGet(HttpServletRequest request, 
    HttpServletResponse response) 
    throws ServletException, IOException {



      // get the received OAuth2 authorization code
      String code = request.getParameter("code");
    
         // request a user access token based on the received code
      UserAccessToken token = null;
      try {
          token = ProviderLocator.getInstance()
                .getAuthorizationProvider()
                .getUserAccessToken(code);

               
           // save user in persistence
           User usr = new User(token.getUserId(), token.getToken(), token.toJson());
           ObjectifyService.ofy().save().entity(usr).now();


          //create a contact for the user
          List<String> caps = new ArrayList();
          caps.add("group_add");
          caps.add("interactive");

          Contact gmrChannel = new Contact(
          Config.roomNumber, 
          Config.roomName, 
          Config.roomImg, 
          caps);
    
          ProviderLocator.getInstance().getApiProvider().invokeContactAddApi(token, gmrChannel);


          //send a welcome message to the user
          String sentMsgStr = ProviderLocator.getInstance()
              .getApiProvider().invokeTextMessageSendApi(
              token,
              Config.roomNumber,
              "0",
              Config.roomDesc,
              false);
          Message sentMsg = MessageFactory.getInstance().buildMessage(sentMsgStr);

    



} 
catch (Exception e) {
  e.printStackTrace();
}




  }

}