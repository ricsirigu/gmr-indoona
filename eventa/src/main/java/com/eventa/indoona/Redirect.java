package com.eventa.indoona;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.io.IOException;
import java.util.Properties;
import javax.servlet.ServletException;
import com.indoona.openplatform.sdk.model.UserAccessToken;
import com.indoona.openplatform.sdk.model.message.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.indoona.openplatform.sdk.provider.ProviderLocator;
import com.indoona.openplatform.sdk.provider.exception.*;


public class Redirect extends HttpServlet {
  @Override
protected void doGet(HttpServletRequest request, 
    HttpServletResponse response) 
    throws ServletException, IOException {

             Config.init();


      // get the received OAuth2 authorization code
      String code = request.getParameter("code");
      response.getWriter().println(code);

         // request a user access token based on the received code
       UserAccessToken token = null;
try {
    token = ProviderLocator.getInstance()
                .getAuthorizationProvider()
                .getUserAccessToken(code);

                String sentMsgStr = ProviderLocator.getInstance()
    .getApiProvider().invokeTextMessageSendApi(
      token,
      "0",
      "0",
      "Hello Eventa",
      false);

    Message sentMsg = MessageFactory.getInstance().buildMessage(sentMsgStr);

} 
catch (Exception e) {
  e.printStackTrace();
}





/*      List<String> caps = new ArrayList();
caps.add("group_add");
caps.add("interactive");

Contact eventaChannel = new Contact("01", 
  "Eventa room", 
  "https://pbs.twimg.com/profile_images/638639381226618880/qNOP22ie_400x400.png", 
  caps);
    
ProviderLocator.getInstance().getApiProvider()
    .invokeContactAddApi(token, eventaChannel);*/
      
   





// the API above returns a string representation of the message just sent:
// by means of the message factory, it can be inflated into a Java object,
// that in turn can be serialized/deserialzed to implement local message
// persistency
           
        
      // here you should locally store the received access
      // token for future use
      
      // typically, the connected user will be now redirected 
      // to the App’s management page
      //response.sendRedirect("/managementUrl");
  }

}