package com.springboot.demoquanlysieuthi.model.request;

import com.springboot.demoquanlysieuthi.entity.ProductType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Getter
public class ProductRequest {
    private String name;
    private String units;
    private Integer amount;
    private Double price;
    private Integer productTypeId;
}
