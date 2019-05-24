package com.acs.shopify.service;

import com.acs.shopify.exception.EntityNotFoundException;
import com.acs.shopify.exception.UsernameAlreadyExistsException;
import com.acs.shopify.model.Customer;
import com.acs.shopify.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: brianfroschauer
 * Date: 2019-05-20
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,
                               BCryptPasswordEncoder encoder) {
        this.customerRepository = customerRepository;
        this.encoder = encoder;
    }

    @Override
    public Customer findOne(Long id) {

        return customerRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Customer> findAll() {

        return customerRepository
                .findAll();
    }

    @Override
    public Customer save(Customer customer) {

        validateUsername(customer.getUsername());

        customer.setPassword(encoder.encode(customer.getPassword()));

        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Long id, Customer newCustomer) {

        return customerRepository
                .findById(id)
                .map(customer -> {
                    customer.setFirstName(newCustomer.getFirstName());
                    customer.setLastName(newCustomer.getLastName());
                    return customerRepository.save(customer);
                })
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void delete(Long id) {

        final Customer customer = customerRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);

        customerRepository.delete(customer);
    }

    private void validateUsername(String username) {

        customerRepository
                .findByUsername(username)
                .ifPresent(customer -> {
                    throw new UsernameAlreadyExistsException();
                });
    }
}
