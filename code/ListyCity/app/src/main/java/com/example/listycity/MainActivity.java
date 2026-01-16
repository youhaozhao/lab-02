package com.example.listycity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    Button addCityButton;
    Button deleteCityButton;
    EditText inputCity;
    Button confirmButton;
    LinearLayout newCityLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        addCityButton = findViewById(R.id.add_city);
        deleteCityButton = findViewById(R.id.delete_city);
        inputCity = findViewById(R.id.input_city);
        confirmButton = findViewById(R.id.confirm_button);
        newCityLayout = findViewById(R.id.new_city_layout);

        // String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        String[] cities = {"Edmonton", "Montréal"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        addCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newCityLayout.setVisibility(View.VISIBLE);
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newCity = inputCity.getText().toString().trim();
                // Add new city if and only if the String is not empty string
                if (!newCity.isEmpty()) {
                    inputCity.setText("");
                    newCityLayout.setVisibility(View.INVISIBLE);
                    cityAdapter.add(newCity);
                }
            }
        });

        final int[] selectedPosition = {-1};

        // ListView listener to record the click of city
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition[0] = position;
            }
        });

        // Once the deleteCityButton is clicked, delete the city at that index if index is not -1
        deleteCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition[0] != -1) {
                    dataList.remove(selectedPosition[0]);
                    cityAdapter.notifyDataSetChanged();
                    selectedPosition[0] = -1;
                }
            }
        });
    }
}