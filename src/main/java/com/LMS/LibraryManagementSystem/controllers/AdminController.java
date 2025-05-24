package com.LMS.LibraryManagementSystem.controllers;

import com.LMS.LibraryManagementSystem.enums.LeaveStatus;
import com.LMS.LibraryManagementSystem.models.Leave;
import com.LMS.LibraryManagementSystem.services.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private LeaveService leaveService;

    @GetMapping("/pendingLeaves")
    public ResponseEntity<List<Leave>> getPendingLeaves() {return ResponseEntity.ok(leaveService.getLeaveByStatus(LeaveStatus.PENDING));}
}
