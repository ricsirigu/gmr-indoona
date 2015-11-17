package com.gmr.indoona.api;


 import com.gmr.indoona.model.Conversation;
 import com.gmr.indoona.model.User;
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

        log.info("User " + userId + " " + action + " " + contactId );

        User usr = ObjectifyService.ofy().load().type(User.class).filter("userId", userId).first().now();

        usr.addChannel(contactId, contactId);

        switch(contactId){
            case "gmr-buddy": 
                usr.sendMessage("GMR Buddy", contactId);   
                break;            
            case "news": 
                usr.sendMessage("GMR News", contactId);   
                break;            
            case "events": 
                usr.sendMessage("GMR Events", contactId);   
                break;            
            case "community": 
                usr.sendMessage("GMR Community", contactId);   
                break;            
            case "promo": 
                usr.sendMessage("GMR Promo", contactId);   
                break;
        }

    
        //resp.sendRedirect("/management.jsp?user="+usr.getUserId());


    }

}