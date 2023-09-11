package com.example.thechamber.utilities;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class SpinnerHelper {

    public static void setupSpinner(Context context, Spinner spinner, List<Countries> countryList) {
        ArrayAdapter<Countries> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, countryList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Countries selectedCountry = countryList.get(position);
                String selectedCode = selectedCountry.getCode();
                String selectedFlag = selectedCountry.getFlag();
                Toast.makeText(context, "Seçilen ülke kodu: " + selectedCode + "\nBayrak: " + selectedFlag, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Hiçbir şey seçilmediğinde yapılacak işlemler burada
            }
        });
    }


}
