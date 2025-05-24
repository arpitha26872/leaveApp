package com.LMS.LeaveApplication.services.auth;

import com.LMS.LeaveApplication.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // Marks this class as a service in Spring, specifically for handling user authentication.
public class MyCustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;
    // Dependency injection for `UserService`, which handles the retrieval of user data.

    /**
     * Loads a user by their username (email in this case) for authentication.
     * @param username The username (email) of the user to load.
     * @return A `UserDetails` object containing user-specific details.
     * @throws UsernameNotFoundException if the user cannot be found in the system.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            System.out.println("Trying to load user by email: " + username);

            // Fetch the user from the database using the email as the identifier
            User user = userService.loadUserByEmail(username);

            if (user == null) {
                System.err.println("User not found with email: " + username);
                throw new UsernameNotFoundException("User not found with email: " + username);
            }

            return new MyCustomUserDetails(user);
        } catch (UsernameNotFoundException e) {
            throw e; // rethrow so Spring Security can handle it properly
        } catch (Exception e) {
            // Log unexpected errors and wrap them in InternalAuthenticationServiceException
            System.err.println("Unexpected error in loadUserByUsername: " + e.getMessage());
            e.printStackTrace();
            throw new InternalAuthenticationServiceException("Unexpected error occurred while loading user", e);
        }
    }

}
// END OF MY CUSTOM USER DETAILS SERVICE CLASS.
