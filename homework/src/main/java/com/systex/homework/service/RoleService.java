package com.systex.homework.service;

import com.systex.homework.dao.RoleRepository;
import com.systex.homework.entity.Role;

import java.util.List;

public interface RoleService  {
    List<Role> getAll();

    Role findById(int Id);

    Role save(Role role);

    void deleteById(int id);
}
