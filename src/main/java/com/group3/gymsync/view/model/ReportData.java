package com.group3.gymsync.view.model;

import java.math.BigDecimal;
import java.util.List;

public class ReportData {
    private int totalMembers;
    private int activeMemberships;
    private int expiredMemberships;
    private int dailyAttendance;
    private BigDecimal totalPayments;
    private BigDecimal outstandingBalance;

    public ReportData() {
        this.totalPayments = BigDecimal.ZERO;
        this.outstandingBalance = BigDecimal.ZERO;
    }

    public void calculateStatistics(List<Member> members, List<Membership> memberships,
                                    List<Attendance> attendances, List<Payment> payments) {
        this.totalMembers = members != null ? members.size() : 0;

        int activeCount = 0;
        int expiredCount = 0;
        double totalExpectedFees = 0.0;

        if (memberships != null) {
            for (Membership m : memberships) {
                if (m.checkExpiration() || "Expired".equalsIgnoreCase(m.getStatus())) {
                    expiredCount++;
                } else {
                    activeCount++;
                }
                totalExpectedFees += (m.getActualFee() != null ? m.getActualFee() : m.calculateFee());
            }
        }
        this.activeMemberships = activeCount;
        this.expiredMemberships = expiredCount;
        this.dailyAttendance = attendances != null ? attendances.size() : 0;

        double paidSum = 0.0;
        if (payments != null) {
            for (Payment p : payments) {
                if ("Paid".equalsIgnoreCase(p.getPaymentStatus()) || "Partial".equalsIgnoreCase(p.getPaymentStatus())) {
                    paidSum += (p.getAmount() != null ? p.getAmount() : 0.0);
                }
            }
        }

        this.totalPayments = BigDecimal.valueOf(paidSum);
        double balance = Math.max(0.0, totalExpectedFees - paidSum);
        this.outstandingBalance = BigDecimal.valueOf(balance);
    }

    public void calculateStatistics() {
        if (totalPayments == null) {
            totalPayments = BigDecimal.ZERO;
        }
        if (outstandingBalance == null) {
            outstandingBalance = BigDecimal.ZERO;
        }
    }

    public String generateSummary() {
        return "GymSync Summary -> Total Members: " + totalMembers +
               ", Active Memberships: " + activeMemberships +
               ", Expired Memberships: " + expiredMemberships +
               ", Daily Check-ins: " + dailyAttendance +
               ", Total Payments: " + totalPayments +
               ", Outstanding Balance: " + outstandingBalance;
    }

    public String getReportDetails() {
        return "=== GymSync Administrative Report ===\n" +
               "Total Members        : " + totalMembers + "\n" +
               "Active Memberships   : " + activeMemberships + "\n" +
               "Expired Memberships  : " + expiredMemberships + "\n" +
               "Daily Attendance     : " + dailyAttendance + "\n" +
               "Total Payments       : " + totalPayments + "\n" +
               "Outstanding Balance  : " + outstandingBalance;
    }

    public int getTotalMembers() {
        return totalMembers;
    }

    public void setTotalMembers(int totalMembers) {
        this.totalMembers = totalMembers;
    }

    public int getActiveMemberships() {
        return activeMemberships;
    }

    public void setActiveMemberships(int activeMemberships) {
        this.activeMemberships = activeMemberships;
    }

    public int getExpiredMemberships() {
        return expiredMemberships;
    }

    public void setExpiredMemberships(int expiredMemberships) {
        this.expiredMemberships = expiredMemberships;
    }

    public int getDailyAttendance() {
        return dailyAttendance;
    }

    public void setDailyAttendance(int dailyAttendance) {
        this.dailyAttendance = dailyAttendance;
    }

    public BigDecimal getTotalPayments() {
        return totalPayments;
    }

    public void setTotalPayments(BigDecimal totalPayments) {
        this.totalPayments = totalPayments;
    }

    public BigDecimal getOutstandingBalance() {
        return outstandingBalance;
    }

    public void setOutstandingBalance(BigDecimal outstandingBalance) {
        this.outstandingBalance = outstandingBalance;
    }
}
