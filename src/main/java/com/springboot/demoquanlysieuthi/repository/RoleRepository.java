package com.springboot.demoquanlysieuthi.repository;

import com.springboot.demoquanlysieuthi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
