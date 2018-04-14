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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        txtHeader.setText("Cacti Levels");

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
}
