package com.example.mexpensive;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mexpensive.Entities.Expense;

import java.util.List;

public class ExpenseAdapter extends BaseAdapter {
    private Context context;
    private List<Expense> list;

    public ExpenseAdapter(Context context, List<Expense> list){
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.expense_detail,null);

        }

        TextView tvName = view.findViewById(R.id.tvExpense);
        Expense expense = list.get(position);

        tvName.setText(expense.getName());

        return view;
    }
}
