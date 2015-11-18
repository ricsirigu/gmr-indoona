package com.gmr.indoona.api;

import com.gmr.indoona.model.*;
import static com.gmr.indoona.model.GMRContact.*;
import com.google.appengine.repackaged.org.joda.time.DateTime;
import com.googlecode.objectify.ObjectifyService;
import com.indoona.openplatform.sdk.provider.ProviderLocator;
import com.indoona.openplatform.sdk.model.AppAccessToken;
import com.indoona.openplatform.sdk.utils.TextUtils.*;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;


public class Management extends HttpServlet {
    User usr = null;

    private static final Logger log = Logger.getLogger(Management.class.getName());

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {
            ProviderLocator locator = ProviderLocator.getInstance();

            String otp = request.getParameter("otp");

            AppAccessToken appToken = locator
                    .getAuthorizationProvider()
                    .getAppAccessToken();
            String jsonResult = locator.getApiProvider()
                    .invokeOtpVerifyApi(appToken, otp);
            String userId = JSONObject.fromObject(jsonResult).getString("user_id");

            usr = ObjectifyService.ofy().load().type(User.class).filter("userId", userId).first().now();


            response.sendRedirect("/management.jsp?user="+usr.getUserId());
            // here you should check whether the obtained
            // user_id matches the one locally stored for
            // the currently logged user (if exists);
            // if yes, provide the management page’s content,
            // otherwise you should raise an error
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    //handling a user message
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {


        String contactId = req.getParameter("contactId");

        String action = req.getParameter("action");

        String userId = req.getParameter("userId");

        log.severe("User " + userId + " " + action + " " + contactId );

        User usr = ObjectifyService.ofy().load().type(User.class).filter("userId", userId).first().now();

        switch(action){
            case "add":
                switch(contactId){
                    case "gmr-buddy": 
                        usr.addContact(GMRBuddy.CONTACT_NUMBER.toString(), contactId);
                        usr.sendMessage(GMRBuddy.CONTACT_NAME.toString(), GMRBuddy.CONTACT_NUMBER.toString());   
                        break;            
                    case "news": 
                        usr.addContact(GMRNews.CONTACT_NUMBER.toString(), contactId);
                        usr.sendMessage(GMRNews.CONTACT_NAME.toString(), GMRNews.CONTACT_NUMBER.toString());   
                        break;            
                    case "events": 
                        usr.addContact(GMREvents.CONTACT_NUMBER.toString(), contactId);
                        usr.sendMessage(GMREvents.CONTACT_NAME.toString(), GMREvents.CONTACT_NUMBER.toString());   
                        break;            
                    case "community": 
                        usr.addContact(GMRCommunity.CONTACT_NUMBER.toString(), contactId);
                        usr.sendMessage(GMRCommunity.CONTACT_NAME.toString(), GMRCommunity.CONTACT_NUMBER.toString());   
                        break;            
                    case "promo": 
                        usr.addContact(GMRPromo.CONTACT_NUMBER.toString(), contactId);
                        usr.sendMessage(GMRPromo.CONTACT_NAME.toString(), GMRPromo.CONTACT_NUMBER.toString());   
                        break;
                    }
                    break;
            case "remove":
                switch(contactId){
                    case "gmr-buddy": 
                        usr.removeContact(GMRBuddy.CONTACT_NUMBER.toString());
                        break;            
                    case "news": 
                        usr.removeContact(GMRNews.CONTACT_NUMBER.toString());
                        break;            
                    case "events": 
                        usr.removeContact(GMREvents.CONTACT_NUMBER.toString());
                        break;            
                    case "community": 
                        usr.removeContact(GMRCommunity.CONTACT_NUMBER.toString());
                        break;            
                    case "promo": 
                        usr.removeContact(GMRPromo.CONTACT_NUMBER.toString());
                        break;
                }

        }


    }   
    

}