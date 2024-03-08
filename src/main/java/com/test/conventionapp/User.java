package com.test.conventionapp;

/**
 * The User class represents a user in the convention application.
 */
public class User {
    private String email;       // The email of the user
    private String password;    // The password of the user

    /**
     * Get the email of the user.
     *
     * @return The email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email of the user.
     *
     * @param email The email of the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password of the user.
     *
     * @param password The password of the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    // Getters and setters for other properties...
}
