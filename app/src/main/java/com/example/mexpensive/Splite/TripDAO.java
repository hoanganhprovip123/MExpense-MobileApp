package com.example.mexpensive.Splite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.example.mexpensive.Splite.DatabaseHelper;
import com.example.mexpensive.Entities.Trip;

public class TripDAO {

    private SQLiteDatabase db;
    public TripDAO(@NonNull Context context){
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        this.db = dbHelper.getWritableDatabase();
    }
    public long insertTrip(Trip trip) {
        ContentValues values = new ContentValues();
        values.put("tripName", trip.getTripName());
        values.put("destination", trip.getDestination());
        values.put("startDate", trip.getStartDate());
        values.put("endDate", trip.getEndDate());
        values.put("description", trip.getDescription());
        values.put("vehicle", trip.getVehicle());

        return db.insert("Trip", null, values);
    }
}
