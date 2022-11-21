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
    TextView vName, vDest, vStd, vEnd, vRisk, vDesc;
    Button btnDel;
    private List<Trip> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_trip);
        Intent intent = getIntent();

        vName = findViewById(R.id.TripName);
        vDest = findViewById(R.id.Dest);
        vStd = findViewById(R.id.StDate);
        vEnd = findViewById(R.id.EndD);
        vRisk = findViewById(R.id.Risk);
        vDesc = findViewById(R.id.Desc);

        TripDbHelpler tripDbHelpler = new TripDbHelpler(this);
        btnDel = findViewById(R.id.btnDeleteTr);
        btnDel.setOnClickListener(v -> {
            String name = vName.getText().toString();
            tripDbHelpler.delete(name);

            Snackbar snackbar = Snackbar.make(v, "Delete Successfully", Snackbar.LENGTH_SHORT);
            snackbar.show();
            vName.setText("");
            vDest.setText("");
            vStd.setText("");
            vEnd.setText("");
            vRisk.setText("");
            vDesc.setText("");
        });

        String TripNames =  intent.getStringExtra("name");
        String Dest = intent.getStringExtra("destination");
        String StDate = intent.getStringExtra("startDate");
        String EnDate = intent.getStringExtra("endDate");
        String Risk = intent.getStringExtra("risk");
        String Desc = intent.getStringExtra("description");

        vName.setText(TripNames);
        vDest.setText(Dest);
        vStd.setText(StDate);
        vEnd.setText(EnDate);
        vRisk.setText(Risk);
        vDesc.setText(Desc);
    }
}