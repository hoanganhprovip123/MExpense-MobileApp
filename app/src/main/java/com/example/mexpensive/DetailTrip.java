package com.example.mexpensive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.mexpensive.Entities.Trip;
import com.example.mexpensive.Splite.TripDbHelpler;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class DetailTrip extends AppCompatActivity {
    TextView viewNameOfTrip,viewDest,viewStd,viewEnd,viewRisk,viewDesc;
    Button btnDel;
    private List<Trip> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_trip);
        Intent intent = getIntent();

        viewNameOfTrip = findViewById(R.id.TripName);
        viewDest = findViewById(R.id.Dest);
        viewStd = findViewById(R.id.StDate);
        viewEnd = findViewById(R.id.EndD);
        viewRisk = findViewById(R.id.Risk);
        viewDesc = findViewById(R.id.Desc);

        TripDbHelpler tripDbHelpler = new TripDbHelpler(this);
        btnDel = findViewById(R.id.btnDeleteTr);
        btnDel.setOnClickListener(v -> {
            String name = viewNameOfTrip.getText().toString();
            tripDbHelpler.delete(name);

            Snackbar snackbar = Snackbar.make(v, "Delete Successfully", Snackbar.LENGTH_SHORT);
            snackbar.show();
            viewNameOfTrip.setText("");
            viewDest.setText("");
            viewStd.setText("");
            viewEnd.setText("");
            viewRisk.setText("");
            viewDesc.setText("");
        });

        String TripNames =  intent.getStringExtra("name");
        String Dest = intent.getStringExtra("destination");
        String DateStarts = intent.getStringExtra("startDate");
        String DateEnds = intent.getStringExtra("endDate");
        String Risk = intent.getStringExtra("risk");
        String Desc = intent.getStringExtra("description");

        viewNameOfTrip.setText(TripNames);
        viewDest.setText(Dest);
        viewStd.setText(DateStarts);
        viewEnd.setText(DateEnds);
        viewRisk.setText(Risk);
        viewDesc.setText(Desc);
    }
}