package com.springboot.demoquanlysieuthi;

import com.springboot.demoquanlysieuthi.entity.Product;
import com.springboot.demoquanlysieuthi.entity.ProductType;
import com.springboot.demoquanlysieuthi.repository.OrderRepository;
import com.springboot.demoquanlysieuthi.repository.ProductRepository;
import com.springboot.demoquanlysieuthi.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProductTest {
    @Autowired
    private ProductService productService;
    @Test
    void sortingProductByPriceDesc(){
        List<Product> products = productService.getProductByPriceDesc();
        System.out.println("Danh sách sản phẩm theo giá giảm dần: " + "\n" + products);
    }
    @Test
    void sortingProductByPriceAsc(){
        List<Product> products = productService.getProductByPriceAsc();
        System.out.println("Danh sách sản phẩm theo giá tăng dần: " + "\n" + products);
    }
    @Test
    void sortingProductByStock(){
        List<Product> products = productService.getProductByStock();
        System.out.println("Danh sách sản phầm theo số lượng tồn tăng dần: " + "\n" + products);
    }
    @Test
    void getProductByName(){
        List<Product> products = productService.getProductByName("Miss Elfreda Kessler");
        System.out.println("Danh sách sản phẩm: " + products);
    }
    @Test
    void getProductByAmount(){
        List<Product> products = productService.getProductByAmount(20);
        System.out.println("Danh sách sản phẩm: " + products);
    }
}
