package com.acs.shopify.repository;

import com.acs.shopify.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: brianfroschauer
 * Date: 2019-05-20
 */
public interface CartRepository extends JpaRepository<Cart, Long> {
}
