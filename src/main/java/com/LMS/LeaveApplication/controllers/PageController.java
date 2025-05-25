
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

    /** Landing page after successful login of employee*/
    @GetMapping("dashboard")
    public String dashboard() {
        return "dashboard";
    }

    /** Landing page after successful login of admin*/
    @GetMapping("adminDashboard")
    public String adminDashboard() {
        return "adminDashboard";
    }

    /** Renders templates/addEmployee.html */
    @GetMapping("addEmployee")
    public String addEmployee() {
        return "addEmployee";
    }
    /** Renders templates/addEmployee.html */
    @GetMapping("pendingRequests")
    public String pendingRequests() {
        return "pendingRequests";
    }
    /** Renders templates/addEmployee.html */
    @GetMapping("approvedRequests")
    public String approvedRequests() {
        return "approvedRequests";
    }
    /** Renders templates/addEmployee.html */
    @GetMapping("rejectedRequests")
    public String rejectedRequests() {
        return "rejectedRequests";
    }
    /** Renders templates/addEmployee.html */
    @GetMapping("onLeaveEmployees")
    public String onLeaveEmployees() {
        return "onLeaveEmployees";
    }
}
