package com.LMS.LeaveApplication.dto.requests;

public class UserSignUpRequest {

    private String first_name;
    private String last_name;
    private String email;
    private String password;

    /**
     * --------------------------------
     *  GETTERS AND SETTERS
     * -------------------------------
     * */

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
}
// END OF USER SIGN UP REQUEST CLASS.