package com.LMS.LeaveApplication.controllers;

import com.LMS.LeaveApplication.models.Leave;
import com.LMS.LeaveApplication.services.LeaveService;
import com.LMS.LeaveApplication.services.auth.MyCustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class EmployeeController {
    @Autowired
    private LeaveService leaveService;

    @PostMapping("/request")
    public ResponseEntity<Leave> createLeaveRequest(
            @RequestParam String reason,
            @RequestParam String startDate,
            @RequestParam String endDate
    ) {
        MyCustomUserDetails user = (MyCustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Check if the user is authenticated
        if (user == null) {
            return ResponseEntity.status(401).body(null);
        }
        System.out.println(user.getUserId());
        Leave leave = leaveService.addLeaveRequest(user.getUserId(),reason, startDate, endDate);
        return ResponseEntity.ok(leave);
    }

    @GetMapping("/myLeaveRequests")
    public ResponseEntity<?> getMyLeaveRequests() {
        MyCustomUserDetails user = (MyCustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Check if the user is authenticated
        if (user == null) {
            return ResponseEntity.status(401).body(null);
        }
        return ResponseEntity.ok(leaveService.getEmployeeLeaves(user.getUserId()));
    }
}
