package com.maxim.service;

import com.maxim.dto.FilterDepDto;
import com.maxim.model.Department;
import com.maxim.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;


@Service
public class DepartmentService {

    private static final String getAllDepUrl = "http://localhost:8080/sber-service-1.0-SNAPSHOT/api/department/getAllDepartment";
    private static final String addOrEditDepUrl = "http://localhost:8080/sber-service-1.0-SNAPSHOT/api/department/addOrEditDepartment";
    private static final String deleteById = "http://localhost:8080/sber-service-1.0-SNAPSHOT/api/department/{id}/deleteDepById";
    private static final String getByIdEmpUrl = "http://localhost:8080/sber-service-1.0-SNAPSHOT/api/department/{id}/getById";

    @Autowired
    RestTemplate restTemplate;

    public Department[] getDepartments(FilterDepDto filterDepDto) {
        if (filterDepDto == null) {
            filterDepDto = new FilterDepDto();
        }
        ResponseEntity<Department[]> department = request(filterDepDto, getAllDepUrl);

        if (department.getBody() != null) {
            Arrays.asList(department.getBody()).forEach(d -> d.setAvgSalary(d.getEmployees().stream()
                    .filter(Objects::nonNull)
                    .mapToLong(Employee::getSalary).average().orElse(0d)));
        }

        return department.getBody();
    }

    public void addOrEditDepartment(Department department) {
        request(department, addOrEditDepUrl);
    }

    public Department getById(Long id) {
        return restTemplate.getForObject(getByIdEmpUrl, Department.class, id);
    }

    public void deleteEmployeeById(Long id) {
        restTemplate.delete(deleteById, id);
    }

    private <T> ResponseEntity<Department[]> request(T o, String url) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<T> entity = new HttpEntity<>(o, headers);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return restTemplate.exchange(url, HttpMethod.POST, entity, Department[].class);
    }
}
