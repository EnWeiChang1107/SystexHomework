package com.systex.homework.controller;

import com.systex.homework.entity.Role;
import com.systex.homework.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoleController {
    private RoleService roleService;
    @Autowired
    public RoleController(RoleService roleService){
        this.roleService=roleService;
    }

    @GetMapping("/getAllRoles")
    public List<Role> getAll(){
        return roleService.getAll();
    }
    @GetMapping("/getRole/{roleId}")
    public Role getRole(@PathVariable int roleId){
        Role role=roleService.findById(roleId);
        if(role==null){
            throw new RuntimeException("Role id not found - " + roleId);
        }
        return role;

    }
    @PostMapping("/addRole")
    public Role addRole(@RequestBody Role role){
        role.setId(0);
        Role role1 =roleService.save(role);
        return role1;
    }
    @PutMapping("/updateRole")
    public Role updateRole(@RequestBody Role role){
        Role role1=roleService.findById(role.getId());
        role.setCreatedAt(role1.getCreatedAt());
        Role dbRole=roleService.save(role);
        return dbRole;
    }
    @DeleteMapping("/deleteRole/{roleId}")
    public String deleteRole(@PathVariable int roleId){
        Role role =roleService.findById(roleId);
        if(role==null){
            throw new RuntimeException("Role id not found - " + roleId);
        }
        roleService.deleteById(roleId);
        return "Deleted Role id: "+roleId;
    }
}
