package com.springboot.demoquanlysieuthi.repository;

import com.springboot.demoquanlysieuthi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    long countOrderByDate(LocalDate date);
    @Query("select  count(o.id) from Order o WHERE o.date = :date")
    long countOrderByDate2(LocalDate date);
    @Query(nativeQuery = true, value = "select count(*) from Orders o where o.date = :date")
    long countOrderByDate3(LocalDate date);
    
}
