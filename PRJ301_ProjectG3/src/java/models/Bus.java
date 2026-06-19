package models;

public class Bus {
    private int busId;
    private int routeId;
    private String busCode;
    private int capacity;
    private String licensePlate;
    private int managerId;
    private int driverId;
    private String status;
    private String createdDate;

    public Bus() {
    }

    public Bus(int busId, int routeId, String busCode, int capacity, String licensePlate, int managerId, int driverId, String status) {
        this.busId = busId;
        this.routeId = routeId;
        this.busCode = busCode;
        this.capacity = capacity;
        this.licensePlate = licensePlate;
        this.managerId = managerId;
        this.driverId = driverId;
        this.status = status;
    }

    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getBusCode() {
        return busCode;
    }

    public void setBusCode(String busCode) {
        this.busCode = busCode;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
