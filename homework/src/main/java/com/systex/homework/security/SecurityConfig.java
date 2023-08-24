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
                        .requestMatchers(HttpMethod.GET,"/Employee/getAll").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/Employee/getByName").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/Employee/get/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/Employee/add").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/Employee/update").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/Employee/delete/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET,"/Role/get/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/Role/getAll").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/Role/add").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/Role/update").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/Role/delete/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET,"/Category/getAll").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/Category/add").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/Category/update").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/Category/delete/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET,"/Product/getAll").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/Product/add").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/Product/update").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/Product/delete/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET,"/AvailableProduct/getAll").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/AvailableProduct/add/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/AvailableProduct/update").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/AvailableProduct/delete/**").hasRole("ADMIN")
        );


        http.httpBasic(Customizer.withDefaults());

        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}
