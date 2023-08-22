package com.systex.homework.service;

import com.systex.homework.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(int theId);

    Employee save(Employee theEmployee);

    void delete(int Id);
}
