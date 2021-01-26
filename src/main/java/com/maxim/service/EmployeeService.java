package com.maxim.service;

import com.maxim.dto.FilterEmpDto;
import com.maxim.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class EmployeeService {
    private static final String getAllEmpUrl = "http://localhost:8080/sber-service-1.0-SNAPSHOT/api/employee/getAllEmployees";
    private static final String addOrEditEmpUrl = "http://localhost:8080/sber-service-1.0-SNAPSHOTapi/employee/addOrEditEmployee";
    private static final String deleteById = "http://localhost:8080/sber-service-1.0-SNAPSHOTapi/employee/{id}/deleteEmpById";
    private static final String getByIdEmpUrl = "http://localhost:8080/sber-service-1.0-SNAPSHOTapi/employee/{id}/getById";

    @Autowired
    RestTemplate restTemplate;

    public Employee[] getEmployees(FilterEmpDto filterEmpDto) {
        if (filterEmpDto == null) {
            filterEmpDto = new FilterEmpDto();
        }
        ResponseEntity<Employee[]> employees = request(filterEmpDto, getAllEmpUrl);
        return employees.getBody();
    }

    public void addOrEditEmployee(Employee employee) {
        request(employee, addOrEditEmpUrl);
    }

    public void deleteEmployeeById(Long id) {
        restTemplate.delete(deleteById, id);
    }

    public Employee getById(Long id) {
        return restTemplate.getForObject(getByIdEmpUrl, Employee.class, id);
    }

    private <T> ResponseEntity<Employee[]> request(T o, String url) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<T> entity = new HttpEntity<>(o, headers);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return restTemplate.exchange(url, HttpMethod.POST, entity, Employee[].class);
    }
}
