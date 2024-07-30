package com.springboot.demoquanlysieuthi;

import com.springboot.demoquanlysieuthi.entity.Order;
import com.springboot.demoquanlysieuthi.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class OrderTest {
    @Autowired
    private OrderService orderService;
    @Test
    void sumOrderByDate(){
        LocalDate date = LocalDate.of(2024,3,2);
        long sumOrder = orderService.countOrderByDate(date);
        System.out.println("Tổng số đơn hàng ngày " + date + " là: " + sumOrder);
    }
    @Test
    void sumOrderByDate2(){
        LocalDate date = LocalDate.of(2024,3,2);
        long sumOrder = orderService.countOrderByDate2(date);
        System.out.println("Tổng số đơn hàng ngày " + date + " là: " + sumOrder);
    }
    @Test
    void sumOrderByDate3(){
        LocalDate date = LocalDate.of(2024,3,2);
        long sumOrder = orderService.countOrderByDate3(date);
        System.out.println("Tổng số đơn hàng ngày " + date + " là: " + sumOrder);
    }
}
