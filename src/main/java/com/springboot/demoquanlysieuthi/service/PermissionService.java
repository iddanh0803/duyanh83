package com.springboot.demoquanlysieuthi.service;

import com.springboot.demoquanlysieuthi.entity.Permission;
import com.springboot.demoquanlysieuthi.repository.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {
    private final PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public List<Permission> getPermission() {
        return permissionRepository.findAll();
    }
}
