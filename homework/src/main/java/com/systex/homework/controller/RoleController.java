package com.systex.homework.controller;

import com.systex.homework.entity.Role;
import com.systex.homework.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<Role>> getAll(){
        List<Role> list =roleService.getAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping("/get/{roleId}")
    public ResponseEntity<Role> getRole(@PathVariable int roleId){
        Role role=roleService.findById(roleId);
        if(role==null){
            throw new RuntimeException("Role id not found - " + roleId);
        }
        return ResponseEntity.ok().body(role);

    }
    @PostMapping("/add")
    public ResponseEntity<Role> addRole(@RequestBody Role role){
        role.setId(0);
        Role role1 =roleService.save(role);
        return ResponseEntity.ok().body(role1);
    }
    @PutMapping("/update")
    public ResponseEntity<Role> updateRole(@RequestBody Role role){
        Role role1=roleService.findById(role.getId());
        role.setCreatedAt(role1.getCreatedAt());
        Role dbRole=roleService.save(role);
        return ResponseEntity.ok().body(dbRole);
    }
    @DeleteMapping("/delete/{roleId}")
    public ResponseEntity<String> deleteRole(@PathVariable int roleId){
        Role role =roleService.findById(roleId);
        roleService.deleteById(roleId);
        return ResponseEntity.ok().body("Deleted Role id: "+roleId) ;
    }
}
