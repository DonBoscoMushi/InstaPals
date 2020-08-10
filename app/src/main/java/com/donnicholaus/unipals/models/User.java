package com.donnicholaus.unipals.models;

public class User {

    private String user_id;
    private String username;
    private String fullname;
    private long phone_no;
    private String email;

    public User(String user_id, String username, String fullname, long phone_no, String email) {
        this.user_id = user_id;
        this.username = username;
        this.fullname = fullname;
        this.phone_no = phone_no;
        this.email = email;
    }

    public User() {
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public long getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(long phone_no) {
        this.phone_no = phone_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id='" + user_id + '\'' +
                ", username='" + username + '\'' +
                ", phone_no='" + phone_no + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
