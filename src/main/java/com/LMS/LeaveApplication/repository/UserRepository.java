package com.LMS.LeaveApplication.repository;

import com.LMS.LeaveApplication.enums.Role;
import com.LMS.LeaveApplication.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    /** -all methods provided by CrudRepository- **/
    User findByEmail(String email);
    // check if a user with the given email is already there
    boolean existsByEmail(String email);
    // check if a user with the given phone number is already there
    boolean existsByPhoneNumber(long phoneNumber);
    // get all users with the given role
    List<User> getByRole(Role role);
    /**-----------------------------------------------*/


    //insert a new user
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO users (first_name, last_name, email, password, role,phone_number) VALUES (:first_name, :last_name, :email, :password, :role, :phone_number)", nativeQuery = true)
    int signUpUser(@Param("first_name") String first_name,
                   @Param("last_name") String last_name,
                   @Param("email") String email,
                   @Param("password") String password,
                   @Param("role") String role,
                   @Param("phone_number") long phone_number);

    //checks if the user exists by email and password
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.email = :email AND u.password = :password")
    boolean existsByEmailAndPassword(String email, String password);

    // Query to get password by email
    @Query(value = "SELECT password FROM users WHERE email = :email", nativeQuery = true)
    String getPasswordByEmail(@Param("email") String email);
}
// END OF USER REPOSITORY INTERFACE.
