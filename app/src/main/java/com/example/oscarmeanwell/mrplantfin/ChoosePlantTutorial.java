package com.example.oscarmeanwell.mrplantfin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ChoosePlantTutorial extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_choose_plant_tutorial, container, false);
        //Intent select= new Intent(getActivity(), SelectPlant.class);
        //startActivity(select);
    }
}