 package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Attendance {
    private int attendanceId;
    private int memberId;
    private int adminId;
    private String checkInTime;
    private String status;
    private String remarks;

    public Attendance() {
    }

    public Attendance(int attendanceId, int memberId, int adminId, String checkInTime, String status, String remarks) {
        this.attendanceId = attendanceId;
        this.memberId = memberId;
        this.adminId = adminId;
        this.checkInTime = checkInTime;
        this.status = status;
        this.remarks = remarks;
    }

    public void recordCheckIn() {
        if (this.checkInTime == null || this.checkInTime.isBlank()) {
            this.checkInTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
        this.status = "Checked In";
    }

    public boolean validateMembership(Membership membership) {
        if (membership == null) {
            this.status = "Invalid Membership";
            this.remarks = "No active membership found.";
            return false;
        }
        boolean isExpired = membership.checkExpiration();
        boolean isActiveStatus = "Active".equalsIgnoreCase(membership.getMembershipStatus());
        if (!isExpired && isActiveStatus) {
            this.status = "Valid";
            return true;
        }
        this.status = "Denied";
        this.remarks = "Membership is expired or inactive.";
        return false;
    }

    public boolean validateMembership() {
        return "Valid".equalsIgnoreCase(this.status) || "Checked In".equalsIgnoreCase(this.status);
    }

    public String getAttendanceDetails() {
        return "Attendance ID: " + attendanceId +
               " | Member ID: " + memberId +
               " | Admin ID: " + adminId +
               " | Time: " + checkInTime +
               " | Status: " + status +
               " | Remarks: " + (remarks != null ? remarks : "None");
    }

    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}