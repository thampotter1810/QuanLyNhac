package com.example.thampotter.quanlynhac.Model;

public class User {

    public String username;
    public String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String username, String password) {

        this.username = username;
        this.password = password;
    }
}
