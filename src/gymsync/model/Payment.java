 package model;

import java.time.LocalDate;

public class Payment {
    private int paymentId;
    private int membershipId;
    private int adminId;
    private Double amount;
    private String paymentDate;
    private String paymentMethod;
    private String paymentStatus;

    public Payment() {
        this.paymentStatus = "Unpaid";
    }

    public Payment(int paymentId, int membershipId, int adminId, Double amount,
                   String paymentDate, String paymentMethod, String paymentStatus) {
        this.paymentId = paymentId;
        this.membershipId = membershipId;
        this.adminId = adminId;
        setAmount(amount);
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus != null ? paymentStatus : "Unpaid";
    }

    public void recordPayment() {
        if (this.amount == null || this.amount <= 0) {
            throw new IllegalArgumentException("Payment amount must be greater than zero.");
        }
        if (this.paymentDate == null || this.paymentDate.isBlank()) {
            this.paymentDate = LocalDate.now().toString();
        }
        this.paymentStatus = "Paid";
    }

    public Double calculateBalance(Double requiredFee) {
        double fee = requiredFee != null ? requiredFee : 0.0;
        double paid = this.amount != null ? this.amount : 0.0;
        return Math.max(0.0, fee - paid);
    }

    public Double calculateBalance() {
        return this.amount != null ? this.amount : 0.0;
    }

    public void updatePaymentStatus(Double requiredFee) {
        double balance = calculateBalance(requiredFee);
        if (balance <= 0.0 && (this.amount != null && this.amount > 0)) {
            this.paymentStatus = "Paid";
        } else if (this.amount != null && this.amount > 0) {
            this.paymentStatus = "Partial";
        } else {
            this.paymentStatus = "Unpaid";
        }
    }

    public void updatePaymentStatus() {
        if (this.amount != null && this.amount > 0 && "Unpaid".equalsIgnoreCase(this.paymentStatus)) {
            this.paymentStatus = "Paid";
        }
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        if (amount != null && amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }
        this.amount = amount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}