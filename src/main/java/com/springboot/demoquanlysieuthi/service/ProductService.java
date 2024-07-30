package com.springboot.demoquanlysieuthi.service;

import com.github.javafaker.Faker;
import com.springboot.demoquanlysieuthi.entity.Product;
import com.springboot.demoquanlysieuthi.model.request.ProductRequest;
import com.springboot.demoquanlysieuthi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProductByPriceDesc(){
        Sort sort = Sort.by("price").descending();
        return productRepository.findAll(sort);
    }
    public List<Product> getProductByPriceAsc(){
        return productRepository.findAllByOrderByPriceAsc();
    }
    public List<Product> getProductByStock(){
        Sort sort = Sort.by("amount").ascending();
        return productRepository.findAll(sort);
    }
    public List<Product> getProductByName(String name){
        return productRepository.getProductsByName(name);
    }
    public List<Product> getProductByAmount(Integer amount){
        return productRepository.getProductsByAmountGreaterThan(amount);
    }
//    public Product createProduct(ProductRequest request){
//        Faker faker = new Faker();
//        for (int i = 0; i < 5; i++) {
//            String productName = faker.commerce().productName();
//
//            Product product = Product.builder()
//                    .name(productName)
//                    .price((double) faker.number().numberBetween(10,90_000_000))
//                    .productType(request.getProductTypeId())
//                    .build();
//
//        }
//    }
}