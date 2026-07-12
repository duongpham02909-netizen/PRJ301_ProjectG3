package model;

import java.sql.Timestamp;

public class StudentAttendanceView {
    private int studentId;
    private String studentName;
    private String studentCode;
    private String className;

    private int pickupStopId;
    private String pickupStopName;
    private Integer returnStopId;
    private String returnStopName;

    private Timestamp pickupTime;
    private Timestamp dropoffTime;
    private String attendanceStatus; // boarded, dropped_off, no_show, null
    private boolean absentToday;

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getStudentCode() { return studentCode; }
    public void setStudentCode(String studentCode) { this.studentCode = studentCode; }

    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }

    public int getPickupStopId() { return pickupStopId; }
    public void setPickupStopId(int pickupStopId) { this.pickupStopId = pickupStopId; }

    public String getPickupStopName() { return pickupStopName; }
    public void setPickupStopName(String pickupStopName) { this.pickupStopName = pickupStopName; }

    public Integer getReturnStopId() { return returnStopId; }
    public void setReturnStopId(Integer returnStopId) { this.returnStopId = returnStopId; }

    public String getReturnStopName() { return returnStopName; }
    public void setReturnStopName(String returnStopName) { this.returnStopName = returnStopName; }

    public Timestamp getPickupTime() { return pickupTime; }
    public void setPickupTime(Timestamp pickupTime) { this.pickupTime = pickupTime; }

    public Timestamp getDropoffTime() { return dropoffTime; }
    public void setDropoffTime(Timestamp dropoffTime) { this.dropoffTime = dropoffTime; }

    public String getAttendanceStatus() { return attendanceStatus; }
    public void setAttendanceStatus(String attendanceStatus) { this.attendanceStatus = attendanceStatus; }

    public boolean isAbsentToday() { return absentToday; }
    public void setAbsentToday(boolean absentToday) { this.absentToday = absentToday; }
}
