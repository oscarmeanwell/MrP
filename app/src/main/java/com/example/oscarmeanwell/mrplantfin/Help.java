package com.example.oscarmeanwell.mrplantfin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Help extends AppCompatActivity {

    TextView title;
    TextView content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        title = (TextView)findViewById(R.id.titleq);
        content = (TextView)findViewById(R.id.txtShow);
        title.setText("Help: "+getIntent().getStringExtra("type") + " Plants");

        Double tm = ViewPlant.levels.get(getIntent().getStringExtra("type")).get("temp").get("min");
        Double tmm = ViewPlant.levels.get(getIntent().getStringExtra("type")).get("temp").get("max");
        Double ti = ViewPlant.levels.get(getIntent().getStringExtra("type")).get("temp").get("ideal");

        Double hm = ViewPlant.levels.get(getIntent().getStringExtra("type")).get("hum").get("min");
        Double hmm = ViewPlant.levels.get(getIntent().getStringExtra("type")).get("hum").get("max");
        Double hi = ViewPlant.levels.get(getIntent().getStringExtra("type")).get("hum").get("ideal");

        Double sm = ViewPlant.levels.get(getIntent().getStringExtra("type")).get("soil").get("min");
        Double smm = ViewPlant.levels.get(getIntent().getStringExtra("type")).get("soil").get("max");
        Double si = ViewPlant.levels.get(getIntent().getStringExtra("type")).get("soil").get("ideal");


        String Temp = getIntent().getStringExtra("type") + " Temperature" + System.lineSeparator()
                + "\tMin: " + tm.toString() + System.lineSeparator()
                + "\tMax: " + tmm.toString() + System.lineSeparator()
                + "\tIdeal: "  + ti.toString() + " +- 5" + System.lineSeparator() + System.lineSeparator()
                +  getIntent().getStringExtra("type") + " Humidity" + System.lineSeparator()
                + "\tMin: " + hm.toString() + System.lineSeparator()
                + "\tMax: " + hmm.toString() + System.lineSeparator()
                + "\tIdeal: "  + hi.toString() + " +- 5" + System.lineSeparator() + System.lineSeparator()
                +  getIntent().getStringExtra("type") + " Soil Humidity" + System.lineSeparator()
                + "\tMin: " + sm.toString() + System.lineSeparator()
                + "\tMax: " + smm.toString() + System.lineSeparator()
                + "\tIdeal: "  + si.toString() + " +- 5" + System.lineSeparator() + System.lineSeparator()
                ;

        content.setText(Temp);
    }
}
