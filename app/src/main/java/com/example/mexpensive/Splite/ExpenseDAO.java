package com.example.mexpensive.Splite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.example.mexpensive.Entities.Expense;

import java.util.ArrayList;
import java.util.List;

public class ExpenseDAO {
    private SQLiteDatabase db;

    public ExpenseDAO(@NonNull Context context){
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        this.db = dbHelper.getWritableDatabase();
    }

    public long insert(Expense expense){
        ContentValues values = new ContentValues();
        values.put("tripId", expense.getTripId());
        values.put("ExpenseName",expense.getName());
        values.put("Amount",expense.getAmount());
        values.put("Time",expense.getTime());
        values.put("Comment",expense.getComment());

        return db.insert("Expense",null,values);
    }

    @SuppressLint("Range")
    public List<Expense> get(String sql, String...selectArgs){
        List<Expense> listExp = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectArgs);

        while (cursor.moveToNext()){
            Expense exp = new Expense();
            exp.setId(cursor.getInt(cursor.getColumnIndex("ExpenseId")));
            exp.setName(cursor.getString(cursor.getColumnIndex("ExpenseName")));
            exp.setTime(cursor.getString(cursor.getColumnIndex("Time")));
            exp.setAmount(cursor.getString(cursor.getColumnIndex("Amount")));
            exp.setComment(cursor.getString(cursor.getColumnIndex("Comment")));
            listExp.add(exp);
        }
        return listExp;
    }

    public List<Expense> getExpenses(Integer tripId){
        String sql = "SELECT * FROM Expense WHERE tripID = ?";
        return get(sql,"" + tripId);
    }

    public int delete(String name){
        return db.delete("Expense", "ExpenseName" + "= ?",new String[]{name} );
    }

    public long update(Expense expense){
        ContentValues values = new ContentValues();

        values.put("tripId", expense.getTripId());
        values.put("ExpenseName", expense.getName());
        values.put("Amount", expense.getAmount());
        values.put("Time",expense.getTime());
        values.put("Comment", expense.getComment());

        return db.update("Expense", values, "ExpenseName" + "= ?", new String[]{expense.getName()});
    }
}
