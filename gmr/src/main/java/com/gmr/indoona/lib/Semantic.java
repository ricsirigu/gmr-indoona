package com.gmr.indoona.lib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Logger;


/**
 * Created by andreazanda on 11/11/15.
 * Edited By Riccardo Sirigu
 */
public class Semantic {

    private static final Logger log = Logger.getLogger(Semantic.class.getName());


    public static String extractSemantic(String usertext) {

        String jsonTxt = "";

        try {

            URL url = new URL("http://api.webofcode.org/wherenwhen?apikey=guidemeright.it_nVL0rvKWHXgDpUu3&lang=it&text=" + URLEncoder.encode(usertext, "UTF-8"));
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                jsonTxt = jsonTxt + line;
            }
            reader.close();


        } catch (MalformedURLException e) {
            log.severe(e.toString());
        } catch (IOException e) {
            log.severe(e.toString());
        }

        return jsonTxt;
    }

    public static String containsKeywords(String usertext) {
        String result = "none";

        if (usertext.toLowerCase().contains("siri")){
            result = "Sei proprio spiritoso!";
        }
        else if (usertext.toLowerCase().contains("scemo")){
            result = "Specchio riflesso!";
        }
        else if (usertext.toLowerCase().contains("prova")){
            result = "Funziono, non preoccuparti!";
        }
        else if (usertext.toLowerCase().contains("coglione")){
            result = "Sono una persona educata!";
        }
        else if (usertext.toLowerCase().contains("cavallo")){
            result = "Ti coddidi!";
        }
        else if (usertext.toLowerCase().contains("gmr")){
            result = "Eccomi, cosa vorresti sapere?";
        } 
        else if (usertext.toLowerCase().contains("uber")){
            result = "Hem.. no, non siamo l'uber delle guide.";
        }
        else if (usertext.toLowerCase().contains("google")){
            result = "ahahha, volevi dire istella?";
        }

        return result;
    }


}
