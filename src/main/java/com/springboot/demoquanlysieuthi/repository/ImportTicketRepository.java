package com.springboot.demoquanlysieuthi.repository;

import com.springboot.demoquanlysieuthi.entity.ImportTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportTicketRepository extends JpaRepository<ImportTicket, Integer> {
}
