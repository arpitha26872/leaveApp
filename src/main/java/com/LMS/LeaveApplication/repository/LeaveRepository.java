package com.LMS.LeaveApplication.repository;

import com.LMS.LeaveApplication.enums.LeaveStatus;
import com.LMS.LeaveApplication.models.Leave;
import com.LMS.LeaveApplication.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LeaveRepository extends CrudRepository<Leave, Integer> {
    /** -all methods provided by CrudRepository- **/
    //get all leave requests
    List<Leave> findAll();
    //get all leave requests of an employee
    List<Leave> findByEmployee(User employee);
    //get all leave request by status
    List<Leave> findByLeaveStatus(LeaveStatus leaveStatus);
    /**-------------------------------------------------*

    /**-------------------------------------------------*
    * this returns all employees who have approved leaves
    * and start date is more than current date
    * and end date is less than current date
     ** -----------------------------------------------*/
    @Query("""
           SELECT l
             FROM Leave l
            WHERE l.leaveStatus   = com.LMS.LeaveApplication.enums.LeaveStatus.APPROVED
              AND l.leaveStartDate <= CURRENT_DATE
              AND l.leaveEndDate   >= CURRENT_DATE
           """)
    List<Leave> findEmployeesOnLeave();
}
