package com.example.mexpensive;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mexpensive.Entities.Expense;
import com.example.mexpensive.Entities.Trip;
import com.example.mexpensive.Splite.ExpenseDbHelpler;
import com.example.mexpensive.Splite.TripDbHelpler;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class AddExpense extends AppCompatActivity implements View.OnClickListener {

    EditText eExpense,eAmount,eTime,eComment;
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
        TripFilter();
        eExpense =  findViewById(R.id.expense);
        eAmount = findViewById(R.id.amount);
        eTime = findViewById(R.id.time);
        eComment = findViewById(R.id.comment);
        findViewById(R.id.btnSave).setOnClickListener(this);
        findViewById(R.id.btnDel).setOnClickListener(this);

        lvExpense = findViewById(R.id.lvExpense1);
        lvExpense.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Expense exp = expenseList.get(position);
                eExpense.setText(exp.getName());
                eAmount.setText(exp.getAmount());
                eTime.setText(exp.getTime());
                eComment.setText(exp.getComment());
                isEdit = true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        ExpenseDbHelpler expenseDbHelpler = new ExpenseDbHelpler(this);
        switch (v.getId()) {
            case R.id.btnSave:
                try{
                    Expense exp = new Expense();
                    exp.setName(eExpense.getText().toString());
                    exp.setAmount(eAmount.getText().toString());
                    exp.setTime(eTime.getText().toString());
                    exp.setComment(eComment.getText().toString());

                    Trip trp = (Trip) spTrip.getSelectedItem();
                    exp.setTripId(trp.getTripId());
                    String msg ;
                    if(!isEdit) {
                        expenseDbHelpler.insert(exp);
                        msg = "Success";
                    }else{
                        expenseDbHelpler.update(exp);
                        msg = "Expense have been update";
                    }
                    Snackbar snackbar = Snackbar.make(v,msg,Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    eExpense.setText("");
                    eAmount .setText("");
                    eTime.setText("");
                    eComment.setText("");
                    isEdit =false;
                    ExpenseFilter();

                }catch (Exception ex){
                    ex.printStackTrace();
                    Toast.makeText(this,"Error" + ex.getMessage(),Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btnDel:
                if(isEdit && !eExpense.getText().toString().equals("")){
                    String name = eExpense.getText().toString();
                    expenseDbHelpler.delete(name);
                    ExpenseFilter();
                    Snackbar snackbar = Snackbar.make(v,"Delete Success",Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    eExpense.setText("");
                    eAmount .setText("");
                    eTime.setText("");
                    eComment.setText("");
                }
                break;
        }
    }

    private void TripFilter(){
        TripDbHelpler tripDbHelpler = new TripDbHelpler(this);
        tripList = tripDbHelpler.getTrip();
        TripAdapter tripAdapter = new TripAdapter(this,tripList);
        spTrip.setAdapter(tripAdapter);
        spTrip.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ExpenseFilter();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void ExpenseFilter(){
        ExpenseDbHelpler expenseDbHelpler = new ExpenseDbHelpler(this);
        try{
            Trip trip = (Trip) spTrip.getSelectedItem();
            expenseList = expenseDbHelpler.getExpenses(trip.getTripId());
            ExpenseAdapter expenseAd = new ExpenseAdapter(this,expenseList);
            lvExpense.setAdapter(expenseAd);
        }catch (Exception ex){
            ex.printStackTrace();
            Toast.makeText(this,"Error " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}