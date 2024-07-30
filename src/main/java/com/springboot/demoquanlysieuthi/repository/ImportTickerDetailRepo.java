package com.springboot.demoquanlysieuthi.repository;

import com.springboot.demoquanlysieuthi.entity.ImportTicketDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportTickerDetailRepo extends JpaRepository<ImportTicketDetail, Integer> {
}
