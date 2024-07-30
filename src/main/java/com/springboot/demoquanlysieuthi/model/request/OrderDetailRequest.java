package com.springboot.demoquanlysieuthi.model.request;

import com.springboot.demoquanlysieuthi.entity.Order;
import lombok.Getter;

@Getter
public class OrderDetailRequest {
    private Integer productId;
    private Integer quantity;
    private Integer orderId;
    private Double price;
}
