package com.example.mexpensive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAddTrip = findViewById(R.id.btnTrip);
        btnAddTrip.setOnClickListener(View -> {
            startActivity(new Intent(MainActivity.this, AddTrip.class));
        });

        Button btnShowTrip = findViewById(R.id.btnShow);
        btnShowTrip.setOnClickListener((View ->{
            startActivity(new Intent(MainActivity.this, ShowTrip.class));
        }));

        Button btnCost = findViewById((R.id.btnExp));
        btnCost.setOnClickListener((View -> {
            startActivity((new Intent(MainActivity.this, AddExpense.class)));
        }));
    }
}