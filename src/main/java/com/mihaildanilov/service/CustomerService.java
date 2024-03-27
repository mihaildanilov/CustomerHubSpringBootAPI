package com.mihaildanilov.service;

import com.mihaildanilov.dto.NewCustomerRequest;
import com.mihaildanilov.exception.CustomerNotFoundException;
import com.mihaildanilov.exception.IncorrectAgeException;
import com.mihaildanilov.exception.MissingFieldException;
import com.mihaildanilov.model.Customer;
import com.mihaildanilov.repository.CustomerRepository;
import com.mihaildanilov.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional(readOnly = true)
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Customer findCustomerById(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + id + " not found."));
    }

    @Transactional
    public void saveCustomer(NewCustomerRequest request) {

        if (request.name() == null || request.name().isEmpty()) {
            throw new MissingFieldException("Name is required!");
        }
        if (request.surname() == null || request.surname().isEmpty()) {
            throw new MissingFieldException("Surname is required!");
        }
        if (request.email() == null || request.email().isEmpty()) {
            throw new MissingFieldException("Email is required!");
        }
        if (request.age() == null) {
            throw new MissingFieldException("Age is required!");
        }
        if (request.age() < 18) {
            throw new IncorrectAgeException("Customers must be 18 and older!");

        }
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setSurname(request.surname());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepository.save(customer);
    }

    @Transactional
    public void updateCustomer(Integer id, NewCustomerRequest request) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + id + " not found."));

        Optional.ofNullable(request.name()).ifPresent(existingCustomer::setName);
        Optional.ofNullable(request.surname()).ifPresent(existingCustomer::setSurname);
        Optional.ofNullable(request.email()).ifPresent(existingCustomer::setEmail);
        Optional.ofNullable(request.age()).ifPresent(existingCustomer::setAge);
        customerRepository.save(existingCustomer);
    }

    @Transactional
    public void deleteCustomer(Integer id) {
        customerRepository.findById(id).
                orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + id + " not found."));
        customerRepository.deleteById(id);
    }
}
