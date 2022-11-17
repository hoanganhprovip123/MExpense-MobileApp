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

import com.example.mexpensive.Entities.Expense;
import com.example.mexpensive.Entities.Trip;
import com.example.mexpensive.Splite.TripDAO;

import java.util.Calendar;
import java.util.List;

public class AddTrip extends AppCompatActivity implements View.OnClickListener {

    Context context;
    EditText startDate;
    EditText destination;
    EditText description;
    EditText endDate;
    EditText name;
    EditText vehicle;

//    Button btnSave1;
//    Button btnClose;
//    List<Expense> expenseList;
//    List<Trip> tripList;
//    ListView lvTrip;
//    TripAdapter tripAdapter;


    boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);

        name = findViewById(R.id.tripname);
        destination = findViewById(R.id.inputDestination);
        startDate = findViewById(R.id.inputStartDate);
        vehicle = findViewById(R.id.inputVehicle);
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSave:
                Trip trip =new Trip();
                trip.setTripName(name.getText().toString());
                trip.setDestination(destination.getText().toString());
                trip.setStartDate(startDate.getText().toString());
                trip.setEndDate(endDate.getText().toString());
                trip.setVehicle(vehicle.getText().toString());
                trip.setDescription(description.getText().toString());
                if(name.getText().toString().equals("") && destination.getText().toString().equals("") && startDate.getText().toString().equals("")){
                    Toast.makeText(this,"Please input information ",Toast.LENGTH_SHORT).show();
                } else{
                    TripDAO dao = new TripDAO(this);
                    dao.insertTrip(trip);
                    Toast.makeText(this,"Save",Toast.LENGTH_SHORT).show();
                }
                break;
        }
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