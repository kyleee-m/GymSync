 package model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Membership {
    private int membershipId;
    private int memberId;
    private MembershipPlan plan;
    private String startDate;
    private String endDate;
    private Double actualFee;
    private Double feeOverride;
    private String customBenefits;
    private String status;

    public Membership() {
        this.status = "Active";
    }

    public Membership(int membershipId, int memberId, MembershipPlan plan, String startDate, String endDate,
                      Double feeOverride, String customBenefits, String status) {
        this.membershipId = membershipId;
        this.memberId = memberId;
        this.plan = plan;
        this.startDate = startDate;
        this.endDate = endDate;
        this.feeOverride = feeOverride;
        this.customBenefits = customBenefits;
        this.status = status != null ? status : "Active";
        this.actualFee = calculateFee();
    }

    public Double calculateFee() {
        if (feeOverride != null && feeOverride >= 0) {
            this.actualFee = feeOverride;
        } else if (plan != null && plan.calculateFee() != null) {
            this.actualFee = plan.calculateFee();
        } else {
            this.actualFee = 0.0;
        }
        return this.actualFee;
    }

    public boolean checkExpiration() {
        if (endDate == null || endDate.isBlank()) {
            return false;
        }
        try {
            LocalDate expiration = LocalDate.parse(endDate);
            boolean expired = LocalDate.now().isAfter(expiration);
            if (expired) {
                this.status = "Expired";
            }
            return expired;
        } catch (DateTimeParseException e) {
            return "Expired".equalsIgnoreCase(this.status);
        }
    }

    public void renewMembership() {
        int daysToAdd = (plan != null && plan.getDurationDays() > 0) ? plan.getDurationDays() : 30;
        LocalDate baseDate = LocalDate.now();
        if (endDate != null && !endDate.isBlank()) {
            try {
                LocalDate parsedEnd = LocalDate.parse(endDate);
                if (!parsedEnd.isBefore(LocalDate.now())) {
                    baseDate = parsedEnd;
                }
            } catch (DateTimeParseException ignored) {
            }
        }
        this.startDate = LocalDate.now().toString();
        this.endDate = baseDate.plusDays(daysToAdd).toString();
        this.status = "Active";
        calculateFee();
    }

    public String getMembershipStatus() {
        checkExpiration();
        return this.status;
    }

    public int getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public MembershipPlan getPlan() {
        return plan;
    }

    public void setPlan(MembershipPlan plan) {
        this.plan = plan;
        calculateFee();
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Double getActualFee() {
        return actualFee;
    }

    public void setActualFee(Double actualFee) {
        this.actualFee = actualFee;
    }

    public Double getFeeOverride() {
        return feeOverride;
    }

    public void setFeeOverride(Double feeOverride) {
        this.feeOverride = feeOverride;
        calculateFee();
    }

    public String getCustomBenefits() {
        return customBenefits;
    }

    public void setCustomBenefits(String customBenefits) {
        this.customBenefits = customBenefits;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}