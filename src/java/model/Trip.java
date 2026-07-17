package model;

public class Trip {

    private int tripId;
    private String busCode;
    private String licensePlate;
    private String vehicleStatus;
    private String vehicleIssueNote;
    private String tripDate;
    private String scheduledStartTime;

    public Trip() {
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public String getBusCode() {
        return busCode;
    }

    public void setBusCode(String busCode) {
        this.busCode = busCode;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(String vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public String getVehicleIssueNote() {
        return vehicleIssueNote;
    }

    public void setVehicleIssueNote(String vehicleIssueNote) {
        this.vehicleIssueNote = vehicleIssueNote;
    }

    public String getTripDate() {
        return tripDate;
    }

    public void setTripDate(String tripDate) {
        this.tripDate = tripDate;
    }

    public String getScheduledStartTime() {
        return scheduledStartTime;
    }

    public void setScheduledStartTime(String scheduledStartTime) {
        this.scheduledStartTime = scheduledStartTime;
    }
}
