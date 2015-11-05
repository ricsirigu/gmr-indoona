package com.gmr.indoona;

import com.indoona.openplatform.sdk.provider.impl.*;
import com.indoona.openplatform.sdk.provider.exception.*;
import com.indoona.openplatform.sdk.provider.*;

import java.util.*;
import java.lang.*;



public class Config  {

//app settings
public static String redirect= "https://gmr-indoona.appspot.com/indoona/redirect"; 
public static String client_id = "**";
public static String client_secret = "**";

//room settings
public static String roomNumber ="10"; 
public static String roomName   ="Guide Me Right";
public static String roomImg    ="https://lh3.googleusercontent.com/-lBS8dnXoAiM/AAAAAAAAAAI/AAAAAAAABfM/9wjarOVJMVE/s120-c/photo.jpg"; 
public static String roomFirstMessage = "Ciao, sono GuideMeRight e ti aiuterò a trovare le migliori attività intorno a te. Digita la città :-)";
public static String roomDesc = "Ciao, sono GuideMeRight e ti aiuterò a trovare le migliori attività intorno a te. Digita la città :-)";


public static void  init() {	


// initialize a configuration provider with your app’s data
List<String> scope = new ArrayList();
scope.add("basic");
scope.add("user_phone");
ConfigurationProvider confProvider = new ConfigurationProvider();
confProvider.init(client_id, client_secret, 
	redirect, scope);


try {
	ProviderLocator.buildInstance(confProvider);

	ProviderLocator.getInstance().setProvider(
		ProviderLocator.TAG_MD5_SIGNATURE_PROVIDER, 
		new MD5SignatureProvider());
} 
catch (Exception e) {
	e.printStackTrace();
}
}
}