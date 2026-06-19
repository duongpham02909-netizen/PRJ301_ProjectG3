package models;

public class Route {
    private int routeId;
    private String routeName;
    private String routeCode;
    private String description;
    private String status;
    private String createdDate;

    public Route() {
    }

    public Route(int routeId, String routeName, String routeCode, String status) {
        this.routeId = routeId;
        this.routeName = routeName;
        this.routeCode = routeCode;
        this.status = status;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getRouteCode() {
        return routeCode;
    }

    public void setRouteCode(String routeCode) {
        this.routeCode = routeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
