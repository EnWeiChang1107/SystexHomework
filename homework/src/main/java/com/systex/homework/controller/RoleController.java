package com.systex.homework.controller;

import com.systex.homework.entity.Role;
import com.systex.homework.response.ErrorRes;
import com.systex.homework.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Role")
public class RoleController {
    private RoleService roleService;
    @Autowired
    public RoleController(RoleService roleService){
        this.roleService=roleService;
    }

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        try {
            List<Role> list = roleService.getAll();
            return ResponseEntity.ok().body(list);
        }catch(Exception e){
            ErrorRes errorRes=new ErrorRes(HttpStatus.INTERNAL_SERVER_ERROR,"Oops... Something went wrong.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorRes);
        }
    }
    @GetMapping("/get/{roleId}")
    public ResponseEntity getRole(@PathVariable int roleId){
        try {

            Role role = roleService.findById(roleId);
            return ResponseEntity.ok().body(role);

        }catch(NullPointerException e){

            ErrorRes errorRes = new ErrorRes(HttpStatus.NOT_FOUND, "Role NotFound");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorRes);

        }catch(Exception e){

            ErrorRes errorRes=new ErrorRes(HttpStatus.INTERNAL_SERVER_ERROR,"Oops... Something went wrong.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorRes);

        }
    }
    @PostMapping("/add")
    public ResponseEntity addRole(@RequestBody Role role){
        try {
            role.setId(0);
            Role role1 = roleService.save(role);
            return new ResponseEntity<>(role1, HttpStatus.CREATED);
        }catch(Exception e){

            ErrorRes errorRes=new ErrorRes(HttpStatus.INTERNAL_SERVER_ERROR,"Oops... Something went wrong.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorRes);

        }
    }
    @PutMapping("/update")
    public ResponseEntity updateRole(@RequestBody Role role){
        try {

            Role role1 = roleService.findById(role.getId());
            role.setCreatedAt(role1.getCreatedAt());
            Role dbRole = roleService.save(role);
            return ResponseEntity.ok().body(dbRole);

        }catch(NullPointerException e){

            ErrorRes errorRes = new ErrorRes(HttpStatus.NOT_FOUND, "Role NotFound");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorRes);

        }catch(Exception e){

            ErrorRes errorRes=new ErrorRes(HttpStatus.INTERNAL_SERVER_ERROR,"Oops... Something went wrong.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorRes);

        }
    }
    @DeleteMapping("/delete/{roleId}")
    public ResponseEntity deleteRole(@PathVariable int roleId){
        try {

            Role role = roleService.findById(roleId);
            roleService.deleteById(roleId);
            return ResponseEntity.ok().body("Deleted Role id: " + roleId);

        }catch(NullPointerException e){

            ErrorRes errorRes = new ErrorRes(HttpStatus.NOT_FOUND, "Role NotFound");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorRes);

        }catch(Exception e){

            ErrorRes errorRes=new ErrorRes(HttpStatus.INTERNAL_SERVER_ERROR,"Oops... Something went wrong.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorRes);

        }
    }
}
