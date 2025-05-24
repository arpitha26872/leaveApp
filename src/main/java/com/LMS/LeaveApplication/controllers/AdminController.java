package com.LMS.LeaveApplication.controllers;

import com.LMS.LeaveApplication.enums.LeaveStatus;
import com.LMS.LeaveApplication.enums.Role;
import com.LMS.LeaveApplication.models.Leave;
import com.LMS.LeaveApplication.models.User;
import com.LMS.LeaveApplication.services.LeaveService;
import com.LMS.LeaveApplication.services.auth.MyCustomUserDetails;
import com.LMS.LeaveApplication.services.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private LeaveService leaveService;

    @Autowired
    private UserService userService;

    @PutMapping("/approveLeave/{leaveId}")
    public ResponseEntity<Leave> approveLeaveRequest(@PathVariable int leaveId) {
        MyCustomUserDetails user = (MyCustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Check if the user is authenticated
        if (user == null) {
            return ResponseEntity.status(401).body(null);
        }
        return ResponseEntity.ok(leaveService.changeLeaveStatus(leaveId, user.getUserId(), LeaveStatus.APPROVED));
    }

    @PutMapping("/rejectLeave/{leaveId}")
    public ResponseEntity<Leave> rejectLeaveRequest(@PathVariable int leaveId) {
        MyCustomUserDetails user = (MyCustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Check if the user is authenticated
        if (user == null) {
            return ResponseEntity.status(401).body(null);
        }
        return ResponseEntity.ok(leaveService.changeLeaveStatus(leaveId, user.getUserId(), LeaveStatus.REJECTED));
    }

    @GetMapping("/pendingLeaves")
    public ResponseEntity<List<Leave>> getPendingLeaves() {return ResponseEntity.ok(leaveService.getLeaveByStatus(LeaveStatus.PENDING));}

    @GetMapping("/approvedLeaves")
    public ResponseEntity<List<Leave>> getApprovedLeaves() {return ResponseEntity.ok(leaveService.getApprovedLeaves());}

    @GetMapping("/rejectedLeaves")
    public ResponseEntity<List<Leave>> getRejectedLeaves() {return ResponseEntity.ok(leaveService.getRejectedLeaves());}

    @GetMapping("/employeesOnLeave")
    public ResponseEntity<List<Leave>> getEmployeesOnLeave() {return ResponseEntity.ok(leaveService.getEmployeeOnLeave());}

    @GetMapping("/employees")
    public ResponseEntity<List<User>> getEmployees() {return ResponseEntity.ok(userService.getAllUsers(Role.EMPLOYEE));}

    @GetMapping("/admins")
    public ResponseEntity<List<User>> getAdmins() {return ResponseEntity.ok(userService.getAllUsers(Role.ADMIN));}
}
