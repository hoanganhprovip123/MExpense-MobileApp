package com.example.mexpensive;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mexpensive.Entities.Trip;

import java.util.List;

public class ShowTrip extends AppCompatActivity {
    private ListView listTripView;
    private List<Trip> list;
    TextView trName, trDest, trStd, trEd, trVeh, tcDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_trip);
    }
}