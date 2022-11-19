package com.example.mexpensive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.mexpensive.Entities.Expense;
import com.example.mexpensive.Entities.Trip;
import com.example.mexpensive.Splite.ExpenseDAO;
import com.example.mexpensive.Splite.TripDAO;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class AddExpense extends AppCompatActivity implements View.OnClickListener {
    Context context;
    EditText inputExpense,inputAmount,inputTime,inputComment;
    ListView lvExpense;
    Spinner spTrip;
    List<Expense> expenseList;
    List<Trip> tripList;
    boolean  isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        spTrip = findViewById(R.id.spTrip);
        fillTripToSp();
        inputExpense =  findViewById(R.id.inputExpense);
        inputAmount = findViewById(R.id.inputAmount);
        inputTime = findViewById(R.id.inputTime);
        inputComment = findViewById(R.id.inputComment);
        findViewById(R.id.btnSave).setOnClickListener(this);
        findViewById(R.id.btnDelete1).setOnClickListener(this);

        lvExpense = findViewById(R.id.lvExpense1);
        lvExpense.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Expense exp = expenseList.get(position);
                inputExpense.setText(exp.getName());
                inputAmount.setText(exp.getAmount());
                inputTime.setText(exp.getTime());
                inputComment.setText(exp.getComment());
                isEdit = true;
            }
        });

    }

    private void fillTripToSp() {
        TripDAO dao = new TripDAO(this);
        tripList = dao.getTrip();
        TripAdapter trAdapter = new TripAdapter(this,tripList);
        spTrip.setAdapter(trAdapter);
        spTrip.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fillExpensetolv();
            }
        });
    }

    @Override
    public void onClick(View v) {
        ExpenseDAO dao = new ExpenseDAO(this);

        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(View -> {
            Expense exp = new Expense();

            exp.setName(inputExpense.getText().toString());
            exp.setAmount(inputAmount.getText().toString());
            exp.setTime(inputTime.getText().toString());
            exp.setComment(inputComment.getText().toString());

            Trip trip = (Trip) spTrip.getSelectedItem();
            exp.setTripId(trip.getTripId());
            if(!isEdit){
                dao.insert(exp);
            } else {
                dao.update(exp);
            }
            fillExpensetolv();
        });
        
        Button btnDel = findViewById(R.id.btnDel);
        btnDel.setOnClickListener(View ->{
            if(isEdit && !inputExpense.getText().toString().equals("")){
                String name = inputExpense.getText().toString();
                dao.delete(name);
                fillExpensetolv();

                inputExpense.setText("");
                inputAmount .setText("");
                inputTime.setText("");
                inputComment.setText("");
            }
        });
    }

    private void fillExpensetolv() {
        ExpenseDAO dao = new ExpenseDAO(this);
        Trip trip = (Trip) spTrip.getSelectedItem();
        expenseList = dao.getExpenses(trip.getTripId());
        expenseAdapter expAdapter = new expenseAdapter(this,expenseList);
        lvExpense.setAdapter(expAdapter);
    }
}