package com.systex.homework;

import com.systex.homework.service.EmployeeService;
import com.systex.homework.service.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HomeworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeworkApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(EmployeeService employeeService, RoleService roleService){
		return runner ->{
			System.out.println(employeeService.findById(1));
			System.out.println(roleService.findById(1));
		};
	}

}
