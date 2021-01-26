package com.maxim.controller;

import com.maxim.dto.FilterEmpDto;
import com.maxim.model.Employee;
import com.maxim.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/addOrEditEmployee", method = RequestMethod.POST)
    public void addOrEditEmployee(@RequestBody Employee employee) {
        employeeService.addOrEditEmployee(employee);
    }

    @RequestMapping(value = "/deleteEmpById", method = RequestMethod.POST)
    public void deleteEmployeeById(Long id) {
        employeeService.deleteEmployeeById(id);
    }

    @RequestMapping(value = "/getEmployees", method = RequestMethod.POST)
    public Employee[] getEmployees(@RequestBody(required = false) FilterEmpDto filterEmpDto) {
        return employeeService.getEmployees(filterEmpDto);
    }

    @RequestMapping(value = "/getEmpById", method = RequestMethod.GET)
    public Employee getEmployees(Long id) {
        return employeeService.getById(id);
    }

}