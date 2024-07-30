package com.springboot.demoquanlysieuthi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ImportTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @OneToMany(mappedBy = "importTicket", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ImportTicketDetail> importTicketDetailList = new ArrayList<>();
    private Double totalMoney;
    private LocalDate importDate;
}
