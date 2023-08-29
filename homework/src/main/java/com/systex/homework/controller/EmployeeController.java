package com.systex.homework.controller;

import com.systex.homework.entity.Employee;
import com.systex.homework.response.ErrorRes;
import com.systex.homework.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity findByNAme(@RequestParam(value = "name",required = false) String name){
        try {
            List<Employee> employees = employeeService.findByName(name);
            return ResponseEntity.ok().body(employees);
        }catch(NullPointerException e){
            ErrorRes errorRes = new ErrorRes(HttpStatus.NOT_FOUND, "Employee NotFound");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorRes);
        }catch (Exception e){
            ErrorRes errorRes=new ErrorRes(HttpStatus.INTERNAL_SERVER_ERROR,"Oops... Something went wrong.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorRes);
        }
    }
    @GetMapping("/getAll")
    public ResponseEntity findAll(){
        try {
            List<Employee> list = employeeService.findAll();
            return ResponseEntity.ok().body(list);
        }catch (Exception e){
            ErrorRes errorRes=new ErrorRes(HttpStatus.INTERNAL_SERVER_ERROR,"Oops... Something went wrong.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorRes);
        }
    }
    @GetMapping("/get/{employeeId}")
    public ResponseEntity getEmployee(@PathVariable int employeeId){
        try {
            Employee employee = employeeService.findById(employeeId);
            if (employee == null||employee.getIs_delete()==1) {
                ErrorRes errorRes = new ErrorRes(HttpStatus.NOT_FOUND, "Employee NotFound");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorRes);
            }
            return ResponseEntity.ok().body(employee);
        }catch(Exception e){
            ErrorRes errorRes=new ErrorRes(HttpStatus.INTERNAL_SERVER_ERROR,"Oops... Something went wrong.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorRes);
        }
    }
    @PostMapping("/add")
    public ResponseEntity addEmployee(@RequestBody Employee employee){
        try{
            employee.setId(0);
            Employee dbEmployee = employeeService.save(employee);
            return  new ResponseEntity<>(dbEmployee, HttpStatus.CREATED);
        }catch(Exception e){
            ErrorRes errorRes=new ErrorRes(HttpStatus.INTERNAL_SERVER_ERROR,"Oops... Something went wrong.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorRes);
        }
    }
    @PutMapping("/update")
    public ResponseEntity updateEmployee(@RequestBody Employee employee){
        try{
            Employee employee1=employeeService.findById(employee.getId());
            employee.setCreatedAt(employee1.getCreatedAt());
            Employee dbemployee = employeeService.save(employee);
            return ResponseEntity.ok().body(dbemployee);
        }catch(NullPointerException e){

            ErrorRes errorRes=new ErrorRes(HttpStatus.NOT_FOUND,"Employee NotFound");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorRes);

        }catch(Exception e){
            ErrorRes errorRes=new ErrorRes(HttpStatus.INTERNAL_SERVER_ERROR,"Oops... Something went wrong.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorRes);
        }
    }
    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity delEmployee(@PathVariable int employeeId){
        try {
            Employee dbEmployee = employeeService.findById(employeeId);
            if (dbEmployee.getIs_delete() == 1) {
                ErrorRes errorRes = new ErrorRes(HttpStatus.NOT_FOUND, "Id " + employeeId + " has already been deleted");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorRes);
            }
            employeeService.delete(employeeId);
            return ResponseEntity.ok().body("Deleted employee id: " + employeeId);
        }catch(NullPointerException e){
            ErrorRes errorRes = new ErrorRes(HttpStatus.NOT_FOUND, "Id " + employeeId + " has already been deleted");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorRes);
        }catch(Exception e){
            ErrorRes errorRes=new ErrorRes(HttpStatus.INTERNAL_SERVER_ERROR,"Oops... Something went wrong.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorRes);
        }
    }
}
