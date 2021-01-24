package com.restaurant.chantee.chantee_restaurant.entity;


import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String username;
    private String email;
    private String password;
    private String role;

    public User() {// default constructor
    }

    @Override
    public int hashCode() {
        return 31 * (getId() + getUsername().hashCode() + getEmail().hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass().equals(User.class)) {
            User that = ((User) obj);
            return (this.getId() == that.getId());
        }
        return false;
    }

    @Override
    public String toString() {
        return getUsername() + " id: " + getId() + " email: " + getEmail();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
