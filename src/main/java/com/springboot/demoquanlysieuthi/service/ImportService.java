package com.springboot.demoquanlysieuthi.service;

import com.springboot.demoquanlysieuthi.entity.Employee;
import com.springboot.demoquanlysieuthi.entity.ImportTicket;
import com.springboot.demoquanlysieuthi.entity.ImportTicketDetail;
import com.springboot.demoquanlysieuthi.entity.Product;
import com.springboot.demoquanlysieuthi.model.request.CartItemRequest;
import com.springboot.demoquanlysieuthi.model.request.ImportRequest;
import com.springboot.demoquanlysieuthi.repository.ImportTickerDetailRepo;
import com.springboot.demoquanlysieuthi.repository.ImportTicketRepository;
import com.springboot.demoquanlysieuthi.repository.ProductRepository;
import com.springboot.demoquanlysieuthi.security.CustomEmployeeDetail;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImportService {
    private final ImportTicketRepository importTicketRepository;
    private final ImportTickerDetailRepo importTickerDetailRepo;
    private final ProductRepository productRepository;

    public ImportService(ImportTicketRepository importTicketRepository, ImportTickerDetailRepo importTickerDetailRepo, ProductRepository productRepository) {
        this.importTicketRepository = importTicketRepository;
        this.importTickerDetailRepo = importTickerDetailRepo;
        this.productRepository = productRepository;
    }

    public ImportTicket createImport(ImportRequest request){
        CustomEmployeeDetail customEmployeeDetail = (CustomEmployeeDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employee employee = customEmployeeDetail.getEmployee();

        ImportTicket importTicket = new ImportTicket();
        importTicket.setEmployee(employee);
        importTicket.setImportDate(LocalDate.now());
        importTicketRepository.save(importTicket);
        Double totalMoney = 0.0;
        List<ImportTicketDetail> importTicketDetailList = new ArrayList<>();
        for (CartItemRequest cartItemRequest: request.getCartItems()){
            ImportTicketDetail importTicketDetail = new ImportTicketDetail();
            importTicketDetail.setImportTicket(importTicket);

            Integer productId = cartItemRequest.getProductId();
            Integer quantity = cartItemRequest.getQuantity();

            Product product = productRepository.findById(productId)
                    .orElseThrow(()-> new RuntimeException("Not found product with id: " + productId));

            importTicketDetail.setProduct(product);
            importTicketDetail.setQuantity(quantity);
            importTicketDetail.setPrice(product.getPrice() * quantity);

            importTicketDetailList.add(importTicketDetail);

            product.setAmount(product.getAmount()+quantity);
            productRepository.save(product);
        }
        importTickerDetailRepo.saveAll(importTicketDetailList);
        importTicket.setImportTicketDetailList(importTicketDetailList);
        for (ImportTicketDetail importTicketDetail: importTicketDetailList){
            totalMoney += importTicketDetail.getPrice();
            importTicket.setTotalMoney(totalMoney);
        }
        return importTicketRepository.save(importTicket);
    }
}
