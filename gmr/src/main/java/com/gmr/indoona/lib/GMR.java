package com.gmr.indoona.lib;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

/**
 * Created by andreazanda on 11/11/15.
 */
public class GMR {

    private final static String GmrURI = "https://www.guidemeright.com/";

    public static String getActivities(String lat, String lon, String date) {

        String jsonTxt = "";
        String response = "";
        Random randomGen  = new Random();

        try {
            URL url = new URL(GmrURI + "api/geoActivities?lat="+ lat + "&lon=" + lon + "&exp=all&sty=all&lan=all&bad=all&key=android");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                jsonTxt = jsonTxt + line;
            }
            reader.close();
            JSONArray jsonarray = JSONArray.fromObject(jsonTxt);

            int randomExp;

            for (int i=0; i<2; i++) {

                randomExp = randomGen.nextInt(jsonarray.size());

                JSONObject jsobj = (JSONObject) jsonarray.get(randomExp);
                String title = jsobj.getString("name");
                String link = GmrURI + "activity/" + jsobj.getString("seoUrlIT");

                response = response + title + " - " + link + "\n";
            }

        } catch (MalformedURLException e) {
            // ...
        } catch (IOException e) {
            // ...
        }

        return response;
    }


}
