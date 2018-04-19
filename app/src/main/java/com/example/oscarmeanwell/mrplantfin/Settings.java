package com.example.oscarmeanwell.mrplantfin;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        //here users will be able to set there default plant type.
        //Write it to a file, read from the file.
        final Switch switch1 = (Switch)findViewById(R.id.notificationsSwitch);
        final Spinner spinner = (Spinner) findViewById(R.id.plant_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.plant_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        if (checkNot().equals("1")){
            //this means notifications should be on
            switch1.setChecked(true);
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println(spinner.getSelectedItem().toString());
                try{
                    FileOutputStream outputStream = openFileOutput("type.txt", Context.MODE_PRIVATE);
                    outputStream.write(spinner.getSelectedItem().toString().getBytes());
                    outputStream.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (switch1.isChecked()){
                    //if notifications should be received
                    try{
                        FileOutputStream outputStream = openFileOutput("not.txt", Context.MODE_PRIVATE);
                        outputStream.write("1".toString().getBytes());
                        outputStream.close();
                    } catch (Exception e){
                        e.printStackTrace();
                    }

                }
                else{
                    try{
                        FileOutputStream outputStream = openFileOutput("not.txt", Context.MODE_PRIVATE);
                        outputStream.write("0".toString().getBytes());
                        outputStream.close();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });

    }
    public String checkNot(){
        String line2 = "";
        try {
            FileInputStream br = openFileInput("not.txt");
            int x;
            while ((x = br.read()) != -1) {
                line2 = line2 + Character.toString((char) x);
            }
        }catch (Exception e){
            ;
        }
        return line2;
    }
}
