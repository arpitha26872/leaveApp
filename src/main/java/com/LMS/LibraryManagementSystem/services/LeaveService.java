package com.LMS.LibraryManagementSystem.services;

import com.LMS.LibraryManagementSystem.enums.LeaveStatus;
import com.LMS.LibraryManagementSystem.models.Leave;
import com.LMS.LibraryManagementSystem.models.User;
import com.LMS.LibraryManagementSystem.repository.LeaveRepository;
import com.LMS.LibraryManagementSystem.repository.UserRepository;
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
        User employee = userRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Leave leave = new Leave();
        leave.setEmployee(employee);
        leave.setManager(null);
        leave.setLeaveReason(reason);
        leave.setLeaveStartDate(startDate);
        leave.setLeaveEndDate(endDate);
        leave.setLeaveStatus(LeaveStatus.PENDING);

        return leaveRepository.save(leave);  // ←🔥 This is where the leave is added (inserted)
    }

    public List<Leave>  getEmployeeLeaves(int employeeId) {
        User employee = userRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return leaveRepository.findByEmployee(employee);
    }

    public List<Leave> getLeaveByStatus(LeaveStatus leaveStatus) {
        return leaveRepository.findByLeaveStatus(leaveStatus);
    }

    public Leave changeLeaveStatus(int leaveId, int userId, LeaveStatus leaveStatus) {
        User manager = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Manager not found"));
        Leave leave = leaveRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));
        leave.setLeaveStatus(leaveStatus);
        leave.setManager(manager);
        return leaveRepository.save(leave);
    }

    public List<Leave> getApprovedLeaves() {
        return leaveRepository.findByLeaveStatus(LeaveStatus.APPROVED);
    }

    public List<Leave> getRejectedLeaves() {
        return leaveRepository.findByLeaveStatus(LeaveStatus.REJECTED);
    }

    public List<Leave> getEmployeeOnLeave() {
        return leaveRepository.findEmployeesOnLeave();
    }
}

