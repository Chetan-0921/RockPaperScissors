package com.rps.model;
public class User {
    private int id;
    private String username, password, email;
    public User() {}
    public User(int id, String username, String email) {
        this.id=id; this.username=username; this.email=email;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id=id; }
    public String getUsername() { return username; }
    public void setUsername(String u) { this.username=u; }
    public String getPassword() { return password; }
    public void setPassword(String p) { this.password=p; }
    public String getEmail() { return email; }
    public void setEmail(String e) { this.email=e; }
}