package com.LMS.LibraryManagementSystem.models;

import com.LMS.LibraryManagementSystem.enums.LeaveStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "leave_requests")
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int leave_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private User employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private User manager;

    private String leave_reason;
    private LocalDate leave_start_date;
    private LocalDate leave_end_date;
    private LeaveStatus leave_status;

    public Leave() {
    }

    public Leave(int leave_id, User employee, User manager, String leave_reason, LocalDate leave_start_date, LocalDate leave_end_date, LeaveStatus leave_status) {
        this.leave_id = leave_id;
        this.employee = employee;
        this.manager = manager;
        this.leave_reason = leave_reason;
        this.leave_start_date = leave_start_date;
        this.leave_end_date = leave_end_date;
        this.leave_status = leave_status;
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

    public String getLeave_reason() {
        return leave_reason;
    }

    public void setLeave_reason(String leave_reason) {
        this.leave_reason = leave_reason;
    }

    public LocalDate getLeave_start_date() {
        return leave_start_date;
    }

    public void setLeave_start_date(String leave_start_date) {
        this.leave_start_date = LocalDate.parse(leave_start_date);
    }

    public LocalDate getLeave_end_date() {
        return leave_end_date;
    }

    public void setLeave_end_date(String leave_end_date) {
        this.leave_end_date = LocalDate.parse(leave_end_date);
    }

    public LeaveStatus getLeave_status() {
        return leave_status;
    }

    public void setLeave_status(LeaveStatus leave_status) {
        this.leave_status = leave_status;
    }
}
