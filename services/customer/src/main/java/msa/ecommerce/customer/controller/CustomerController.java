package msa.ecommerce.customer.controller;

import jakarta.validation.Valid;
import msa.ecommerce.customer.entity.Customer;
import msa.ecommerce.customer.request.CustomerRequest;
import msa.ecommerce.customer.request.CustomerResponse;
import msa.ecommerce.customer.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getCustomers() {
        return ResponseEntity.ok(customerService.getCustomers());
    }

    @GetMapping("/exists/{customer_id}")
    public ResponseEntity<Boolean> existsById(@PathVariable("customer_id") String customerId) {
        return ResponseEntity.ok(customerService.existsById(customerId));
    }

    @GetMapping("/{customer_id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable("customer_id") String customerId) {
        System.out.println("--------------CustomerController.findById------------------");
        System.out.println("customerId = " + customerId);
        System.out.println("customerService.findById(customerId) = " + customerService.findById(customerId));
        System.out.println("customerService.findById(customerId).firstName() = " + customerService.findById(customerId).firstName());
        System.out.println("customerService.findById(customerId).lastName() = " + customerService.findById(customerId).lastName());
        System.out.println("customerService.findById(customerId).email() = " + customerService.findById(customerId).email());
        System.out.println("-------------------------------------------------------");
        return ResponseEntity.ok(customerService.findById(customerId));
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(
            @RequestBody @Valid CustomerRequest customerRequest) {
        return ResponseEntity.ok(customerService.createCustomer(customerRequest));
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(
            @RequestBody @Valid CustomerRequest customerRequest) {
        customerService.updateCustomer(customerRequest);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{customer_id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customer_id") String customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.noContent().build();
    }
}
