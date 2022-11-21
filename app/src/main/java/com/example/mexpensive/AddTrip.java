package com.example.mexpensive;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.mexpensive.Entities.Trip;
import com.example.mexpensive.Splite.TripDbHelpler;

import java.util.Calendar;

public class AddTrip extends AppCompatActivity {

    EditText startDate;
    EditText destination;
    EditText description;
    EditText endDate;
    Switch swRisk;
    EditText name;
    Context context;
    boolean isEdit = false;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);

        name = findViewById(R.id.tripname);
        destination = findViewById(R.id.inputDestination);
        startDate = findViewById(R.id.inputStartDate);
        description = findViewById(R.id.inputDescription);
        swRisk = findViewById(R.id.risk);
        swRisk.setTextOff("No");
        swRisk.setTextOn("Yes");
        swRisk.setShowText(true);
        startDate.setOnFocusChangeListener((view, b) -> {
            if(b){
                MyDatePicker dlg = new MyDatePicker();
                dlg.setExamDate(startDate);
                dlg.show(getSupportFragmentManager(),"dateTimePicker");

            }
        });
        endDate = findViewById(R.id.inputEndDate);
        endDate.setOnFocusChangeListener((view, b) -> {
            if(b){
                MyDatePicker dlg = new MyDatePicker();
                dlg.setExamDate(endDate);
                dlg.show(getSupportFragmentManager(),"dateTimePicker");
            }
        });
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Trip trip = new Trip();
                trip.setNameOfTrip(name.getText().toString());
                trip.setDestination(destination.getText().toString());
                trip.setStartDate(startDate.getText().toString());
                trip.setEndDate(endDate.getText().toString());
                trip.setRisk(swRisk.getText().toString());
                trip.setDescription(description.getText().toString());
                if(name.getText().toString().equals("") && destination.getText().toString().equals("") && startDate.getText().toString().equals("")){
                    Toast.makeText(AddTrip.this,"Please input information ",Toast.LENGTH_SHORT).show();
                } else{
                    TripDbHelpler tripDbHelper = new TripDbHelpler(AddTrip.this);
                    tripDbHelper.insertTrip(trip);
                    Toast.makeText(AddTrip.this,"Save",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static class MyDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener{
        public EditText getExamDate() {
            return examDate;
        }
        public void setExamDate(EditText examDate) {
            this.examDate = examDate;
        }
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(requireContext(), this, year, month, day);
        }

        EditText examDate;
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            String dateValue =  i2 + "/" + i1 + "/" + i;
            examDate.setText(dateValue);
        }
    }
}