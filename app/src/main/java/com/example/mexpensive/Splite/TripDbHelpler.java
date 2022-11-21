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

public class TripDbHelpler {

    private SQLiteDatabase db;
    public TripDbHelpler(@NonNull Context context){
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        this.db = dbHelper.getWritableDatabase();
    }
    public long insertTrip(Trip trip) {
        ContentValues values = new ContentValues();
        values.put("tripName", trip.getNameOfTrip());
        values.put("destination", trip.getDestination());
        values.put("startDate", trip.getStartDate());
        values.put("endDate", trip.getEndDate());
        values.put("riskTrip", trip.getRisk());
        values.put("description", trip.getDescription());

        return db.insert("Trip", null, values);
    }
    @SuppressLint("Range")
    public List<Trip> get(String sql, String ...selectArgs){
        List<Trip> trip = new ArrayList<Trip>();
        Cursor cursor = db.rawQuery(sql, selectArgs);
        while (cursor.moveToNext()){
            Trip tr = new Trip();
            tr.setTripId(cursor.getInt(cursor.getColumnIndex("tripId")));
            tr.setNameOfTrip(cursor.getString(cursor.getColumnIndex("tripName")));
            tr.setDestination(cursor.getString(cursor.getColumnIndex("destination")));
            tr.setStartDate(cursor.getString(cursor.getColumnIndex("startDate")));
            tr.setEndDate(cursor.getString(cursor.getColumnIndex("endDate")));
            tr.setRisk(cursor.getString(cursor.getColumnIndex("riskTrip")));
            tr.setDescription(cursor.getString(cursor.getColumnIndex("description")));
            trip.add(tr);
        }
        return trip;
    };

    public long update(Trip trip){
        ContentValues values = new ContentValues();
        values.put("tripName", trip.getNameOfTrip());
        values.put("destination", trip.getDestination());
        values.put("startDate", trip.getStartDate());
        values.put("endDate", trip.getEndDate());
        values.put("riskTrip",trip.getRisk());
        values.put("description", trip.getDescription());

        return db.update("Trip", values, "tripName = ? ", new String[]{trip.getNameOfTrip()});
    }

    public  List<Trip> getTrip(){
        String sql = "SELECT * FROM Trip";
        return get(sql);
    }

    public int delete(String name) {
        return db.delete("Trip", "tripName =? ", new String[]{name});
    }
}
