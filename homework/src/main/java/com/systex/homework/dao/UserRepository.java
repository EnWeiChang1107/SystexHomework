package com.systex.homework.dao;

import com.systex.homework.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findUserByEmail(String email);
}
