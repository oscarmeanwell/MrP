package com.example.oscarmeanwell.mrplantfin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Tutorial extends AppCompatActivity {
    int count;
    @Override
    @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        super.setTitle("Mr Plant");
        count = 0;
        setContentView(R.layout.activity_tutorial);
        final TextView textView = (TextView)findViewById(R.id.txtSwipe);
        final ImageView imageView = (ImageView) findViewById(R.id.swipeLeft);
        RelativeLayout myLayout = (RelativeLayout)findViewById(R.id.activity_tutorial);
        Intent selection = new Intent(Tutorial.this, SelectPlant.class);
        startActivity(selection);
        myLayout.setOnTouchListener(new OnSwipeTouchListener(Tutorial.this) {
            public void onSwipeTop() {

            }
            public void onSwipeRight() {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                if (count == 0 || count == 1){
                    Toast.makeText(Tutorial.this, "Please swipe left", Toast.LENGTH_SHORT).show();
                }
                else if(count == 2){
                    ft.replace(R.id.fragment_container, new PlaceSensors());
                    ft.commit();
                    count = 1;
                }
                else if (count == 3){
                    ft.replace(R.id.fragment_container, new ChoosePlantTutorial());
                    ft.commit();
                    count = 2;
                }
            }
            public void onSwipeLeft() {
                System.out.println("Count is: "+count);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                if (count==0){
                    imageView.setVisibility(View.INVISIBLE);
                    textView.setVisibility(View.INVISIBLE);
                    ft.replace(R.id.fragment_container, new PlaceSensors());
                    ft.commit();
                    count = 1;
                }
                else if (count == 1){
                    ft.replace(R.id.fragment_container, new ChoosePlantTutorial());
                    ft.commit();
                    count = 2;
                }
                else if (count == 2){
                    ft.replace(R.id.fragment_container, new LetsStartTutorial());
                    ft.commit();
                    count = 3;
                }
                else if (count == 3){
                    Intent intent = new Intent(Tutorial.this, SelectPlant.class);
                    startActivity(intent);
                    count = 0;
                }
            }
            public void onSwipeBottom() {
            }
        });
    }
}
