package com.example.mexpensive.Entities;

public class Expense {
    public String name;
    public String amount;
    public String time;
    public String comment;
    public int id;
    public int tripId;

    @Override
    public String toString(){
        return name;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public Expense() {
    }

    public Expense(String name, String amount, String time, String comment, int id,int tripId) {
        this.name = name;
        this.amount = amount;
        this.time = time;
        this.comment = comment;
        this.id = id;
        this.tripId= tripId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
