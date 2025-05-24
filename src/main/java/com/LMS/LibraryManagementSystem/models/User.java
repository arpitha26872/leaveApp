package com.LMS.LibraryManagementSystem.models;

import com.LMS.LibraryManagementSystem.enums.Role;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    private String first_name;
    private String last_name;
    private String email;
    private long phoneNumber;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role = Role.EMPLOYEE; // Default role set to USER

    public User() {}

    public User(String first_name, String last_name, String email, long phone_number, String password, Role role) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phoneNumber = phone_number;
        this.password = password;
        this.role = role;
    }

    public User(int user_id, String first_name, String last_name, String email, long phone_number, String password, Role role) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phoneNumber = phone_number;
        this.password = password;
        this.role = role;
    }

    /**
     * ---------------------------------------
     *  GETTERS AND SETTERS:
     * -------------------------------------
     * */



    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

