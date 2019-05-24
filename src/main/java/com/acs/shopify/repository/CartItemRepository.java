package com.acs.shopify.repository;

import com.acs.shopify.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Author: brianfroschauer
 * Date: 2019-05-20
 */
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findCartItemByIdAndCartId(Long cartItemId, Long cartId);
}
