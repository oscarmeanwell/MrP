package com.example.oscarmeanwell.mrplantfin;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;

public class ViewPlant extends AppCompatActivity {

    TextView txtTemp;
    TextView txtHum;
    TextView txtSoil;
    TextView txtHeader;
    TextView txtUpdate;
    Button btn, help;
    double temp;
    double hum;
    double soil;
    public static String oldPlant = "";
    public static int flag = 0;
    String plantType;
    public static HashMap<String, HashMap<String, HashMap<String, Double>>> levels = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        buildHash();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_plant);
        //Assign the text views
        txtTemp = (TextView)findViewById(R.id.txtTemp);
        txtHum = (TextView)findViewById(R.id.txtHum);
        txtSoil = (TextView)findViewById(R.id.txtSoil);
        txtHeader = (TextView)findViewById(R.id.headertxt1);
        txtUpdate = (TextView)findViewById(R.id.lastUpdated);
        btn = (Button)findViewById(R.id.btn);
        help = (Button)findViewById(R.id.btnHelp);
        //get data from server

        if (flag == 0){
            plantType = getIntent().getStringExtra("plant");
        }else{
            flag = 0;
            plantType = oldPlant;
            oldPlant = "";
        }

        getRefresh();
        txtHeader.setText(plantType + " Levels");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRefresh();
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Help button pressed
                flag = 1;
                oldPlant = plantType;
                startActivity(new Intent(ViewPlant.this, Help.class).putExtra("type", plantType));
            }
        });
        //Water Your plant Notification
        //issueWarning("Water your plant!");


        startService(new Intent(this, Notifier.class));

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
    public void getRefresh(){
        HashMap<String, String> data = new GetServerData().GetServerDataNow();
        //set int variables
        try{
            temp = Double.parseDouble(data.get("temp"));
            hum = Double.parseDouble(data.get("room_humidity"));
            soil = Double.parseDouble(data.get("humidity"));
            txtUpdate.setText("Last updated: " + data.get("datetime").toString());
        }catch (Exception e){
            temp = 0;
            hum = 0;
            soil = 0;
        }
        //Set textViews content
        txtTemp.setText("Temperature: " + temp);
        txtHum.setText("Humidity: " + hum);
        txtSoil.setText("Soil Humidity: " + soil);

        //this will look at hardcoded values
        setTemperatureColor();
        setHumidityColor();
        setSoilHumidityColor();
    }

    public void setTemperatureColor(){
        if (temp > levels.get(plantType).get("temp").get("ideal")-5.0 && temp < levels.get(plantType).get("temp").get("ideal")+5.0 ){
            //Is temp 5 degreese less or more than optimum
            //This is green so ideal temperature conditions
            txtTemp.setBackgroundColor(Color.parseColor("#008000"));
        }
        else if (temp > levels.get(plantType).get("temp").get("min") && temp < levels.get(plantType).get("temp").get("max")){
            //This is amber
            //More than the min, less than the max
            txtTemp.setBackgroundColor(Color.parseColor("#FFC200"));
        }
        else{
            txtTemp.setBackgroundColor(Color.parseColor("#FF0000"));
        }
    }
    public void setHumidityColor(){
        if (hum > levels.get(plantType).get("hum").get("ideal")-5.0 && hum < levels.get(plantType).get("hum").get("ideal")+5.0){
            //This is green so ideal temperature conditions
            //5 less or more than ideal
            txtHum.setBackgroundColor(Color.parseColor("#008000"));
        }
        else if (hum > levels.get(plantType).get("hum").get("min") && hum < levels.get(plantType).get("hum").get("max")){
            //This is amber
            txtHum.setBackgroundColor(Color.parseColor("#FFC200"));
        }
        else{
            txtHum.setBackgroundColor(Color.parseColor("#FF0000"));
        }
    }

    public void setSoilHumidityColor(){
        if (soil > levels.get(plantType).get("soil").get("ideal")-5.0 && hum < levels.get(plantType).get("soil").get("ideal")+5.0){
            //This is green so ideal temperature conditions
            txtSoil.setBackgroundColor(Color.parseColor("#008000"));
        }
        else if (soil > levels.get(plantType).get("soil").get("ideal") && hum < levels.get(plantType).get("soil").get("ideal")){
            //This is amber
            txtSoil.setBackgroundColor(Color.parseColor("#FFC200"));
        }
        else{
            txtSoil.setBackgroundColor(Color.parseColor("#FF0000"));
        }
    }

    public void buildHash(){
        buildCactiHash();
        buildFloralHash();
        buildFoliageHash();
        buildlowlightHash();
    }

    public void buildCactiHash(){
        //Build cacti now
        HashMap<String, HashMap<String, Double>> tmpCacti = new HashMap<>();
        //Build Temp
        HashMap<String, Double> tmpCactiTemp = new HashMap<>();
        tmpCactiTemp.put("min", 7.0);
        tmpCactiTemp.put("ideal", 40.0);
        tmpCactiTemp.put("max", 50.0);
        tmpCacti.put("temp", tmpCactiTemp);
        HashMap<String, Double> tmpCactiHum = new HashMap<>();
        tmpCactiHum.put("min", 15.0);
        tmpCactiHum.put("ideal", 25.0);
        tmpCactiHum.put("max", 50.0);
        tmpCacti.put("hum", tmpCactiHum);
        HashMap<String, Double> tmpCactiSoil = new HashMap<>();
        tmpCactiSoil.put("min", 100.0);
        tmpCactiSoil.put("ideal", 120.0);
        tmpCactiSoil.put("max", 150.0);
        tmpCacti.put("soil", tmpCactiSoil);
        levels.put("Cacti", tmpCacti);
    }
    public void buildFloralHash(){
        //Build cacti now
        HashMap<String, HashMap<String, Double>> tmpFoloral = new HashMap<>();
        //Build Temp
        HashMap<String, Double> tmpTemp = new HashMap<>();
        tmpTemp.put("min", 2.0);
        tmpTemp.put("ideal", 13.0);
        tmpTemp.put("max", 20.0);
        tmpFoloral.put("temp", tmpTemp);
        HashMap<String, Double> tmpHum = new HashMap<>();
        tmpHum.put("min", 20.0);
        tmpHum.put("ideal", 30.0);
        tmpHum.put("max", 40.0);
        tmpFoloral.put("hum", tmpHum);
        HashMap<String, Double> tmpSoil = new HashMap<>();
        tmpSoil.put("min", 120.0);
        tmpSoil.put("ideal", 180.0);
        tmpSoil.put("max", 220.0);
        tmpFoloral.put("soil", tmpSoil);

        levels.put("Floral", tmpFoloral);
        //System.out.println("HEHHE: " + levels.get("Floral").get("temp").get("min"));
    }
    public void buildFoliageHash(){
        //Build cacti now
        HashMap<String, HashMap<String, Double>> tmpFoloral = new HashMap<>();
        //Build Temp
        HashMap<String, Double> tmpTemp = new HashMap<>();
        tmpTemp.put("min", 1.0);
        tmpTemp.put("ideal", 10.0);
        tmpTemp.put("max", 15.0);
        tmpFoloral.put("temp", tmpTemp);
        HashMap<String, Double> tmpHum = new HashMap<>();
        tmpHum.put("min", 20.0);
        tmpHum.put("ideal", 25.0);
        tmpHum.put("max", 30.0);
        tmpFoloral.put("hum", tmpHum);
        HashMap<String, Double> tmpSoil = new HashMap<>();
        tmpSoil.put("min", 100.0);
        tmpSoil.put("ideal", 150.0);
        tmpSoil.put("max", 200.0);
        tmpFoloral.put("soil", tmpSoil);

        levels.put("Foliage", tmpFoloral);
    }

    public void buildlowlightHash(){
        //Build cacti now
        HashMap<String, HashMap<String, Double>> tmpLowLight = new HashMap<>();
        //Build Temp
        HashMap<String, Double> tmpTemp = new HashMap<>();
        tmpTemp.put("min", 5.0);
        tmpTemp.put("ideal", 10.0);
        tmpTemp.put("max", 20.0);
        tmpLowLight.put("temp", tmpTemp);
        HashMap<String, Double> tmpHum = new HashMap<>();
        tmpHum.put("min", 15.0);
        tmpHum.put("ideal", 25.0);
        tmpHum.put("max", 30.0);
        tmpLowLight.put("hum", tmpHum);
        HashMap<String, Double> tmpSoil = new HashMap<>();
        tmpSoil.put("min", 100.0);
        tmpSoil.put("ideal", 150.0);
        tmpSoil.put("max", 200.0);
        tmpLowLight.put("soil", tmpSoil);

        levels.put("Low Light", tmpLowLight);
    }


}
