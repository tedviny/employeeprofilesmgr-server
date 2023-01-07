package com.ted.employeemanager.employee;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ted.employeemanager.model.Employee;
import com.ted.employeemanager.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class EmployeeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    // Post Unit test, check if 201 Created is returned, when new user is added.
    @Test
    public void givenEmployeeObject_whenCreateEmployee_thenReturnSavedEmployee() throws Exception{
        //Given

        Employee employee = new Employee(
                "Doe",
                "johndoe@gmail.com",
                "Developer",
                "0033111221123",
                "www.image.com",
                "02"
        );

        //When

        ResultActions response = mockMvc.perform(post("/api/v1/employee/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)));

        //Then
        response.andExpect(status().isCreated());

    }

    // Get unit test, check if 200 OK is returned, when all employees are returned.
    @Test
    public void givenListOfEmployees_whenGetAllEmployees_thenReturnEmployeesList() throws Exception{
        // Given
        Employee employee1 = new Employee(
                "Alex",
                "alex@gmail.com",
                "Designer",
                "0033111221123",
                "www.image.com",
                "03"
        );
        Employee employee2 = new Employee(
                "Alice",
                "alice@gmail.com",
                "UX",
                "0033111221123",
                "www.image.com",
                "04"
        );
        List<Employee> listOfEmployees = new ArrayList<>();
        listOfEmployees.add(employee1);
        listOfEmployees.add(employee2);

        // When
        ResultActions response = mockMvc.perform(get("/api/v1/employee/"));

        // Then
        response.andExpect(status().isOk());

    }

    // Get unit test, check if 200 OK is returned, when employee given id is returned.
    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject() throws Exception{
        // Given
        long employeeId = 1L;
        Employee employee = new Employee(
                "Bob",
                "alex@gmail.com",
                "Tech Lead",
                "003311122113",
                "www.image.com",
                "05"
        );

        // When
        ResultActions response = mockMvc.perform(get("/api/v1/employee/{id}", employeeId));

        // Then
        response.andExpect(status().isOk());

    }

    
}
