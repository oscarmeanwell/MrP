package com.example.oscarmeanwell.mrplantfin;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;

public class ViewPlant extends AppCompatActivity {

    TextView txtTemp;
    TextView txtHum;
    TextView txtSoil;
    TextView txtHeader;
    TextView txtUpdate;
    double temp;
    double hum;
    double soil;
    String plantType;
    HashMap<String, HashMap<String, HashMap<String, Double>>> levels = new HashMap<>();
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
        //get data from server
        HashMap<String, String> data = new GetServerData().GetServerDataNow();
        //set int variables
        temp = Double.parseDouble(data.get("temp"));
        hum = Double.parseDouble(data.get("room_humidity"));
        soil = Double.parseDouble(data.get("humidity"));
        //Set textViews content
        txtTemp.setText("Temperature: " + temp);
        txtHum.setText("Humidity: " + hum);
        txtSoil.setText("Soil Humidity: " + soil);
        txtUpdate.setText("Last updated: " + data.get("datetime").toString());
        //this will look at hardcoded values

        setTemperatureColor();
        setHumidityColor();
        setSoilHumidityColor();

        plantType = getIntent().getStringExtra("plant");
        txtHeader.setText(plantType + " Levels");

    }

    public void setTemperatureColor(){
        if (temp < 15 && temp > 0 ){
            //This is green so ideal temperature conditions
            txtTemp.setBackgroundColor(Color.parseColor("#008000"));
        }
        else if (temp  > 14 && temp < 30 ){
            //This is amber
            txtTemp.setBackgroundColor(Color.parseColor("#FFC200"));
        }
        else{
            txtTemp.setBackgroundColor(Color.parseColor("#FF0000"));
        }
    }

    public void setHumidityColor(){
        if (hum < 15 && hum > 0 ){
            //This is green so ideal temperature conditions
            txtHum.setBackgroundColor(Color.parseColor("#008000"));
        }
        else if (hum  > 14 && hum < 30 ){
            //This is amber
            txtHum.setBackgroundColor(Color.parseColor("#FFC200"));
        }
        else{
            txtHum.setBackgroundColor(Color.parseColor("#FF0000"));
        }
    }

    public void setSoilHumidityColor(){
        if (soil < 15 && soil > 0 ){
            //This is green so ideal temperature conditions
            txtSoil.setBackgroundColor(Color.parseColor("#008000"));
        }
        else if (soil  > 14 && soil < 30 ){
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
    }

    public void buildCactiHash(){
        //Build cacti now
        HashMap<String, HashMap<String, Double>> tmpCacti = new HashMap<>();
        //Build Temp
        HashMap<String, Double> tmpCactiTemp = new HashMap<>();
        tmpCactiTemp.put("min", 0.0);
        tmpCactiTemp.put("ideal", 10.0);
        tmpCactiTemp.put("max", 20.0);
        tmpCacti.put("temp", tmpCactiTemp);
        HashMap<String, Double> tmpCactiHum = new HashMap<>();
        tmpCactiHum.put("min", 2.0);
        tmpCactiHum.put("ideal", 40.0);
        tmpCactiHum.put("max", 80.0);
        tmpCacti.put("hum", tmpCactiHum);
        HashMap<String, Double> tmpCactiSoil = new HashMap<>();
        tmpCactiSoil.put("min", 0.0);
        tmpCactiSoil.put("ideal", 10.0);
        tmpCactiSoil.put("max", 20.0);
        tmpCacti.put("soil", tmpCactiSoil);
        levels.put("cacti", tmpCacti);
        System.out.println("HEHHE: " + levels.get("cacti").get("temp").get("min"));
    }
    public void buildFloralHash(){
        //Build cacti now
        HashMap<String, HashMap<String, Double>> tmpFoloral = new HashMap<>();
        //Build Temp
        HashMap<String, Double> tmpTemp = new HashMap<>();
        tmpTemp.put("min", 0.0);
        tmpTemp.put("ideal", 10.0);
        tmpTemp.put("max", 20.0);
        tmpFoloral.put("temp", tmpTemp);
        HashMap<String, Double> tmpHum = new HashMap<>();
        tmpHum.put("min", 2.0);
        tmpHum.put("ideal", 40.0);
        tmpHum.put("max", 80.0);
        tmpFoloral.put("hum", tmpHum);
        HashMap<String, Double> tmpSoil = new HashMap<>();
        tmpSoil.put("min", 0.0);
        tmpSoil.put("ideal", 10.0);
        tmpSoil.put("max", 20.0);
        tmpFoloral.put("soil", tmpSoil);

        levels.put("Floral", tmpFoloral);
        System.out.println("HEHHE: " + levels.get("Floral").get("temp").get("min"));
    }
    public void buildFoliageHash(){
        //Build cacti now
        HashMap<String, HashMap<String, Double>> tmpFoloral = new HashMap<>();
        //Build Temp
        HashMap<String, Double> tmpTemp = new HashMap<>();
        tmpTemp.put("min", 0.0);
        tmpTemp.put("ideal", 10.0);
        tmpTemp.put("max", 20.0);
        tmpFoloral.put("temp", tmpTemp);
        HashMap<String, Double> tmpHum = new HashMap<>();
        tmpHum.put("min", 2.0);
        tmpHum.put("ideal", 40.0);
        tmpHum.put("max", 80.0);
        tmpFoloral.put("hum", tmpHum);
        HashMap<String, Double> tmpSoil = new HashMap<>();
        tmpSoil.put("min", 0.0);
        tmpSoil.put("ideal", 10.0);
        tmpSoil.put("max", 20.0);
        tmpFoloral.put("soil", tmpSoil);

        levels.put("Foliage", tmpFoloral);
        System.out.println("HEHHE: " + levels.get("Foliage").get("temp").get("min"));
    }
}
