package com.acs.shopify.repository;

import com.acs.shopify.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Author: brianfroschauer
 * Date: 2019-05-20
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByUsername(String username);
}
