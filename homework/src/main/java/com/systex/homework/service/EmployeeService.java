package com.systex.homework.service;

import com.systex.homework.entity.Employee;

public interface EmployeeService {
    Employee findById(int theId);

    void save(Employee theEmployee);

    void deleteById(int theId);
}
