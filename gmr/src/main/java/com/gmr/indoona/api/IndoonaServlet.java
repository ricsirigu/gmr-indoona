package com.gmr.indoona.api;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.indoona.openplatform.sdk.provider.ProviderLocator;
import com.indoona.openplatform.sdk.provider.exception.*;
import com.indoona.openplatform.sdk.model.AccessToken;
import com.indoona.openplatform.sdk.model.message.*;
import com.indoona.openplatform.sdk.model.UserAccessToken;
import static com.gmr.indoona.model.GMRContact.*;
import  com.gmr.indoona.model.User;
import com.gmr.indoona.config.Config;
import java.util.logging.Logger;


public class IndoonaServlet extends HttpServlet {

  private static final Logger log = Logger.getLogger(IndoonaServlet.class.getName());

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {

    try {

       //testing the rest service
       resp.setContentType("text/plain");
       resp.getWriter().println("testing rest: ok");
    } 

    catch (Exception e) {
      e.printStackTrace();
    }

  }


  //handling a user message
  @Override 
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    
    try {

      //getting data parameter
      String data = req.getParameter("data");

       //parsing just the sender and user txt 
      //TODO check for message type
      String roomId = MessageFactory.getInstance().buildMessage(data).getRecipient().split("@")[0];

      TextMessage receivedMsg = (TextMessage) MessageFactory.getInstance().buildMessage(data);
      
      String sender = receivedMsg.getSender();

      String userText = receivedMsg.getText();

      String[] parts = sender.split("@");
      sender = parts[0];
      
      //retrieving the user from persistence
      User usr = ObjectifyService.ofy().load().type(User.class).filter("userId", sender).first().now();

      //TODO Handling user not found

      //sending message
      String userResponse = usr.buildResponse(userText);
        
      log.severe("Message " + receivedMsg.getText() + " room " + roomId );

      //sending message
      if(roomId.equals(GMRBuddy.CONTACT_NUMBER.toString())){
         usr.sendMessage(userResponse, GMRBuddy.CONTACT_NUMBER.toString());
      }
 
    } 

    catch (Exception e) {
      e.printStackTrace();
    }

    }


}