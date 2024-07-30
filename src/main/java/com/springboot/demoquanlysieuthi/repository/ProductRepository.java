package com.springboot.demoquanlysieuthi.repository;

import com.springboot.demoquanlysieuthi.entity.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAll(Sort sort);
    List<Product> findAllByOrderByPriceAsc();
    @Query("select p from Product p where p.name = ?1")
    List<Product> getProductsByName(String name);

    @Query(nativeQuery = true, value = "select * from Product where amount > :amount order by amount asc")
    List<Product> getProductsByAmountGreaterThan(Integer amount);
}
