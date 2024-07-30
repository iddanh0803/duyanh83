package com.springboot.demoquanlysieuthi.model.request;

import lombok.Getter;

import java.util.List;

@Getter
public class ImportRequest {
    private List<CartItemRequest> cartItems;

}
