package com.ted.employeemanager.repo;

import com.ted.employeemanager.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    @Query("" +
            "SELECT CASE WHEN COUNT(e) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Employee e " +
            "WHERE e.email = ?1"
    )

    Boolean selectExistsEmail(String email);
}
