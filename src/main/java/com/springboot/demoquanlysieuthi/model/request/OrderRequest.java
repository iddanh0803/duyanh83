package com.springboot.demoquanlysieuthi.model.request;

import com.springboot.demoquanlysieuthi.entity.OrderDetail;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
public class OrderRequest {
    private Integer customerId;
    private List<CartItemRequest> cartItems;

}
