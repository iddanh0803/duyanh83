package com.springboot.demoquanlysieuthi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ImportTicketDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "import_ticket_id")
    @JsonIgnore
    private ImportTicket importTicket;
    @ManyToOne
    private Product product;
    private Double price;
    private Integer quantity;

}
