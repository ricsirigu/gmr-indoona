package com.gmr.indoona.config;

import com.indoona.openplatform.sdk.provider.impl.*;
import com.indoona.openplatform.sdk.provider.exception.*;
import com.indoona.openplatform.sdk.provider.*;

import java.util.*;
import java.lang.*;



public class Config  {

	//app settings
	public static String redirect= "*"; 
	public static String client_id = "*";
	public static String client_secret = "*";

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