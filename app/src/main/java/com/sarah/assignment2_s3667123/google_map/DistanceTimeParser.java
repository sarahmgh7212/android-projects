package com.sarah.assignment2_s3667123.google_map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DistanceTimeParser {

    public List<HashMap<String, String>> parse(JSONObject jObject) {


        List<HashMap<String, String>> routes = new ArrayList<HashMap<String, String>>();
        JSONArray jRoutes = null;
        JSONArray jLegs = null;

        JSONObject jDistance = null;
        JSONObject jDuration = null;

        try {

            jRoutes = jObject.getJSONArray("routes");

            jLegs = ((JSONObject) jRoutes.get(0)).getJSONArray("legs");

            List<HashMap<String, String>> path = new ArrayList<HashMap<String, String>>();


            /** Getting distance from the json data */
            jDistance = ((JSONObject) jLegs.get(0)).getJSONObject("distance");
            HashMap<String, String> hashmapDistance = new HashMap<String, String>();
            hashmapDistance.put("distance", jDistance.getString("text"));

            /** Getting duration from the json data */
            jDuration = ((JSONObject) jLegs.get(0)).getJSONObject("duration");
            HashMap<String, String> hmDuration = new HashMap<String, String>();
            hmDuration.put("duration", jDuration.getString("text"));

            routes.add(hashmapDistance);

            routes.add(hmDuration);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
        }

        return routes;
    }

}
