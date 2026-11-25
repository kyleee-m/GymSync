 package model;

public class Admin {
    private int adminId;
    private String username;
    private String passwordHash;
    private String fullName;
    private String role;
    private boolean active;
    private boolean loggedIn;

    public Admin() {
        this.active = true;
        this.role = "Administrator";
    }

    public Admin(int adminId, String username, String passwordHash, String fullName, String role, boolean active) {
        this.adminId = adminId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
        this.role = role;
        this.active = active;
    }

    public boolean login(String inputUsername, String inputPasswordHash) {
        if (!active || inputUsername == null || inputPasswordHash == null) {
            this.loggedIn = false;
            return false;
        }
        this.loggedIn = inputUsername.equals(this.username) && inputPasswordHash.equals(this.passwordHash);
        return this.loggedIn;
    }

    public boolean login() {
        this.loggedIn = this.active && this.username != null && !this.username.isBlank();
        return this.loggedIn;
    }

    public void logout() {
        this.loggedIn = false;
    }

    public void changePassword(String currentPasswordHash, String newPasswordHash) {
        if (currentPasswordHash == null || !currentPasswordHash.equals(this.passwordHash)) {
            throw new IllegalArgumentException("Current password is incorrect.");
        }
        if (newPasswordHash == null || newPasswordHash.isBlank()) {
            throw new IllegalArgumentException("New password cannot be empty.");
        }
        this.passwordHash = newPasswordHash;
    }

    public void changePassword() {
        // Hook when passwordHash is updated directly via setter
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}