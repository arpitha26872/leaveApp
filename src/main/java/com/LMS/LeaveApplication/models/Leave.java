package com.LMS.LeaveApplication.models;

import com.LMS.LeaveApplication.enums.LeaveStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "leave_requests")
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int leave_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private User employee;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manager_id")
    private User manager;

    private String leaveReason;
    private LocalDate leaveStartDate;
    private LocalDate leaveEndDate;
    private LeaveStatus leaveStatus;

    public Leave() {
    }

    public Leave(int leave_id, User employee, User manager, String leave_reason, LocalDate leave_start_date, LocalDate leave_end_date, LeaveStatus leave_status) {
        this.leave_id = leave_id;
        this.employee = employee;
        this.manager = manager;
        this.leaveReason = leave_reason;
        this.leaveStartDate = leave_start_date;
        this.leaveEndDate = leave_end_date;
        this.leaveStatus = leave_status;
    }

    public int getLeave_id() {
        return leave_id;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User user) {
        this.employee = user;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    public LocalDate getLeaveStartDate() {
        return leaveStartDate;
    }

    public void setLeaveStartDate(String leaveStartDate) {
        this.leaveStartDate = LocalDate.parse(leaveStartDate);
    }

    public LocalDate getLeaveEndDate() {
        return leaveEndDate;
    }

    public void setLeaveEndDate(String leaveEndDate) {
        this.leaveEndDate = LocalDate.parse(leaveEndDate);
    }

    public LeaveStatus getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(LeaveStatus leaveStatus) {
        this.leaveStatus = leaveStatus;
    }
}
