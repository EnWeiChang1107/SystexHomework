package com.systex.homework.service;

import com.systex.homework.dao.EmployeeRepository;
import com.systex.homework.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
        employeeRepository = theEmployeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAllNotDeleted();
    }

    @Override
    public Employee findById(int theId) {
        Optional<Employee> result = employeeRepository.findById(theId);

        Employee theEmployee = null;

        if (result.isPresent()) {
            theEmployee = result.get();
        }
        else {
//            System.out.println("Did not find employee id - " + theId);
            throw new RuntimeException("Did not find employee id - " + theId);
        }

        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        return employeeRepository.save(theEmployee);
    }

    @Override
    public void delete(int Id) {
        Employee employee =employeeRepository.findById(Id).orElse(null);
        if(employee!=null){
            employee.setIs_delete((byte)1);
            employeeRepository.save(employee);
        }else{
            throw new RuntimeException("Employee id not found - " + Id);
        }
    }

    @Override
    public List<Employee> findByName(String name) {

        return employeeRepository.findByName(name);
    }


}
