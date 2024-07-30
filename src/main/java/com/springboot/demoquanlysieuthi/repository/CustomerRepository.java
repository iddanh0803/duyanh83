package com.springboot.demoquanlysieuthi.repository;

import com.springboot.demoquanlysieuthi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
