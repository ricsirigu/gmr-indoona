package com.gmr.indoona.config;

import com.indoona.openplatform.sdk.provider.impl.*;
import com.indoona.openplatform.sdk.provider.exception.*;
import com.indoona.openplatform.sdk.provider.*;

import java.util.*;
import java.lang.*;



public class Config  {

	//app settings
	public static String redirect= "https://gmr-indoona.appspot.com/indoona/redirect"; 
	public static String client_id = "5645d0cd8464495b4001d2bcsj9Pb";
	public static String client_secret = "ajVactu3D0NA3gO5QFhYC1iaqADKKsWUMR5Zkz3XAQyE6nJX31VG2eRtPueAXauJ";

	//room settings
	public static String roomNumber ="10"; 
	public static String roomName   ="Guide Me Right";
	public static String roomImg    ="https://lh3.googleusercontent.com/-zhNcU9MA0C0/U9TO9xJj4eI/AAAAAAAAASY/af4GlYGR8aI/s301-no/Omino%2BGMR.png"; 
	public static String roomFirstMessage = "Ciao, sono GuideMeRight e ti aiuterò a trovare le migliori esperienze intorno a te. Digita la città :-)";
	public static String roomDesc = "Ciao, sono GuideMeRight e ti aiuterò a trovare le migliori esperienze intorno a te. Digita la città :-)";


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