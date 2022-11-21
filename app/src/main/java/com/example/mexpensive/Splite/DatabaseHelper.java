package com.example.mexpensive.Splite;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.MenuItem;


import androidx.annotation.Nullable;

import com.example.mexpensive.Entities.Expense;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private SQLiteDatabase database;
    public DatabaseHelper(@Nullable  Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        database = getWritableDatabase();
    }



    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "M-Expense";
    private static final String TABLE_EXPENSES= "Expense";

    private static final String EXPENSE_ID = "ExpenseId";
    private static final String EXPENSE_NAME = "ExpenseName";
    private static final String AMOUNT = "Amount";
    private static final String TIME = "Time";
    private static final String COMMENT = "Comment";

    private static final String TABLE_TRIPS= "Trip";

    private static final String TRIP_ID = "tripId";
    private static final String TRIP_NAME = "tripName";
    private static final String END_DATE = "endDate";
    private static final String START_DATE = "startDate";
    private static final String RISK_TRIP = "riskTrip";
    private static final String DESCRIPTION = "description";
    private static final String DESTINATION = "destination";


    private static final String EXPENSE_TABLE_CREATE =  "create table "
            + TABLE_EXPENSES + " ("
            + EXPENSE_ID  + " integer primary key autoincrement, "
            + TRIP_ID + " integer, "
            + EXPENSE_NAME + " text not null, "
            + AMOUNT + " text not null, "
            + TIME + " text not null, "
            + COMMENT + " text not null,"
            + " FOREIGN KEY ("+TRIP_ID+") REFERENCES "+TABLE_TRIPS+"("+TRIP_ID+"));";

    private static final String TRIP_TABLE_CREATE = String.format(
            "CREATE TABLE %s (" +
                    "   %s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "   %s TEXT, " +
                    "   %s TEXT, " +
                    "   %s TEXT, " +
                    "   %s TEXT, " +
                    "   %s TEXT, " +
                    "   %s TEXT)",
            TABLE_TRIPS, TRIP_ID, TRIP_NAME,DESTINATION, START_DATE, END_DATE, RISK_TRIP, DESCRIPTION);



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EXPENSE_TABLE_CREATE);
        db.execSQL(TRIP_TABLE_CREATE);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String ExpenseSql = "DROP TABLE IF EXISTS Expense";
        String TripSql = "DROP TABLE IF EXISTS Trip";
        db.execSQL(ExpenseSql);
        db.execSQL(TripSql);
        onCreate(db);

    }



}
