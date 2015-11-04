package com.eventa.indoona;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;
import net.sf.json.*;


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
import com.indoona.openplatform.sdk.model.message.*;

import  com.eventa.indoona.model.User;



public class IndoonaServlet extends HttpServlet {
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {

    try {

    //testing the rest service
     resp.setContentType("text/plain");
     resp.getWriter().println("testing rest: ok");



      String sender = "prova";
      //retrieving the user from persistence
      User usr = ObjectifyService.ofy().load().type(User.class).filter("userId", sender).first().now();

      String userResponse = buildResponse("");


      //sending message
      String sentMsgStr = ProviderLocator.getInstance().getApiProvider().invokeTextMessageSendApi(
      UserAccessToken.fromJson(usr.jsonUserAccessToken()),
      Config.roomNumber,
      usr.getUserId(),
      userResponse,
      false);
      Message sentMsg = MessageFactory.getInstance().buildMessage(sentMsgStr);



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
      JSONObject jobj = JSONObject.fromObject(data);
      String sender = jobj.getString("sender");
      String userText = jobj.getJSONObject("data").getString("body");
      String[] parts = sender.split("@");
      sender = parts[0];

      
      //retrieving the user from persistence
      User usr = ObjectifyService.ofy().load().type(User.class).filter("userId", sender).first().now();

      String userResponse = buildResponse(userText);


      //sending message
      String sentMsgStr = ProviderLocator.getInstance().getApiProvider().invokeTextMessageSendApi(
      UserAccessToken.fromJson(usr.jsonUserAccessToken()),
      Config.roomNumber,
      usr.getUserId(),
      userResponse,
      false);
      Message sentMsg = MessageFactory.getInstance().buildMessage(sentMsgStr);
 
    } 

    catch (Exception e) {
      e.printStackTrace();
    }

    }



    public String buildResponse(String usertext){
      String response = "Hi my friend!";



      return response;
    }
}