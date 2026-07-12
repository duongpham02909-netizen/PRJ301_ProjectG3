package model;

import java.sql.Date;
import java.sql.Time;

public class Trip {
    private int tripId;
    private int busId;
    private int routeId;
    private Date tripDate;
    private Time scheduledStartTime;
    private Time actualStartTime;
    private Time scheduledEndTime;
    private Time actualEndTime;
    private String status;          // scheduled, in_progress, completed, cancelled
    private String vehicleStatus;   // normal, issue, broken
    private String vehicleIssueNote;

    private String busCode;
    private String routeName;

    public int getTripId() { return tripId; }
    public void setTripId(int tripId) { this.tripId = tripId; }

    public int getBusId() { return busId; }
    public void setBusId(int busId) { this.busId = busId; }

    public int getRouteId() { return routeId; }
    public void setRouteId(int routeId) { this.routeId = routeId; }

    public Date getTripDate() { return tripDate; }
    public void setTripDate(Date tripDate) { this.tripDate = tripDate; }

    public Time getScheduledStartTime() { return scheduledStartTime; }
    public void setScheduledStartTime(Time t) { this.scheduledStartTime = t; }

    public Time getActualStartTime() { return actualStartTime; }
    public void setActualStartTime(Time t) { this.actualStartTime = t; }

    public Time getScheduledEndTime() { return scheduledEndTime; }
    public void setScheduledEndTime(Time t) { this.scheduledEndTime = t; }

    public Time getActualEndTime() { return actualEndTime; }
    public void setActualEndTime(Time t) { this.actualEndTime = t; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getVehicleStatus() { return vehicleStatus; }
    public void setVehicleStatus(String v) { this.vehicleStatus = v; }

    public String getVehicleIssueNote() { return vehicleIssueNote; }
    public void setVehicleIssueNote(String n) { this.vehicleIssueNote = n; }

    public String getBusCode() { return busCode; }
    public void setBusCode(String busCode) { this.busCode = busCode; }

    public String getRouteName() { return routeName; }
    public void setRouteName(String routeName) { this.routeName = routeName; }
}
