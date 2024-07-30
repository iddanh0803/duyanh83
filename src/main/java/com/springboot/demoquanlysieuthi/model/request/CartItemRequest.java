package com.springboot.demoquanlysieuthi.model.request;

import lombok.Getter;

@Getter
public class CartItemRequest {
    private Integer productId;
    private Integer quantity;
}
