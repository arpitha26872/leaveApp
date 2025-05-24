
package com.LMS.LeaveApplication.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("pages")
public class PageController {

    /** Renders templates/login.html */
    @GetMapping("login")
    public String loginView() {
        return "login";
    }

    /** Landing page after successful login (optional) */
    @GetMapping("dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("adminDashboard")
    public String adminDashboard() {
        return "adminDashboard";
    }

    @GetMapping("addEmployee")
    public String addEmployee() {
        return "addEmployee";
    }

    @GetMapping("pendingRequests")
    public String pendingRequests() {
        return "pendingRequests";
    }

    @GetMapping("approvedRequests")
    public String approvedRequests() {
        return "approvedRequests";
    }

    @GetMapping("rejectedRequests")
    public String rejectedRequests() {
        return "rejectedRequests";
    }

    @GetMapping("onLeaveEmployees")
    public String onLeaveEmployees() {
        return "onLeaveEmployees";
    }

}
