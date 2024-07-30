package com.springboot.demoquanlysieuthi.repository;

import com.springboot.demoquanlysieuthi.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType,Integer> {
    @Query(value = "select \n" + "\tpt.id as id," +
            "\tpt.name as nhom_san_pham,\n" +
            "\tsum(od.price - itd.price) as loi_nhuan\n" +
            "from order_detail od\n" +
            "join order_detail_products odp on od.id = odp.order_detail_id\n" +
            "join product p on odp.products_id = p.id\n" +
            "join product_type pt on p.product_type_id = pt.id\n" +
            "join import_ticket_detail_products itdp on p.id = itdp.products_id\n" +
            "join import_ticket_detail itd on itdp.import_ticket_detail_id = itd.id\n" +
            "group by pt.name, pt.id\n", nativeQuery = true)
    List<ProductType> getProductTypeByProfit();
}
