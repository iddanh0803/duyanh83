package com.springboot.demoquanlysieuthi.controller;

import com.springboot.demoquanlysieuthi.entity.Order;
import com.springboot.demoquanlysieuthi.model.request.ImportRequest;
import com.springboot.demoquanlysieuthi.model.request.OrderRequest;
import com.springboot.demoquanlysieuthi.model.request.ProductTypeRequest;
import com.springboot.demoquanlysieuthi.service.ImportService;
import com.springboot.demoquanlysieuthi.service.OrderService;
import com.springboot.demoquanlysieuthi.service.PermissionService;
import com.springboot.demoquanlysieuthi.service.ProductTypeService;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebController {
    private final OrderService orderService;
    private final ProductTypeService productTypeService;
    private final ImportService importService;
    private final PermissionService permissionService;
    public WebController(OrderService orderService, ProductTypeService productTypeService, ImportService importService, PermissionService permissionService) {
        this.orderService = orderService;
        this.productTypeService = productTypeService;
        this.importService = importService;
        this.permissionService = permissionService;
    }
    @GetMapping("/order/{id}")
    @PreAuthorize("hasRole('ACCOUNTANT')")
    public Order getOrderById(@PathVariable Integer id){
        return orderService.getOrderById(id);
    }
    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request){
        return new ResponseEntity<>(orderService.createOrder(request), HttpStatus.CREATED);
    }
    @PostMapping("/product-type")
    @PreAuthorize("hasRole('ACCOUNTANT')")
    public ResponseEntity<?> createProductType(@RequestBody ProductTypeRequest request){
        return new ResponseEntity<>(productTypeService.createProductType(request), HttpStatus.CREATED);
    }
    @PostMapping("/create-import-ticket")
    public ResponseEntity<?> creatImportTicket(@RequestBody ImportRequest request){
        return new ResponseEntity<>(importService.createImport(request), HttpStatus.CREATED);
    }
    @GetMapping("/permission")
    public ResponseEntity<?> getPermission(){
        return ResponseEntity.ok(permissionService.getPermission());
    }
}
