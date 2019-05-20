package com.acs.shopify.service;

import com.acs.shopify.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: brianfroschauer
 * Date: 2019-05-20
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public Customer findOne(Long id) {
        return null;
    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public Customer save(Customer customer) {
        return null;
    }

    @Override
    public Customer update(Long id, Customer customer) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
