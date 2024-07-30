package com.springboot.demoquanlysieuthi.service;

import com.springboot.demoquanlysieuthi.entity.Product;
import com.springboot.demoquanlysieuthi.entity.ProductType;
import com.springboot.demoquanlysieuthi.model.request.ProductTypeRequest;
import com.springboot.demoquanlysieuthi.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeService {
    @Autowired
    private ProductTypeRepository productTypeRepository;
    public List<ProductType> getProductTypeByProfit(){
        return productTypeRepository.getProductTypeByProfit();
    }
    public ProductType createProductType(ProductTypeRequest request){
         ProductType productType = ProductType.builder()
                .name(request.getName())
                .build();
         return productTypeRepository.save(productType);
    }
}
