package com.example.mexpensive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.mexpensive.Entities.Trip;
import com.example.mexpensive.Splite.TripDAO;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class DetailTrip extends AppCompatActivity {
    TextView tvName,tvDest,tvSt,tvEn,tvRik,tvVeh, tvDesc;
    Button btnDel;
    private List<Trip> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_trip);
        Intent intent = getIntent();

        tvName = findViewById(R.id.tvTripName);
        tvDest = findViewById(R.id.tvDestination);
        tvSt = findViewById(R.id.tvStartDate);
        tvEn = findViewById(R.id.tvEndD);
        tvVeh = findViewById(R.id.tvVehicle);
        tvDesc = findViewById(R.id.tvDescp);

        TripDAO dao = new TripDAO(this);
        btnDel = findViewById(R.id.btnDel);
        btnDel.setOnClickListener(v -> {
            String name = tvName.getText().toString();
            dao.delete(name);

            Snackbar snackbar = Snackbar.make(v, "Delete Successfully", Snackbar.LENGTH_SHORT);
            snackbar.show();
            tvName.setText("");
            tvDest.setText("");
            tvSt.setText("");
            tvEn.setText("");
            //tvRik.setText("");
            tvVeh.setText("");
            tvDesc.setText("");
        });

        String TripNames =  intent.getStringExtra("name");
        String Desti = intent.getStringExtra("destination");
        String Date = intent.getStringExtra("startDate");
        String DateEnds = intent.getStringExtra("endDate");
        //String Risk = intent.getStringExtra("risk");
        String Trans = intent.getStringExtra("transportation");
        String Descrip = intent.getStringExtra("description");

        tvName.setText(TripNames);
        tvDest.setText(Desti);
        tvSt.setText(Date);
        tvEn.setText(DateEnds);
        //tvRik.setText(Risk);
        tvVeh.setText(Trans);
        tvDesc.setText(Descrip);
    }
}