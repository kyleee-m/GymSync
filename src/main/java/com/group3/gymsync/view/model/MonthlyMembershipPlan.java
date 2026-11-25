 package com.group3.gymsync.view.model;

public class MonthlyMembershipPlan extends MembershipPlan {
    private int months;

    public MonthlyMembershipPlan() {
        super();
        this.months = 1;
        this.durationDays = 30;
    }

    public MonthlyMembershipPlan(int planId, String planName, Double baseFee, int months, String description, String benefit) {
        super(planId, planName, baseFee, Math.max(1, months) * 30, description, benefit);
        setMonths(months);
    }

    @Override
    public Double calculateFee() {
        double fee = baseFee != null ? baseFee : 0.0;
        return fee * Math.max(1, months);
    }

    @Override
    public String getBenefits() {
        String baseBenefit = (benefit != null && !benefit.isEmpty()) ? benefit : "Unlimited gym access & progress tracking";
        return "Monthly Plan Benefits (" + months + " mo): " + baseBenefit;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        if (months <= 0) {
            throw new IllegalArgumentException("Months must be greater than zero.");
        }
        this.months = months;
        this.durationDays = months * 30;
    }
}