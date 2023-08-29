package com.systex.homework.controller;

import com.systex.homework.entity.Employee;
import com.systex.homework.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Employee")
public class EmployeeController {
    private EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }


    @GetMapping("/getByName")//localhost:8080/api/getEmployeesByName/search?name=John
    public ResponseEntity<List<Employee>> findByNAme(@RequestParam(value = "name",required = false) String name){
        List<Employee> employees=employeeService.findByName(name);
        return ResponseEntity.ok().body(employees);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Employee>> findAll(){
        List<Employee> list=employeeService.findAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping("/get/{employeeId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable int employeeId){
        Employee employee = employeeService.findById(employeeId);
        if(employee==null){
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        return ResponseEntity.ok().body(employee);
    }
    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        employee.setId(0);
        Employee dbEmployee = employeeService.save(employee);
        return ResponseEntity.ok().body(dbEmployee);
    }
    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
        Employee employee1=employeeService.findById(employee.getId());
        employee.setCreatedAt(employee1.getCreatedAt());
        Employee dbemployee = employeeService.save(employee);
        return ResponseEntity.ok().body(dbemployee);
    }
    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity delEmployee(@PathVariable int employeeId){
        Employee dbEmployee=employeeService.findById(employeeId);
        if(dbEmployee.getIs_delete()==1){
            throw new RuntimeException("Employee id not found - " + employeeId);
        }
        employeeService.delete(employeeId);
        return ResponseEntity.ok().body("Deleted employee id: "+employeeId);
    }
}
