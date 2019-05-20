package com.acs.shopify.repository;

import com.acs.shopify.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: brianfroschauer
 * Date: 2019-05-20
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
