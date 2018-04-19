package com.example.oscarmeanwell.mrplantfin;

import android.os.StrictMode;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;

public class GetServerData extends HashMap<String, String> {
    public static HashMap GetServerDataNow(){
        JSONObject json = null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        HashMap<String, String> data = new HashMap<>();
        try {
            json = new JSONObject(IOUtils.toString(new URL("http://54.213.142.174/test.json"), Charset.forName("UTF-8")));
            data.put("temp", json.get("temp").toString());
            data.put("humidity", json.get("humidity").toString());
            data.put("datetime", json.get("datetime").toString());
            data.put("light", json.get("light").toString());
            data.put("room_humidity", json.get("room_humidity").toString());

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
