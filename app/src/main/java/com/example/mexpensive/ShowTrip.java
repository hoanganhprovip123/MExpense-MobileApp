package com.example.mexpensive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mexpensive.Entities.Trip;
import com.example.mexpensive.Splite.TripDAO;

import java.util.List;

public class ShowTrip extends AppCompatActivity {
    private ListView listTripView;
    private List<Trip> list;
    TextView trName, trDest, trStd, trEd, trVeh, tcDesc;
    private  TripAdapter tripAdapter;
    private TripDAO tripDAO;
    final int RESULT_PRODUCT_ACTIVITY= 1;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_trip);
        listTripView =findViewById(R.id.listviewTrip);

        TripDAO dao =new TripDAO(this);
        List<Trip> trip = dao.getTrip();
        fillTripToLv();

        listTripView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Trip selectedTrip = trip.get(position);
                openTripDetail(selectedTrip);
            }
        });
    }

    private void openTripDetail(Trip selectedTrip) {
        Intent intent = new Intent(ShowTrip.this, DetailTrip.class);
        intent.putExtra("name", selectedTrip.getTripName());
        intent.putExtra("destination", selectedTrip.getDestination());
        intent.putExtra("startDate", selectedTrip.getStartDate());
        intent.putExtra("endDate", selectedTrip.getEndDate());
        //intent.putExtra("risk", selectedTrip.getRisk());
        intent.putExtra("vehicle", selectedTrip.getVehicle());
        intent.putExtra("description", selectedTrip.getDescription());
        startActivity(intent);
    }

    private void fillTripToLv() {
        TripDAO dao = new TripDAO(this);
        list = dao.getTrip();
        tripAdapter = new TripAdapter(this, list);

        listTripView.setAdapter(tripAdapter);
    }
}