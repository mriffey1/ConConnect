package com.test.conconnect.model.user;


public class User {
    private String username;
    private String passwordHash; // Store the hashed password instead of plain text
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getPassword() {
        return getPasswordHash();
    }

    public void setPassword(String passwordHash){
        setPasswordHash(passwordHash);
    }
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

}
