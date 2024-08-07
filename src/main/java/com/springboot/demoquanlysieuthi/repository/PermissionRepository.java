package com.springboot.demoquanlysieuthi.repository;

import com.springboot.demoquanlysieuthi.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission,Integer> {
}
