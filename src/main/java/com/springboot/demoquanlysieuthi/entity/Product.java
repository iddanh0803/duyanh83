package com.springboot.demoquanlysieuthi.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String units;
    private Integer amount;
    private Double price;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_type_id")
    private ProductType productType;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id + "," +
                " name='" + name + "," + '\'' +
                " units='" + units + "," + '\'' +
                " amount=" + amount + "," +
                " price=" + price + "," +
                " productType=" + productType +
                '}' + '\n';
    }
}
