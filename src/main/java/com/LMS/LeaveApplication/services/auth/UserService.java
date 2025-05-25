package com.LMS.LeaveApplication.services.auth;

import com.LMS.LeaveApplication.enums.Role;
import com.LMS.LeaveApplication.models.User;
import com.LMS.LeaveApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Marks this class as a service, part of the Spring Service layer.
public class UserService {

    @Autowired
    private UserRepository userRepository;
    // Dependency injection for `UserRepository`, which handles database operations.

    /**
     * Method to load a user by their email.
     *
     * @param email The email of the user to load.
     * @return A `User` object with the details of the user corresponding to the email.
     */
    public User loadUserByEmail(String email) {
            System.out.println("Trying to load user by email: " + email);
            User user = userRepository.findByEmail(email);
            if (user == null) {
                System.out.println("No user found with email: " + email);
            } else {
                System.out.println("User found: " + user.getEmail());
            }
            return user;
        // Calls the repository method `getUserByEmail` to fetch the user details.
    }
    // END OF LOAD USER BY EMAIL.

    /**
     * Method to register a new user in the database.
     *
     * @param first_name The first name of the user.
     * @param last_name  The last name of the user.
     * @param email      The email of the user.
     * @param password   The hashed password of the user.
     * @return An integer indicating the success (1) or failure (0) of the operation.
     */
    public int signUpUser(String first_name, String last_name, String email, String password,String role,long phone_number) {
        return userRepository.signUpUser(first_name, last_name, email, password,role,phone_number);
        // Calls the repository method `signUpUser` to save the new user to the database.
    }// END OF SIGN UP USER SERVICE METHOD.

    public boolean doesWithEmailExist(String email) {
        return userRepository.existsByEmail(email);
    }// END OF CHECK IF EMAIL EXISTS SERVICE METHOD.

    public boolean doesUserWithEmailPasswordExist(String email, String password) {
        return userRepository.existsByEmailAndPassword(email, password);
    }// END OF CHECK IF USER WITH EMAIL AND PASSWORD EXISTS.

    public String getPasswordByEmail(String email) {
        String hashedPassword = userRepository.getPasswordByEmail(email);
                System.out.println("Stored hashed password: " + hashedPassword);
        return hashedPassword;
    }// END OF GET PASSWORD BY EMAIL

    public boolean doesWithPhoneNumberExist(long phoneNumber) {
        return userRepository.existsByPhoneNumber(phoneNumber);
    }

    public List<User> getAllUsers(Role role) {
        return userRepository.getByRole(role);
    }
}
// END OF USER SERVICE CLASS.
