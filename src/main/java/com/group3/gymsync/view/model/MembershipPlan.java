package com.group3.gymsync.view.model;

public abstract class MembershipPlan {
    protected int planId;
    protected String planName;
    protected Double baseFee;
    protected int durationDays;
    protected String description;
    protected String benefit;

    public MembershipPlan() {
    }

    public MembershipPlan(int planId, String planName, Double baseFee, int durationDays, String description, String benefit) {
        this.planId = planId;
        this.planName = planName;
        setBaseFee(baseFee);
        setDurationDays(durationDays);
        this.description = description;
        this.benefit = benefit;
    }

    public abstract Double calculateFee();

    public abstract String getBenefits();

    public String getPlanDetails() {
        return "Plan ID: " + planId +
               " | Name: " + planName +
               " | Duration: " + durationDays + " days" +
               " | Base Fee: " + baseFee +
               " | Description: " + description;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public Double getBaseFee() {
        return baseFee;
    }

    public void setBaseFee(Double baseFee) {
        if (baseFee == null || baseFee < 0) {
            throw new IllegalArgumentException("Base fee cannot be null or negative.");
        }
        this.baseFee = baseFee;
    }

    public int getDurationDays() {
        return durationDays;
    }

    public void setDurationDays(int durationDays) {
        if (durationDays <= 0) {
            throw new IllegalArgumentException("Duration days must be greater than zero.");
        }
        this.durationDays = durationDays;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBenefit() {
        return benefit;
    }

    public void setBenefit(String benefit) {
        this.benefit = benefit;
    }
}