package com.ted.employeemanager.employee;

import com.ted.employeemanager.model.Employee;
import com.ted.employeemanager.repo.EmployeeRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepo underTest;


    @Test
    void itShouldCheckIfEmployeeEmailExists(){
        // Given
        String email = "loungouviny@gmail.com";
        Employee employee = new Employee(
                "Loungou",
                email,
                "DevOps",
                "0033652907497",
                "www.image.com",
                "01"
        );
        underTest.save(employee);
        // When
        boolean expected = underTest.selectExistsEmail(email);

        // Then
        assertThat(expected).isTrue();


    }
}
