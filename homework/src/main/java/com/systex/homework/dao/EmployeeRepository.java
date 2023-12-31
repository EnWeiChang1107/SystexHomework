package com.systex.homework.dao;

import com.systex.homework.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    @Query("SELECT e FROM Employee e WHERE e.is_delete <> 1")
    List<Employee> findAllNotDeleted();

    @Query(value = "select * from employees e where e.name like %?1% AND e.is_delete = 0",nativeQuery = true)
    List<Employee> findByName(String name);
}
