package com.example.mexpensive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mexpensive.Entities.Trip;
import com.example.mexpensive.Splite.TripDbHelpler;

import java.util.List;

public class ShowTrip extends AppCompatActivity {
    private ListView listTrip;
    private List<Trip> trips;
    private  TripAdapter tripAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_trip);
        listTrip = (ListView) findViewById(R.id.listTrip);

        TripDbHelpler tripDbHelpler =new TripDbHelpler(this);
        List<Trip> trip = tripDbHelpler.getTrip();
        fillTripToLv();

        listTrip.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Trip selectedTrip = trip.get(position);
                openTripDetail(selectedTrip);
            }
        });
    }
    private void openTripDetail(Trip selectedTrip) {
        Intent intent = new Intent(ShowTrip.this, DetailTrip.class);
        intent.putExtra("name", selectedTrip.getNameOfTrip());
        intent.putExtra("destination", selectedTrip.getDestination());
        intent.putExtra("startDate", selectedTrip.getStartDate());
        intent.putExtra("endDate", selectedTrip.getEndDate());
        intent.putExtra("risk", selectedTrip.getRisk());
        intent.putExtra("description", selectedTrip.getDescription());

        startActivity(intent);
    }

    private void fillTripToLv() {
        TripDbHelpler tripDbHelpler = new TripDbHelpler(this);
        trips = tripDbHelpler.getTrip();
        tripAdapter = new TripAdapter(this, trips);

        listTrip.setAdapter(tripAdapter);
    }
}