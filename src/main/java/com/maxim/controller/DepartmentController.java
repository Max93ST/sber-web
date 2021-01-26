package com.maxim.controller;

import com.maxim.dto.FilterDepDto;
import com.maxim.model.Department;
import com.maxim.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "/getAllDepartments", method = RequestMethod.POST)
    public Department[] getDepartments(@RequestBody(required = false) FilterDepDto filterDepDto) {
        return departmentService.getDepartments(filterDepDto);
    }

    @RequestMapping(value = "/addOrEditDepartment", method = RequestMethod.POST)
    public void addOrEditDepartment(@RequestBody Department department) {
        departmentService.addOrEditDepartment(department);
    }

    @RequestMapping(value = "/getEmpById", method = RequestMethod.GET)
    public Department getEmployees(Long id) {
        return departmentService.getById(id);
    }

    @RequestMapping(value = "/deleteEmpById", method = RequestMethod.POST)
    public void deleteEmployeeById(Long id) {
        departmentService.deleteEmployeeById(id);
    }
}