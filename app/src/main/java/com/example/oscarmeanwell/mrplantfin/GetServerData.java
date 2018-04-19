package com.example.oscarmeanwell.mrplantfin;

import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;

public class GetServerData extends HashMap<String, String> {
    @RequiresApi(api = 26)
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

        //we should now write this to a file.
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("/data/user/0/com.example.oscarmeanwell.mrplantfin/files/temp.txt", true)));
            out.print(data.get("temp").toString()+",");
            out.close();
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
            System.out.println("FAiled");
        }
        return data;
    }
}
