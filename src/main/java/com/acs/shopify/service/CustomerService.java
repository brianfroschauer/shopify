package com.acs.shopify.service;

import com.acs.shopify.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: brianfroschauer
 * Date: 2019-05-20
 */
@Service
public interface CustomerService {

    /**
     * Find the customer with the provided id.
     *
     * @param id of the user to be found.
     * @return the customer with the provided id.
     */
    Customer findOne(Long id);

    /**
     * Find all customers.
     *
     * @return all customers or an empty list if there are no customers.
     */
    List<Customer> findAll();

    /**
     * Save the customer provided.
     *
     * The user's password is encrypted before persisting.
     *
     * @param customer to be saved.
     * @return the saved user.
     */
    Customer save(Customer customer);

    /**
     * Update the customer with the provided id.
     *
     * @param id of the customer to be updated.
     * @param customer the new customer.
     * @return the updated customer.
     */
    Customer update(Long id, Customer customer);

    /**
     * Delete the customer with the provided id.
     * @param id of the customer to be deleted.
     */
    void delete(Long id);
}
