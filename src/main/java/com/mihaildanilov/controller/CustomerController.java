package com.mihaildanilov.controller;

import com.mihaildanilov.dto.NewCustomerRequest;
import com.mihaildanilov.exception.CustomerNotFoundException;
import com.mihaildanilov.exception.IncorrectAgeException;
import com.mihaildanilov.exception.MissingFieldException;
import com.mihaildanilov.model.Customer;
import com.mihaildanilov.service.CustomerService;
import com.mihaildanilov.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.findAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping
            ("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer customerId) {
        Customer customer = customerService.findCustomerById(customerId);
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createCustomer(@RequestBody NewCustomerRequest newCustomerRequest) {
        customerService.saveCustomer(newCustomerRequest);
        ApiResponse response = new ApiResponse(true, "Customer created successfully");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<ApiResponse> updateCustomer(@PathVariable Integer customerId, @RequestBody NewCustomerRequest customerRequest) {
        customerService.updateCustomer(customerId, customerRequest);
        ApiResponse response = new ApiResponse(true, "Customer updated successfully!");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable Integer customerId) {
        customerService.deleteCustomer(customerId);
        ApiResponse response = new ApiResponse(true, "Customer with ID " + customerId + " deleted successfully");
        return ResponseEntity.ok(response);
    }

}