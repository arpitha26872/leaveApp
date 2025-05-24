package com.LMS.LibraryManagementSystem.repository;

import com.LMS.LibraryManagementSystem.models.Leave;
import com.LMS.LibraryManagementSystem.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LeaveRepository extends CrudRepository<Leave, Integer> {
    List<Leave> findAll();
    List<Leave> findByEmployee(User employee);
}
