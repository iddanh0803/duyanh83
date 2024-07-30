package com.springboot.demoquanlysieuthi.security;

import com.springboot.demoquanlysieuthi.entity.Employee;
import com.springboot.demoquanlysieuthi.repository.EmployeeRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomEmployeeDetailService implements UserDetailsService {
    private final EmployeeRepository employeeRepository;

    public CustomEmployeeDetailService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("Employee not found with email: " + email));
        return new CustomEmployeeDetail(employee);
    }
}
