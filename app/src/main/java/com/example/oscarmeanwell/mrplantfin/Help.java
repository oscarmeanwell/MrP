package com.example.oscarmeanwell.mrplantfin;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Help extends AppCompatActivity {

    TextView title;
    TextView content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        title = (TextView)findViewById(R.id.titleq);
        content = (TextView)findViewById(R.id.txtShow);
        String plant = getIntent().getStringExtra("type");
        title.setText("Help: "+plant + " Plants");

        Double tm = ViewPlant.levels.get(plant).get("temp").get("min");
        Double tmm = ViewPlant.levels.get(plant).get("temp").get("max");
        Double ti = ViewPlant.levels.get(plant).get("temp").get("ideal");

        Double hm = ViewPlant.levels.get(plant).get("hum").get("min");
        Double hmm = ViewPlant.levels.get(plant).get("hum").get("max");
        Double hi = ViewPlant.levels.get(plant).get("hum").get("ideal");

        Double sm = ViewPlant.levels.get(plant).get("soil").get("min");
        Double smm = ViewPlant.levels.get(plant).get("soil").get("max");
        Double si = ViewPlant.levels.get(plant).get("soil").get("ideal");


        String Temp = plant + " Temperature" + System.lineSeparator()
                + "\t\tMin: " + tm.toString() + System.lineSeparator()
                + "\t\tMax: " + tmm.toString() + System.lineSeparator()
                + "\t\tIdeal: "  + ((Double) (ti-5)).toString() + " - " + ((Double) (ti+5)).toString() + System.lineSeparator() + System.lineSeparator()
                +  plant + " Humidity" + System.lineSeparator()
                + "\t\tMin: " + hm.toString() + System.lineSeparator()
                + "\t\tMax: " + hmm.toString() + System.lineSeparator()
                + "\t\tIdeal: "  + ((Double) (hi-5)).toString() + " - " + ((Double) (hi+5)).toString() + System.lineSeparator() + System.lineSeparator()
                +  plant + " Soil Humidity" + System.lineSeparator()
                + "\t\tMin: " + sm.toString() + System.lineSeparator()
                + "\t\tMax: " + smm.toString() + System.lineSeparator()
                + "\t\tIdeal: "  + ((Double) (si-5)).toString() + " - " + ((Double) (si+5)).toString() + System.lineSeparator() + System.lineSeparator()
                ;

        content.setText(Temp);
    }
}