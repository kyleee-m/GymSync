package com.group3.gymsync.view.model;

public class DailyMembershipPlan extends MembershipPlan {

    public DailyMembershipPlan() {
        super();
        this.durationDays = 1;
    }

    public DailyMembershipPlan(int planId, String planName, Double baseFee, String description, String benefit) {
        super(planId, planName, baseFee, 1, description, benefit);
    }

    @Override
    public Double calculateFee() {
        return baseFee != null ? baseFee : 0.0;
    }

    @Override
    public String getBenefits() {
        String baseBenefit = (benefit != null && !benefit.isEmpty()) ? benefit : "Single-day gym floor access";
        return "Daily Plan Benefits: " + baseBenefit + " (Valid for 1 day)";
    }
}