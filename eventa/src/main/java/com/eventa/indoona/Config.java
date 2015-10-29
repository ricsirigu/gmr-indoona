package com.eventa.indoona;

import com.indoona.openplatform.sdk.provider.ProviderLocator;
import com.indoona.openplatform.sdk.provider.impl.*;
import com.indoona.openplatform.sdk.provider.exception.*;

import java.util.*;
import java.lang.*;



public class Config  {

public static void  init() {	

// initialize a configuration provider with your app’s data
List<String> scope = new ArrayList();
scope.add("basic");
scope.add("user_phone");
ConfigurationProvider confProvider = new ConfigurationProvider();
confProvider.init("5631fe051d78197630f2a8aakUcFL", "XS1p1XjHPPO4CGDR", 
	"https://corded-forge-111216.appspot.com/redirect", scope);

// construct the provider locator’s unique instance with the
// obtained configuration provider

// add signature providers according to what specified at App Registration, 
// to trust the sender on incoming messages
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