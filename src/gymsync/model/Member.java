package model;

public class Member {
    private int memberId;
    private String firstName;
    private String lastName;
    private String contactNumber;
    private String email;
    private String address;
    private String dateRegistered;
    private String status;

    public Member() {
        this.status = "Active";
    }

    public Member(int memberId, String firstName, String lastName, String contactNumber,
                  String email, String address, String dateRegistered, String status) {
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
        this.email = email;
        this.address = address;
        this.dateRegistered = dateRegistered;
        this.status = status != null ? status : "Active";
    }

    public String getFullName() {
        String first = firstName != null ? firstName.trim() : "";
        String last = lastName != null ? lastName.trim() : "";
        return (first + " " + last).trim();
    }

    public void updateProfile(String firstName, String lastName, String contactNumber, String email, String address) {
        if (firstName == null || firstName.isBlank() || lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("First name and last name are required.");
        }
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
        this.contactNumber = contactNumber;
        this.email = email;
        this.address = address;
    }

    public void updateProfile() {
        // Hook for GUI/controller-driven profile updates after setters are invoked
    }

    public String getMemberDetails() {
        return "Member ID: " + memberId +
               " | Name: " + getFullName() +
               " | Contact: " + contactNumber +
               " | Email: " + email +
               " | Status: " + status;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(String dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}