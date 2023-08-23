package com.systex.homework.controller;

import com.systex.homework.entity.Employee;
import com.systex.homework.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    private EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }


    @GetMapping("/getEmployeesByName")//localhost:8080/api/getEmployeesByName/search?name=John
    public List<Employee> findByNAme(@RequestParam(value = "name",required = false) String name){
        return employeeService.findByName(name);
    }
    @GetMapping("/getAllEmployees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }
    @GetMapping("/getEmployees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        Employee employee = employeeService.findById(employeeId);
        if(employee==null){
            throw new RuntimeException("Employee id not found - " + employeeId);
        }
        return employee;
    }
    @PostMapping("/addEmployees")
    public Employee addEmployee(@RequestBody Employee employee){
        employee.setId(0);
        Employee dbEmployee = employeeService.save(employee);
        return dbEmployee;
    }
    @PutMapping("/updateEmployees")
    public Employee updateEmployee(@RequestBody Employee employee){
        Employee employee1=employeeService.findById(employee.getId());
        employee.setCreatedAt(employee1.getCreatedAt());
        Employee dbemployee = employeeService.save(employee);
        return dbemployee;
    }
    @DeleteMapping("/deleteEmployees/{employeeId}")
    public String delEmployee(@PathVariable int employeeId){
        Employee dbEmployee=employeeService.findById(employeeId);
        if(dbEmployee==null){
            throw new RuntimeException("Employee id not found - " + employeeId);
        }
        employeeService.delete(employeeId);
        return "Deleted employee id: "+employeeId;
    }
}
