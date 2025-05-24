package com.LMS.LeaveApplication.dto.responses;


import com.LMS.LeaveApplication.services.auth.MyCustomUserDetails;
import org.springframework.security.core.GrantedAuthority;

public class AuthResponse {

    private MyCustomUserDetails myCustomUserDetails;
    private String token;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private String role;

    public AuthResponse(){

    }
    // END OF NO ARGS CONSTRUCTOR.

    public AuthResponse(String token, MyCustomUserDetails myCustomUserDetails){
        this.token = token;
        this.myCustomUserDetails = myCustomUserDetails;
        this.role = myCustomUserDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("UNKNOWN");
    }

    public int getUserId(){
        return this.myCustomUserDetails.getUserId();
    }

    public String getUsername(){
        // This Will Return Users Email:
        return this.myCustomUserDetails.getUsername();
    }

    public String getFirstName(){
        return this.myCustomUserDetails.getFirstName();
    }

    public String getLastName(){
        return this.myCustomUserDetails.getLastName();
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
// END OF AUTH RESPONSE CLASS.
