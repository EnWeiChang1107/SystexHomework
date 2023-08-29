package com.systex.homework.service;

import com.systex.homework.dao.RoleRepository;
import com.systex.homework.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class RoleServiceImpl implements RoleService{
    private RoleRepository roleRepository;
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository=roleRepository;
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(int Id) {
        Optional<Role> result=roleRepository.findById(Id);
        Role role=null;
        if(result.isPresent()){
            role=result.get();
        }else{
            throw new NullPointerException();
        }
        return role;
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteById(int id) {
        roleRepository.deleteById(id);
    }
}
