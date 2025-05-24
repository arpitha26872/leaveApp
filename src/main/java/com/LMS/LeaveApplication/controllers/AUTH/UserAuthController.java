package com.LMS.LeaveApplication.controllers.AUTH;

import com.LMS.LeaveApplication.dto.requests.LoginRequest;
import com.LMS.LeaveApplication.dto.responses.AuthResponse;
import com.LMS.LeaveApplication.services.JwtTokenService;
import com.LMS.LeaveApplication.services.auth.MyCustomUserDetailService;
import com.LMS.LeaveApplication.services.auth.MyCustomUserDetails;
import com.LMS.LeaveApplication.services.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth") // Base path for all endpoints in this controller.
public class UserAuthController {

    @Autowired
    private PasswordEncoder passwordEncoder; // Used to hash and compare passwords securely.

    @Autowired
    private AuthenticationManager authenticationManager; // Manages user authentication.

    @Autowired
    private UserService userService; // Handles user-related operations such as registration.

    @Autowired
    private MyCustomUserDetailService myCustomUserDetailService; // Custom service to load user details.

    @Autowired
    private JwtTokenService jwtTokenService; // Service for generating and managing JWT tokens.

    @PostMapping("/login")
    public ResponseEntity signIn(@RequestBody LoginRequest loginRequest) {

        if(!checkEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword())) {
            return new ResponseEntity("Invalid email or password", HttpStatus.BAD_REQUEST);
        }
        // SET AUTHENTICATION:
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        // The above line authenticates the user by checking the email and password.
        // If successful, it returns an authenticated `Authentication` object; otherwise, it throws an exception.

        System.out.println("done");

        // SET USER OBJECT:
        MyCustomUserDetails userDetails =
                (MyCustomUserDetails) myCustomUserDetailService.loadUserByUsername(loginRequest.getEmail());
        // Fetches the custom user details, such as roles and permissions, using the provided email.

        // SET SECURITY CONTEXT:
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Stores the authenticated user's information in the SecurityContext, making it available throughout the application.

        // GENERATE TOKEN:
        String token = jwtTokenService.generateToken(userDetails);
        // Generates a JWT token for the authenticated user using their details.


        // SET RESPONSE:
        AuthResponse response = new AuthResponse(token, userDetails);
        // Constructs a response object containing the token and user details.

        // RETURN RESPONSE:
        return new ResponseEntity(response, HttpStatus.ACCEPTED);
        // Returns the response with HTTP status 202 (Accepted).
    }
    // END OF USER SIGN IN POST METHOD.

    @PostMapping("/is_token_expired")
    public ResponseEntity checkTokenExpiry(@RequestParam("token") String token) {
        Boolean response = jwtTokenService.isTokenExpired(token);
        //returns true if token is expired
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    // END OF TOKEN EXPIRY CHECK POST METHOD

    public boolean checkEmailAndPassword(String email, String password) {
        if (!userService.doesWithEmailExist(email)) {
            return false;
        }
        if (userService.doesWithEmailExist(email)) {
            String hasPassword = userService.getPasswordByEmail(email);
            return passwordEncoder.matches(password, hasPassword);
        }
        return true;
    }
}
// END OF AUTH REST CONTROLLER CLASS.
