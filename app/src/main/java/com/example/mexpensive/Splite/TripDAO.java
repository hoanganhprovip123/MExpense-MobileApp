package com.example.mexpensive.Splite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.style.AlignmentSpan;

import androidx.annotation.NonNull;

import com.example.mexpensive.Splite.DatabaseHelper;
import com.example.mexpensive.Entities.Trip;

import java.util.ArrayList;
import java.util.List;

import kotlin.Suppress;

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
    @SuppressLint("Range")
    public List<Trip> get(String sql, String...selectArgs){
        List<Trip> trip = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectArgs);
        while (cursor.moveToNext()){
            Trip tr = new Trip();
            tr.setTripId(cursor.getInt(cursor.getColumnIndex("tripId")));
            tr.setTripName(cursor.getString(cursor.getColumnIndex("tripName")));
            tr.setDestination(cursor.getString(cursor.getColumnIndex("destination")));
            tr.setTripName(cursor.getString(cursor.getColumnIndex("startDate")));
            tr.setTripName(cursor.getString(cursor.getColumnIndex("endDate")));
            tr.setTripName(cursor.getString(cursor.getColumnIndex("vehicle")));
            tr.setTripName(cursor.getString(cursor.getColumnIndex("description")));
            trip.add(tr);
        }
        return trip;
    };

    public long update(Trip trip){
        ContentValues values = new ContentValues();
        values.put("tripName", trip.getTripName());
        values.put("destination", trip.getDestination());
        values.put("startDate", trip.getStartDate());
        values.put("endDate", trip.getEndDate());
        values.put("vehicle", trip.getVehicle());
        values.put("description", trip.getDescription());

        return db.update("Trip", values, "tripName = ? ", new String[]{trip.getTripName()});
    }

    public  List<Trip> getTrip(){
        String sql = "SELECT * FROM Trip";
        return get(sql);
    }
}
