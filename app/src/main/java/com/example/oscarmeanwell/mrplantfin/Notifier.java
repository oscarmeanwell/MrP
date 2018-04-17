package com.example.oscarmeanwell.mrplantfin;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import static android.support.v7.appcompat.R.id.time;

public class Notifier extends Service {

    Handler handler = new Handler();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String line = "";
        try {
            FileInputStream br = openFileInput("type.txt");
            int x;
            while ((x = br.read()) != -1) {
                line = line + Character.toString((char) x);
            }
        }catch (Exception e){
            ;
        }

        final String finalLine = line;
        handler.postDelayed(new Runnable() {
            public void run() {
                System.out.println("STARTED");
                //read from file default plant type
                String type = "";
                if (finalLine.equals("")){
                    type = "Cacti";
                }else{
                    type = finalLine;
                }
                HashMap<String, String> data = new GetServerData().GetServerDataNow();
                Double temp = Double.parseDouble(data.get("temp"));
                Double hum = Double.parseDouble(data.get("room_humidity"));
                Double soil = Double.parseDouble(data.get("humidity"));

                if(temp < ViewPlant.levels.get(type).get("temp").get("min")) {
                    //issue temperature warning to cold
                    issueWarning("Your plant is to cold!");
                }
                else if (temp > ViewPlant.levels.get(type).get("temp").get("max")){
                    //issue temp to hot
                    issueWarning("Your plant is to hot!");
                }

                if (hum < ViewPlant.levels.get(type).get("hum").get("min")){
                    //Your plant needs more humidity!
                    issueWarning("Your plant needs more humidity!");
                }
                else if (hum > ViewPlant.levels.get(type).get("hum").get("max")){
                    //Your plant needs less humidity!
                    issueWarning("Your plant needs less humidity!");
                }
                if (soil < ViewPlant.levels.get(type).get("soil").get("min")){
                    //Your plant needs more water
                    issueWarning("Your plant needs more water!");
                }
                else if (soil > ViewPlant.levels.get(type).get("soil").get("max")) {
                    //Your plant needs dryer soil
                    issueWarning("Your plant is drowning!");
                }
                handler.postDelayed(this, 30000); //now is every 15 minutes
            }
        }, 0);
        return START_STICKY;
    }

    public void issueWarning(String message){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher_foreground);
        mBuilder.setContentTitle("Mr. Plant");
        mBuilder.setContentText(message);
        mBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());
    }
}
