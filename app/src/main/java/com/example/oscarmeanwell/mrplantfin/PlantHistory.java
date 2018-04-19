package com.example.oscarmeanwell.mrplantfin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.FileInputStream;
import java.util.Arrays;

public class PlantHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_history);

        GraphView graph = (GraphView) findViewById(R.id.graph);

        //graph.getViewport().setYAxisBoundsManual(true);
        //graph.getViewport().setMinY(0.0);
        String line2 = "";
        try {
            FileInputStream br = openFileInput("temp.txt");
            int x;
            while ((x = br.read()) != -1) {
                line2 = line2 + Character.toString((char) x);
            }
        }catch (Exception e){
            ;
        }
        String [] data2 = line2.split(",");

        DataPoint [] array = new DataPoint[data2.length];
        int count = 0;
        for(String x : data2){
            array[count] = new DataPoint(count, Integer.parseInt(data2[count].substring(0,2)));
            count += 1;

        }
        System.out.println(count);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(array);
        graph.addSeries(series);
    }
}
