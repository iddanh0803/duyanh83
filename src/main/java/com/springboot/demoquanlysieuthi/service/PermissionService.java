package com.springboot.demoquanlysieuthi.service;

import com.springboot.demoquanlysieuthi.entity.Employee;
import com.springboot.demoquanlysieuthi.entity.Permission;
import com.springboot.demoquanlysieuthi.repository.PermissionRepository;
import com.springboot.demoquanlysieuthi.security.CustomEmployeeDetail;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class PermissionService {
    private final PermissionRepository permissionRepository;


    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public Collection<GrantedAuthority> getPermission2(){
        CustomEmployeeDetail employeeDetail = (CustomEmployeeDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (Collection<GrantedAuthority>) employeeDetail.getAuthorities();
    }
}
