package com.LMS.LibraryManagementSystem.repository;

import com.LMS.LibraryManagementSystem.enums.LeaveStatus;
import com.LMS.LibraryManagementSystem.models.Leave;
import com.LMS.LibraryManagementSystem.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LeaveRepository extends CrudRepository<Leave, Integer> {
    List<Leave> findAll();
    List<Leave> findByEmployee(User employee);
    List<Leave> findByLeaveStatus(LeaveStatus leaveStatus);

    /* this returns all employees who have approved leaves
    * and start date is more than current date
    * and end date is less than current date
    */
    @Query("""
           SELECT l
             FROM Leave l
            WHERE l.leaveStatus   = com.LMS.LibraryManagementSystem.enums.LeaveStatus.APPROVED
              AND l.leaveStartDate <= CURRENT_DATE
              AND l.leaveEndDate   >= CURRENT_DATE
           """)
    List<Leave> findEmployeesOnLeave();
}
