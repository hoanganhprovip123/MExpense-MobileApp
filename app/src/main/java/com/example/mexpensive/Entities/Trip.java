package com.example.mexpensive.Entities;

public class Trip {
    private int tripId;
    private String NameOfTrip;
    private String Destination;
    private String StartDate;
    private String EndDate;
    private String Risk;
    private String Description;

    public Trip() {
    }

    public Trip(int tripId, String tripName, String destination,
                String startDate, String endDate, String risk, String description) {

        this.tripId = tripId;
        this.NameOfTrip = tripName;
        this.Destination = destination;
        this.StartDate = startDate;
        this.EndDate = endDate;
        this.Risk = risk;
        this.Description = description;
    }
    public int getTripId() {
        return tripId;
    }

    public String getNameOfTrip() {
        return NameOfTrip;
    }

    public String getDestination() {
        return Destination;
    }

    public String getStartDate() {
        return StartDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public String getRisk() {
        return Risk;
    }

    public String getDescription() {
        return Description;
    }
    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public void setNameOfTrip(String nameOfTrip) {
        NameOfTrip = nameOfTrip;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public void setRisk(String risk) {
        Risk = risk;
    }

    public void setDescription(String description) {
        Description = description;
    }

}
