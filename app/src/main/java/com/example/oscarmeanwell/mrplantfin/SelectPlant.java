package com.example.oscarmeanwell.mrplantfin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class SelectPlant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTitle("Mr Plant");
        setContentView(R.layout.activity_select_plant);
        final Button floral = (Button) findViewById(R.id.floral);
        final Button cacti = (Button) findViewById(R.id.cacti);
        final Button lowlight = (Button) findViewById(R.id.lowlight);
        final Button foil = (Button) findViewById(R.id.foliage);

        floral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectPlant.this, ViewPlant.class);
                intent.putExtra("plant", "Floral");
                startActivity(intent);
            }
        });

        cacti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectPlant.this, ViewPlant.class);
                intent.putExtra("plant", "Cacti");
                startActivity(intent);
            }
        });

        lowlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectPlant.this, ViewPlant.class);
                intent.putExtra("plant", "Low Light");
                startActivity(intent);
            }
        });
        foil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectPlant.this, ViewPlant.class);
                intent.putExtra("plant", "Foliage");
                startActivity(intent);
            }
        });

    }


}