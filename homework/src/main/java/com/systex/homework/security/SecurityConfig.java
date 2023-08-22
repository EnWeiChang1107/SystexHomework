package com.systex.homework.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager=new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery("select  name, password ,true from employees where name = ?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT e.name, r.name FROM employees e " +
                "JOIN employee_roles er ON e.id = er.employee_id " +
                "JOIN roles r ON er.role_id = r.id " +
                "WHERE e.name = ?");
        return jdbcUserDetailsManager;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET,"/api/getAllEmployees").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/getEmployees/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/api/addEmployees").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/api/updateEmployees").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/deleteEmployees/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/getRole/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/getAllRoles").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/api/addRole").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/api/updateRole").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/deleteRole/**").hasRole("ADMIN")
        );


        http.httpBasic(Customizer.withDefaults());

        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}
