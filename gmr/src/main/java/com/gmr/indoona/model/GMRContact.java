package com.gmr.indoona.model;

public class GMRContact {

	public static enum GMRBuddy{
		CONTACT_NUMBER("11"),
		CONTACT_NAME("Gmr Buddy"),
		CONTACT_IMAGE_URL("/img/gmr-buddy.png"),
		CONTACT_WELCOME_MESSAGE("Ciao, sono GuideMeRight e ti aiuterò a trovare le migliori esperienze intorno a te. Digita la città :-)"),
	    CONTACT_DESCRIPTION("Ciao, sono GuideMeRight e ti aiuterò a trovare le migliori esperienze intorno a te. Digita la città :-)");

	    private final String text;

	    private GMRBuddy(final String text) {
	        this.text = text;
	    }

	    @Override
	    public String toString() {
	        return text;
	    }
	}		

	public static enum GMRNews{
		CONTACT_NUMBER("12"),
		CONTACT_NAME("Gmr News"),
		CONTACT_IMAGE_URL("/img/gmr-news.png"),
		CONTACT_WELCOME_MESSAGE("Ciao, sono GuideMeRight News e ti terrò aggiurnato sulle varie news! :-)"),
	    CONTACT_DESCRIPTION("Ciao, sono GuideMeRight News e ti terrò aggiurnato sulle varie news! :-)");

	    private final String text;

	    private GMRNews(final String text) {
	        this.text = text;
	    }

	    @Override
	    public String toString() {
	        return text;
	    }
	}		

	public static enum GMREvents{
		CONTACT_NUMBER("13"),
		CONTACT_NAME("Gmr Events"),
		CONTACT_IMAGE_URL("/img/gmr-events.png"),
		CONTACT_WELCOME_MESSAGE("Ciao, sono GuideMeRight Eventi e ti terrò aggiornato sui vari eventi! :-)"),
	    CONTACT_DESCRIPTION("Ciao, sono GuideMeRight Eventi e ti terrò aggiornato sui vari eventi :-)");

	    private final String text;

	    private GMREvents(final String text) {
	        this.text = text;
	    }

	    @Override
	    public String toString() {
	        return text;
	    }

	}		

	public static enum GMRCommunity{
		CONTACT_NUMBER("14"),
		CONTACT_NAME("Gmr Community"),
		CONTACT_IMAGE_URL("/img/gmr-community.png"),
		CONTACT_WELCOME_MESSAGE("Ciao, sono GuideMeRight e ti aiuterò a restare in contatto con la nostra vibrante community! :-)"),
	    CONTACT_DESCRIPTION("Ciao, sono GuideMeRight e ti aiuterò a restare in contatto con la nostra vibrante community! :-)");

	    private final String text;

	    private GMRCommunity(final String text) {
	        this.text = text;
	    }

	    @Override
	    public String toString() {
	        return text;
	    }
	}		

	public static enum GMRPromo{
		CONTACT_NUMBER("15"),
		CONTACT_NAME("Gmr Promo"),
		CONTACT_IMAGE_URL("/img/gmr-promo.png"),
		CONTACT_WELCOME_MESSAGE("Ciao, sono GuideMeRight e ti terrò aggiurnate sulle varie promozioni in corso! :-)"),
	    CONTACT_DESCRIPTION("Ciao, sono GuideMeRight e ti terrò aggiurnate sulle varie promozioni in corso! :-)");

	    private final String text;

	    private GMRPromo(final String text) {
	        this.text = text;
	    }

	    @Override
	    public String toString() {
	        return text;
	    }
	}		


}