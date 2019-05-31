package com.acs.shopify.repository;

import com.acs.shopify.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: brianfroschauer
 * Date: 2019-05-21
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
