package com.example.thechamber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.thechamber.utilities.Countries;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoginActivity extends AppCompatActivity {


    private Spinner spinner;
    private List<Countries> countryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        spinner = findViewById(R.id.spinner);

        // JSON dosyasını oku ve ülke listesini oluştur
        countryList = new ArrayList<>();
        loadCountriesFromJson();

        // Spinner'ı doldur
        ArrayAdapter<Countries> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countryList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Countries selectedCountry = countryList.get(position);
                String selectedCode = selectedCountry.getCode();
                String selectedFlag = selectedCountry.getFlag();
                Toast.makeText(getApplicationContext(), "Seçilen ülke kodu: " + selectedCode + "\nBayrak: " + selectedFlag, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    private void loadCountriesFromJson() {
        try {
            // JSON dosyasını oku
            InputStream inputStream = getResources().openRawResource(R.raw.country_numbers);
            Scanner scanner = new Scanner(inputStream);
            StringBuilder stringBuilder = new StringBuilder();

            while (scanner.hasNextLine()) {
                stringBuilder.append(scanner.nextLine());
            }

            // JSON verilerini çözümle
            JSONArray jsonArray = new JSONArray(stringBuilder.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String flag = jsonObject.getString("flag");
                String code = jsonObject.getString("code");
                String dialCode = jsonObject.getString("dial_code");
                String pattern = jsonObject.getString("pattern");
                int limit = jsonObject.getInt("limit");

                // Country nesnesini oluştur ve listeye ekle
                Countries country = new Countries(id, name, flag, code, dialCode, pattern, limit);
                countryList.add(country);
            }

            scanner.close();
            inputStream.close();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}