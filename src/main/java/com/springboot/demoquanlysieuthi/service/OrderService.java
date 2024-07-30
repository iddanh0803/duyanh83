package com.springboot.demoquanlysieuthi.service;

import com.springboot.demoquanlysieuthi.entity.*;
import com.springboot.demoquanlysieuthi.model.request.CartItemRequest;
import com.springboot.demoquanlysieuthi.model.request.OrderRequest;
import com.springboot.demoquanlysieuthi.repository.*;
import com.springboot.demoquanlysieuthi.security.CustomEmployeeDetail;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderDetailRepository orderDetailRepository;

    public OrderService(OrderRepository orderRepository, EmployeeRepository employeeRepository, CustomerRepository customerRepository, ProductRepository productRepository, OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.employeeRepository = employeeRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public long countOrderByDate(LocalDate date) {
        return orderRepository.countOrderByDate(date);
    }

    public long countOrderByDate2(LocalDate date) {
        return orderRepository.countOrderByDate2(date);
    }

    public long countOrderByDate3(LocalDate date) {
        return orderRepository.countOrderByDate3(date);
    }

    public Order getOrderById(Integer id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found order with ID: " + id));
    }

    public Order createOrder(OrderRequest request) {
        CustomEmployeeDetail customEmployeeDetail = (CustomEmployeeDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employee employee = customEmployeeDetail.getEmployee();
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Not found customer with id: " + request.getCustomerId()));
        Order order = new Order();
        order.setEmployee(employee);
        order.setCustomer(customer);
        order.setDate(LocalDate.now());
        Double totalMoney = 0.0;
        orderRepository.save(order);
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (CartItemRequest cartItemRequest : request.getCartItems()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);

            Integer productId = cartItemRequest.getProductId();
            Integer quantity = cartItemRequest.getQuantity();

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Not found product with id: " + productId));
            orderDetail.setProduct(product);
            orderDetail.setQuantity(quantity);
            orderDetail.setPrice(product.getPrice() * quantity);

            orderDetailList.add(orderDetail);
            product.setAmount(product.getAmount()-quantity);
            productRepository.save(product);
        }
        orderDetailRepository.saveAll(orderDetailList);
        order.setOrderDetailList(orderDetailList);
        for (OrderDetail orderDetail : orderDetailList) {
            totalMoney += orderDetail.getPrice();
            order.setTotalMoney(totalMoney);
        }
        orderRepository.save(order);

        return order;
    }
}
