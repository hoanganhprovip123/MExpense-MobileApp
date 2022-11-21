package com.example.mexpensive;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mexpensive.Entities.Expense;
import com.example.mexpensive.Splite.ExpenseDbHelpler;

import java.util.List;

public class ShowExpense extends AppCompatActivity {
    private ListView lvExpense;
    private List<Expense> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_expense);
        lvExpense = findViewById(R.id.lvExpense);


        lvExpense.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ExpenseDbHelpler expenseDbHelpler = new ExpenseDbHelpler(ShowExpense.this);
                Expense exp = list.get(position);
                expenseDbHelpler.delete("" + exp.getId());

            }
        });
    }
}