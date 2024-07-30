package com.springboot.demoquanlysieuthi.service;

import com.springboot.demoquanlysieuthi.entity.Order;
import com.springboot.demoquanlysieuthi.entity.OrderDetail;
import com.springboot.demoquanlysieuthi.entity.Product;
import com.springboot.demoquanlysieuthi.model.request.OrderDetailRequest;
import com.springboot.demoquanlysieuthi.repository.OrderDetailRepository;
import com.springboot.demoquanlysieuthi.repository.OrderRepository;
import com.springboot.demoquanlysieuthi.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailService(OrderRepository orderRepository, ProductRepository productRepository, OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public OrderDetail createOrderDetail(OrderDetailRequest request){
        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(()-> new RuntimeException("Not found order with id: " + request.getOrderId()));
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(()-> new RuntimeException("Not found product with id: " + request.getProductId()));
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(order);
        orderDetail.setProduct(product);
        orderDetail.setPrice(request.getPrice());
        orderDetail.setQuantity(request.getQuantity());
        return orderDetailRepository.save(orderDetail);
    }
}
