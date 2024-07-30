package com.springboot.demoquanlysieuthi;

import com.github.javafaker.Faker;
import com.springboot.demoquanlysieuthi.entity.*;
import com.springboot.demoquanlysieuthi.repository.*;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
class DemoQuanLySieuThiApplicationTests {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductTypeRepository productTypeRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    Faker faker = new Faker();

    @Test
    void save_roles(){
        List<Role> roles = List.of(
                new Role(1, "SALER"),
                new Role(2,"ACCOUNTANT")
        );
        roleRepository.saveAll(roles);
    }
    @Test
    void save_employees() {
        Role salerRole = roleRepository.findByName("SALER");
        Role accountantRole = roleRepository.findByName("ACCOUNTANT");
        List<Employee> employees = List.of(
                new Employee(null, "Duy Anh", "anh@gmail.com", passwordEncoder.encode("123"),null,null,null,null,null,null,0,null,null, List.of(salerRole)),
                new Employee(null, "Văn A", "a@gmail.com", passwordEncoder.encode("123"),null,null,null,null,null,null,0,null,null, List.of(accountantRole))
        );
        employeeRepository.saveAll(employees);
    }


    @Test
    void save_customer() {
        for (int i = 0; i < 2; i++) {
            Customer customer = Customer.builder()
                    .name(faker.name().fullName())
                    .gender(faker.bothify("male"))
                    .address(faker.address().fullAddress())
                    .phone(faker.phoneNumber().phoneNumber())
                    .numberOfPurchase(faker.number().randomDigitNotZero())
                    .build();
            customerRepository.save(customer);
        }
    }

    @Test
    void save_supplier() {
        for (int i = 0; i < 5; i++) {
            Supplier supplier = Supplier.builder()
                    .name(faker.name().fullName())
                    .address(faker.address().fullAddress())
                    .phone(faker.phoneNumber().phoneNumber())
                    .build();
            supplierRepository.save(supplier);
        }
    }

    @Test
    void save_product_types() {
        for (int i = 0; i < 5; i++) {
            ProductType productType = ProductType.builder()
                    .name(faker.funnyName().name())
                    .build();
            productTypeRepository.save(productType);
        }
    }

    @Test
    void save_product() {
        List<ProductType> productTypes = productTypeRepository.findAll();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            String productName = faker.commerce().productName();
            ProductType randomProductType = productTypes.get(random.nextInt(productTypes.size()));
            Product product = Product.builder()
                    .name(productName)
                    .units(faker.currency().name())
                    .amount(faker.number().numberBetween(1, 100))
                    .price((double) faker.number().numberBetween(10,90_000_000))
                    .productType(randomProductType)
                    .build();
            productRepository.save(product);
        }
    }

//    @Test
//    void save_order() {
//        Random random = new Random();
//        List<Employee> employees = employeeRepository.findAll();
//        List<Customer> customers = customerRepository.findAll();
//        List<OrderDetail> orderDetails = orderDetailRepository.findAll();
//
//        for (int i = 0; i < 5; i++) {
//            Employee rdEmployee = employees.get(random.nextInt(employees.size()));
//            Customer rdCustomer = customers.get(random.nextInt(customers.size()));
//            OrderDetail orderDetail = orderDetails.get(random.nextInt(orderDetails.size()));
//            LocalDateTime fromDate = LocalDateTime.of(2024, 3, 23, 0, 0);
//            LocalDateTime toDate = LocalDateTime.of(2024, 3, 24, 0, 0);
//            Order order = Order.builder()
//                    .customer(rdCustomer)
//                    .employee(rdEmployee)
//                    .date(generateRandomDateTime(fromDate, toDate))
//                    .orderDetail(orderDetail)
//                    .build();
//            orderRepository.save(order);
//        }
//    }

//    @Test
//    void save_order_detail() {
//        Random random = new Random();
//        List<Order> orders = orderRepository.findAll();
//        List<Product> productList = productRepository.findAll();
//        for (int i = 0; i < 3; i++) {
//            List<Product> rdList = new ArrayList<>();
//            Product rdProduct = productList.get(random.nextInt(productList.size()));
//            if (!rdList.contains(rdProduct)) {
//                rdList.add(rdProduct);
//            }
//            for (int j = 0; j < 3; j++) {
//                Order rdOrder = orders.get(random.nextInt(orders.size()));
//                OrderDetail orderDetail = OrderDetail.builder()
//                        .order(rdOrder)
//                        .products(rdList)
//                        .price(random.nextDouble())
//                        .build();
//                orderDetailRepository.save(orderDetail);
//            }
//
//        }
//    }

    public LocalDate generateRandomDateTime(LocalDateTime fromDate, LocalDateTime toDate) {
        Random random = new Random();

        // Convert LocalDateTime to Epoch milliseconds
        long fromMillis = fromDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long toMillis = toDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        // Generate a random Epoch milliseconds value between fromMillis and toMillis
        long randomMillis = fromMillis + (long) (random.nextDouble() * (toMillis - fromMillis));

        // Convert the random milliseconds value back to LocalDateTime
        return LocalDate.ofInstant(java.time.Instant.ofEpochMilli(randomMillis), ZoneId.systemDefault());
    }
}
