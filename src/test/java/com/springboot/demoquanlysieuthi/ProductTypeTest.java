package com.springboot.demoquanlysieuthi;

import com.springboot.demoquanlysieuthi.service.ProductTypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductTypeTest {
    @Autowired
    private ProductTypeService productTypeService;
    @Test
    void getProductTypeByProfit(){

        System.out.println("Tổng hợp lợi nhuận theo nhóm sản phầm: " + productTypeService.getProductTypeByProfit());
    }
}
