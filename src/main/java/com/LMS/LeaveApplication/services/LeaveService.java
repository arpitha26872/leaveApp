package com.LMS.LeaveApplication.services;

import com.LMS.LeaveApplication.enums.LeaveStatus;
import com.LMS.LeaveApplication.models.Leave;
import com.LMS.LeaveApplication.models.User;
import com.LMS.LeaveApplication.repository.LeaveRepository;
import com.LMS.LeaveApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private UserRepository userRepository;

    public Leave addLeaveRequest(int employeeId, String reason, String startDate, String endDate) {
        //get the employee detail , if not found then a runtime exception will be thrown
        User employee = userRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // create object of Leave class
        Leave leave = new Leave();
        leave.setEmployee(employee);
        leave.setManager(null);
        leave.setLeaveReason(reason);
        leave.setLeaveStartDate(startDate);
        leave.setLeaveEndDate(endDate);
        // default leave status as pending
        leave.setLeaveStatus(LeaveStatus.PENDING);

        return leaveRepository.save(leave);
    }

    public List<Leave>  getEmployeeLeaves(int employeeId) {
        //get the employee detail , if not found then a runtime exception will be thrown
        User employee = userRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return leaveRepository.findByEmployee(employee);
    }

    public List<Leave> getLeaveByStatus(LeaveStatus leaveStatus) {
        return leaveRepository.findByLeaveStatus(leaveStatus);
    }

    public Leave changeLeaveStatus(int leaveId, int userId, LeaveStatus leaveStatus) {
        //get the employee detail , if not found then a runtime exception will be thrown
        User manager = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Manager not found"));
        //get the employee detail , if not found then a runtime exception will be thrown
        Leave leave = leaveRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));
        leave.setLeaveStatus(leaveStatus);
        leave.setManager(manager);
        return leaveRepository.save(leave);
    }

    public List<Leave> getApprovedLeaves() {
        //changing the leave status to approve
        return leaveRepository.findByLeaveStatus(LeaveStatus.APPROVED);
    }

    public List<Leave> getRejectedLeaves() {
        //changing the leave status to rejected
        return leaveRepository.findByLeaveStatus(LeaveStatus.REJECTED);
    }

    public List<Leave> getEmployeeOnLeave() {
        //getting the employees currently on leave
        return leaveRepository.findEmployeesOnLeave();
    }
}

